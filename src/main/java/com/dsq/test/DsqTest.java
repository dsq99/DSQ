package com.dsq.test;

import com.dsq.tool.DsqTool;

import java.util.ArrayList;

public class DsqTest {

    public static void main(String[] args) {
        ArrayList<String> strings = DsqTool.readTxt("aaaa.txt");
        for (String string : strings) {
            System.out.println("string = " + string);
        }
    }
}
