package com.linhnt.speedshoot.main;
import java.lang.Math;
import java.util.Scanner;

public class Test {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        float x = in.nextFloat();
        float y = in.nextFloat();
        float length = (float)Math.sqrt(x * x + y * y);

        System.out.println(Math.acos(x / length));
    }
}
