package com.pigsy;

import com.pigsy.interactsh.InteractshPanel;

import javax.swing.*;

public class KuiOxTab extends JTabbedPane {

    public KuiOxTab() {
        JToolBar toolBar = new JToolBar();
        toolBar.add(new JButton("+"));

        this.addTab("Interactsh", new InteractshPanel());
    }

}
