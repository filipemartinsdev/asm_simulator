package main.asmsimulator;

// import java.util.List;

public final class Kernel {
    private static Memory memory;

    static {
        memory = new Memory();
        memory.reserveBytes("eax", 4);
        memory.reserveBytes("ebx", 4);
        memory.reserveBytes("ecx", 4);
        memory.reserveBytes("edx", 4);
    }

    public static void call() {
        String eaxValue = String.copyValueOf(Util.parseBytesToChars(memory.read("eax")));
        switch (eaxValue) {
            case "4":
                Kernel.Syscall.print();
                return;
            default:
                throw new RuntimeException("[ERROR] INVALID SYSCALL");
        }
    }

    public static void mov(String destin, String value) {
        if (!memory.fieldExists(destin)){
            throw new RuntimeException("[ERROR] INVALID DESTIN");
        }

        if (value.startsWith("[") && value.endsWith("]")){
            String valueNoBrackets = Util.removeBrackets(value);
            byte[] valueBytes = memory.read(valueNoBrackets);

            if (memory.read(destin).length < valueBytes.length){
                throw new RuntimeException("[ERROR] SEGMENTATION FAULT (CORE DUMPED)");
            }
            else {
                memory.overwrite(destin, valueBytes);
            }
        }
        else {
            memory.overwrite(destin, memory.read(value));
        }
    }

    public static void mov(String destin, int value){
        if (!memory.fieldExists(destin)) {
            throw new RuntimeException("[ERROR] INVALID DESTIN FIELD");
        }

        if (memory.read(destin).length < Util.parseCharsToBytes((value+"").toCharArray()).length){
            throw new RuntimeException("[ERROR] SEGMENTATION FAULT (CORE DUMPED)");
        }

        memory.overwrite(destin, Util.parseCharsToBytes((value+"").toCharArray()));
    }

    public static void db(String field, String value) {
        if (memory.fieldExists(field)){
            throw new RuntimeException("[ERROR] FIELD IS ALREADY DEFINED");
        }

        memory.alloc(field, Util.parseCharsToBytes(value.toCharArray()));
    }

    public static void resb(String field, int bytes) {
        if (memory.fieldExists(field)) {
            System.out.println("[ERROR] FIELD "+field+" IS ALREADY DEFINED");
            return;
        }

        memory.reserveBytes(field, bytes);
    }

    public static String readField(String field){
        if(!memory.fieldExists(field)){
            throw new RuntimeException("[ERROR] INVALID FIELD");
        }
        else {
            return String.copyValueOf(Util.parseBytesToChars(memory.read(field)));
        }
    }


    private final class Syscall {
        public static void print() {
            String ebx = String.copyValueOf(Util.parseBytesToChars(Kernel.memory.read("ebx")));

            switch (ebx){
                case "1":
                    System.out.print(
                            String.copyValueOf(Util.parseBytesToChars(memory.read("ecx")))
                    );
                    return;
                default:
                    throw new RuntimeException("[ERROR] INVALID EDX");
            }

        }

        public static void read() {
        }

        public static void write() {
        }
    }
}