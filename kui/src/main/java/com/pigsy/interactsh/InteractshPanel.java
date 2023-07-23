package com.pigsy.interactsh;

import com.pigsy.utils.ExecUtil;
import com.pigsy.utils.FileUtil;
import com.pigsy.utils.WorkspaceUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InteractshPanel extends JPanel {
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private Future<?> future;
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
        requestTextArea.setPaneName("REQUEST ");
        responseTextArea = new TextAreaPanel();
        responseTextArea.setPaneName("RESPONSE ");
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
        createBtn.setSelected(true);
        createBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // .\interactsh-client -sf interact.session -o interact.data -json -psf interactsh_payload.txt
                Path sessionPath = Path.of(WorkspaceUtil.getDataPath().toString(), "interactsh", "interact.session");
                Path outputPath = Path.of(WorkspaceUtil.getDataPath().toString(), "interactsh", "interact.output");
                Path payloadPath = Path.of(WorkspaceUtil.getDataPath().toString(), "interactsh", "interact.payload");
                if (!Files.exists(payloadPath)) {
                    try {
                        Files.createFile(payloadPath);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                String cmd = "\"" + Path.of(WorkspaceUtil.getBinPath().toString(), "interactsh-client") + "\"" +
                        " -sf " + "\"" + sessionPath + "\"" +
                        " -o " + "\"" + outputPath + "\"" +
                        " -json " +
                        " -ps " +
                        " -psf " + "\"" + payloadPath + "\"";
                System.out.println(cmd);

                new Thread(() -> ExecUtil.executeCommand(cmd)).start();
                try {
                    Thread.sleep(6000); // 等待5秒钟
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                domainLabel.setText(FileUtil.readFileToString(payloadPath.toString()).strip());
            }
        });

        domainLabel.setToolTipText("双击复制");

        domainLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Path payloadPath = Path.of(WorkspaceUtil.getDataPath().toString(), "interactsh", "interact.payload");
                    domainLabel.setText(FileUtil.readFileToString(payloadPath.toString()).strip());
                    WorkspaceUtil.setClipboardString(domainLabel.getText().strip());
                }
            }
        });

        toolBar.add(createBtn);
        toolBar.addSeparator();
        toolBar.add(domainLabel);
    }
}
