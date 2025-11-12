package test.asmsimulator;

import main.asmsimulator.Kernel;

public class Main {
    public static void main(String[] args) {
        Kernel.mov("eax", 4);
        Kernel.mov("ebx", 1);
        Kernel.db("msg", "Hello World\n");
        Kernel.mov("ecx", "msg");
        Kernel.call();

        Kernel.db("msg2", "Segundo texto\n");
        Kernel.mov("ecx", "msg2");
        Kernel.call();
    }
}
