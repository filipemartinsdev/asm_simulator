package test.asmsimulator;

import main.asmsimulator.Kernel;
public class Main {
    public static void main(String[] args) {
        Kernel.mov("eax", "4");
        Kernel.mov("ecx", "Hello World!");
        Kernel.call();
    }
}
