package test.asmsimulator;

import main.asmsimulator.Kernel;
import main.asmsimulator.Util;

public class Main {
    public static void main(String[] args) {
        Kernel.mov("eax", 4);
        System.out.println(Kernel.readField("eax"));
        Kernel.db("msg", "hi");
        Kernel.mov("ecx", "hi");
        Kernel.call();
    }
}
