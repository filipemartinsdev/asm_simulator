package main.asmsimulator;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
// import java.util.List;

public final class Kernel {
    private static Map<String, byte[]> fields = new HashMap<>();

    private static final String[] REGISTERS_16BITS = { "ax", "bx", "cx", "dx" };
    private static final String[] REGISTERS_8BITS = {
            "al", "ah",
            "bl", "bh",
            "cl", "ch",
            "dl", "dh",
    };

    static {
        fields.put("eax", new byte[4]);
        fields.put("ebx", new byte[4]);
        fields.put("ecx", new byte[4]);
        fields.put("edx", new byte[4]);
    }

    public static void call() {
        String eaxValue = String.copyValueOf(parseBytesToChars(Kernel.fields.get("eax")));
        switch (eaxValue) {
            case "4":
                Kernel.Syscall.print();
                return;
            default:
                System.out.println("[ERROR] INVALID SYSCALL");
                System.out.println(eaxValue); // TODO: FIX THIS
                return;
        }
    }

    public static void mov(String destin, String value) {

        if (!Kernel.fields.containsKey(destin)) {
            System.out.println("[ERROR] INVALID DESTIN FIELD");
            return;
        }

        Kernel.fields.put(destin, parseCharsToBytes(value.toCharArray()));
    }

    public static void db(String field, String value) {
        if (Kernel.fields.containsKey(field)) {
            System.out.println("[ERROR] FIELD IS ALREADY DEFINED.");
            return;
        }

        Kernel.fields.put(field, Kernel.parseCharsToBytes(value.toCharArray()));
    }

    public static void resb(String field, int bytes) {
        if (Kernel.fields.containsKey(field)) {
            System.out.println("[ERROR] FIELD "+field+" IS ALREADY DEFINED");
            return;
        }

        Kernel.fields.put(field, new byte[bytes]);
    }

    public static String readField(String field){
        if(!Kernel.fields.containsKey(field)){
//            System.out.println("[ERROR] INVALID FIELD");
            throw new RuntimeException("INVALID FIELD");
        }
        else {
            return String.copyValueOf(Kernel.parseBytesToChars(fields.get(field)));
        }
    }
    public static char[] parseBytesToChars(byte[] bytes){
        var charArr = new char[bytes.length/2];

        for(int i=0; i<bytes.length; i+=2){
            charArr[i/2] = (char)(((bytes[i] & 0xFF ) << 8) | (bytes[i+1] & 0xFF));
        }

        return charArr;
    }

    public static byte[] parseCharsToBytes(char[] chars){
        var byteArr = new byte[chars.length * 2];
        int i = 0;

        for (char c : chars) {
            byteArr[i+1] = (byte) (c & 0xFF);
            byteArr[i] = (byte) ((c >> 8) & 0xFF);
            i+=2;
        }

        return byteArr;
    }

    private final class Syscall {
        public static void print() {

            char[] charArr = parseBytesToChars(Kernel.fields.get("ecx"));
            System.out.println(charArr);
        }

        public static void read() {
        }

        public static void write() {
        }
    }
}