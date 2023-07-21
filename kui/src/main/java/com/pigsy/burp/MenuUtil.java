package com.pigsy.burp;

import burp.api.montoya.ui.menu.Menu;
import burp.api.montoya.ui.menu.MenuItem;
import com.pigsy.utils.WorkspaceUtil;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuUtil {
    public static Menu getMenu() {
        return Menu.menu("Kui-Ox").withMenuItems(getMenuItems());
    }

    private static List<MenuItem> getMenuItems() {
        List<MenuItem> items = new ArrayList<>();
        MenuItem openWorkspaceItem = MenuItem.basicMenuItem("Workspace").withAction(() -> {
            try {
                Desktop.getDesktop().open(new File(WorkspaceUtil.getWorkSpacePath().toString()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        items.add(openWorkspaceItem);

        return items;
    }
}
