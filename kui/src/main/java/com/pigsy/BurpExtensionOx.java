package com.pigsy;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.extension.ExtensionUnloadingHandler;
import com.pigsy.burp.MyContextMenuItemsProvider;
import com.pigsy.burp.MenuUtil;
import com.pigsy.utils.WorkspaceUtil;

import javax.swing.*;

public class BurpExtensionOx implements BurpExtension, ExtensionUnloadingHandler {
    private final KuiOxTab kuiOxTab = new KuiOxTab();
    public static MontoyaApi api;

    @Override
    public void initialize(MontoyaApi api) {
        BurpExtensionOx.api = api;
        // set extension name
        api.extension().setName("Kui-Ox");

        WorkspaceUtil.initEnv();
        WorkspaceUtil.showBanner(api.logging());

        api.userInterface().menuBar().registerMenu(MenuUtil.getMenu());
        api.userInterface().registerSuiteTab("Kui-Ox", kuiOxTab);
        api.userInterface().registerContextMenuItemsProvider(new MyContextMenuItemsProvider(api));
    }

    @Override
    public void extensionUnloaded() {
        kuiOxTab.removeAll();
    }
}
