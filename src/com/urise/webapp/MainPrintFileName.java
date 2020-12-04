package com.urise.webapp;

import java.io.File;

public class MainPrintFileName {
    public static void main(String[] args) {
        String dirPath = "C:\\Project\\basejava";
        recursive(dirPath);
    }

    private static void recursive(String dirName) {
        File dir = new File(dirName);
        if (!dir.isDirectory()) {
            System.out.println("File: " + dir.getName());
        } else {
            System.out.println("Directory: " + dir.getName());
            File[] list = dir.listFiles();
            if (list != null) {
                for (File name : list) {
                    recursive(name.getPath());
                }
            }
        }
    }

}
