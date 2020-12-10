package com.urise.webapp;

import java.io.File;

public class MainPrintFileName {
    public static void main(String[] args) {
        String dirPath = "..\\basejava";
        printFileName(dirPath, 0);
    }

    private static void printFileName(String dirName, int level) {
        File dir = new File(dirName);
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
        if (!dir.isDirectory()) {
            System.out.println("File: " + dir.getName());
        } else {
            System.out.println("Directory: " + dir.getName());
            File[] list = dir.listFiles();
            if (list != null) {
                for (File name : list) {
                    printFileName(name.getPath(), level + 1);
                }
            }
        }
    }

}
