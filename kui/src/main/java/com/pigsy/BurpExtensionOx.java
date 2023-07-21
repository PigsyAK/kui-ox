package com.pigsy;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import com.pigsy.burp.MyContextMenuItemsProvider;
import com.pigsy.burp.MenuUtil;
import com.pigsy.utils.WorkspaceUtil;

import javax.swing.*;

public class BurpExtensionOx implements BurpExtension {
    @Override
    public void initialize(MontoyaApi api) {
        // set extension name
        api.extension().setName("Kui-Ox");

        WorkspaceUtil.initEnv();
        WorkspaceUtil.showBanner(api.logging());

        api.userInterface().menuBar().registerMenu(MenuUtil.getMenu());
        api.userInterface().registerSuiteTab("Kui-Ox", new JPanel());
        api.userInterface().registerContextMenuItemsProvider(new MyContextMenuItemsProvider(api));
    }

}
