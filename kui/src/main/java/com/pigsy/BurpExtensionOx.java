package com.pigsy;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.logging.Logging;
import burp.api.montoya.ui.menu.Menu;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BurpExtensionOx implements BurpExtension {
    @Override
    public void initialize(MontoyaApi api) {
        // set extension name
        api.extension().setName("Kui-Ox");
        showBanner(api.logging());

        Menu menu = Menu.menu("Kui-Ox");

        api.userInterface().menuBar().registerMenu(menu);
        api.userInterface().registerSuiteTab("Kui-Ox", new JPanel());
    }

    private void showBanner(Logging logging) {
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
    }
}
