package com.pigsy.utils;

import burp.api.montoya.logging.Logging;
import com.pigsy.BurpExtensionOx;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class WorkspaceUtil {
    public static void setClipboardString(String text) {
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 封装文本内容
        Transferable trans = new StringSelection(text);
        // 把文本内容设置到系统剪贴板
        clipboard.setContents(trans, null);
    }

    public static Path getWorkSpacePath() {
        return Path.of(System.getProperty("user.home"), ".kui-ox");
    }

    public static void initEnv() {
        Path workDir = getWorkSpacePath();
        if (!Files.exists(workDir)) {
            try {
                Files.createDirectories(workDir);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ArrayList<String> paths = new ArrayList<>();
        // 在工作目录下添加目录
        paths.add("bin");
        paths.add("conf");
        paths.add("conf/interactsh");
        paths.add("data");
        paths.add("temp");

        for (String path : paths) {
            Path tmpPath = Path.of(workDir.toString(), path);
            if (!Files.exists(tmpPath)) {
                try {
                    Files.createDirectories(tmpPath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void showBanner(Logging logging) {
        // 使用ClassLoader获取资源文件的输入流
        InputStream inputStream = BurpExtensionOx.class.getClassLoader().getResourceAsStream("banner.txt");

        StringBuilder banner = new StringBuilder();
        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            // 处理输入流，例如读取内容
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    banner.append(line).append("\n");
                }
            } catch (Exception e) {
                logging.logToError(e.getMessage());
            }
        } else {
            logging.logToError("资源文件未找到");
        }
        logging.logToOutput(banner.toString());
        System.out.println(banner);
    }


}
