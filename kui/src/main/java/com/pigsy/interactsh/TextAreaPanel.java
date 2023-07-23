package com.pigsy.interactsh;

import burp.api.montoya.core.ByteArray;
import burp.api.montoya.core.Range;
import burp.api.montoya.ui.Selection;
import burp.api.montoya.ui.editor.Editor;
import burp.api.montoya.ui.editor.EditorOptions;
import burp.api.montoya.ui.editor.RawEditor;
import com.pigsy.utils.TextAreaUtils;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Optional;

import static com.pigsy.BurpExtensionOx.api;

public class TextAreaPanel extends JPanel {
    private JToolBar toolBar;
    private RSyntaxTextArea textArea;

    public TextAreaPanel() {
        this.setLayout(new BorderLayout());

        toolBar = new JToolBar();
        initToolbar();

        textArea = TextAreaUtils.createTextArea();
        textArea.setEditable(true);
        RTextScrollPane scrollPane = new RTextScrollPane(textArea);
        scrollPane.setBorder(null);

        this.add(toolBar, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void initToolbar() {
        JButton warpBtn = new JButton("换行");
        warpBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (warpBtn.isSelected()) {
                    textArea.setWrapStyleWord(false);
                    warpBtn.setSelected(false);
                } else {
                    textArea.setWrapStyleWord(true);
                    warpBtn.setSelected(true);
                }
            }
        });

        toolBar.add(warpBtn);
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public void append(String text) {
        textArea.append(text);
    }

}
