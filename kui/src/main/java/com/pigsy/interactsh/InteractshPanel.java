package com.pigsy.interactsh;

import com.pigsy.utils.WorkspaceUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InteractshPanel extends JPanel {
    private JToolBar toolBar = new JToolBar();
    private JLabel domainLabel = new JLabel("请刷新域名");
    private JTable recordTable;
    private DefaultTableModel tableModel;
    private TextAreaPanel requestTextArea;
    private TextAreaPanel responseTextArea;

    public InteractshPanel() {
        this.setLayout(new BorderLayout());

        initToolbar();

        // Left
        recordTable = new JTable();
        tableModel = new DefaultTableModel() {
            // 不可编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] columnNames = {
                "#",
                "TIME",
                "TYPE"};
        tableModel.setColumnIdentifiers(columnNames);
        recordTable.setModel(tableModel);
        JScrollPane tableScroll = new JScrollPane(recordTable);
        tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Right
        requestTextArea = new TextAreaPanel();
        responseTextArea = new TextAreaPanel();
        JSplitPane splitDataPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, requestTextArea, responseTextArea);
        splitDataPane.setDividerLocation(350);


        JSplitPane splitPane = new JSplitPane();
        splitPane.setLeftComponent(tableScroll);
        splitPane.setRightComponent(splitDataPane);

        this.add(toolBar, BorderLayout.NORTH);
        this.add(splitPane, BorderLayout.CENTER);
    }

    private void initToolbar() {
        toolBar.setFloatable(false);

        JButton createBtn = new JButton("刷新域名");
        createBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                domainLabel.setText("cjvs95c2vtc0000ey8hggjphw1ayyyyyb.oast.fun");
            }
        });

        domainLabel.setToolTipText("双击复制");

        domainLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    WorkspaceUtil.setClipboardString(domainLabel.getText());
                }
            }
        });

        toolBar.add(createBtn);
        toolBar.addSeparator();
        toolBar.add(domainLabel);
    }
}
