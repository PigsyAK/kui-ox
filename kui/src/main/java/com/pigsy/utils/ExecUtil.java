package com.pigsy.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ExecUtil {
    public static void executeCommand(String command) {
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String osArch = System.getProperty("os.arch");

        System.out.println("Operating System:");
        System.out.println("\tName: " + osName);
        System.out.println("\tVersion: " + osVersion);
        System.out.println("\tArchitecture: " + osArch);

        String cmd;
        if (osName.startsWith("Windows")) {
            System.out.println("This is Windows");
            cmd = "cmd \\k " + command;
        } else if (osName.startsWith("Linux")) {
            System.out.println("This is Linux");
            cmd = "bash -c " + command;
        } else if (osName.startsWith("Mac")) {
            System.out.println("This is Mac");
            cmd = "bash -c " + command;
        } else {
            System.out.println("Unknown operating system");
            cmd = "bash -c " + command;
        }
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(cmd.split(" "));
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String cmd = "C:\\Users\\G3G4X5X6\\.kui-ox\\bin\\interactsh-client.exe" +
                " -sf " + "\"C:\\Users\\G3G4X5X6\\.kui-ox\\data\\interactsh\\interact.session\"" +
                " -o " + "\"C:\\Users\\G3G4X5X6\\.kui-ox\\data\\interactsh\\interact.output\"" +
                " -json " +
                " -psf " + "\"C:\\Users\\G3G4X5X6\\.kui-ox\\data\\interactsh\\interact.payload\"";
        System.out.println(cmd);
        executeCommand(cmd);
    }
}
