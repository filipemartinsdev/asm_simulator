package test.asmsimulator;

import main.asmsimulator.Kernel;
public class Main {
    public static void main(String[] args) {
        Kernel.mov("eax", "4");
        Kernel.db("msg", "Hello ASSEMBLY!\n");
        Kernel.mov("ecx", Kernel.readField("msg"));
        Kernel.call();
    }
}
