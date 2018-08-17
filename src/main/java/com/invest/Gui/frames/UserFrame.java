package com.invest.Gui.frames;

import com.invest.Gui.tables.UserTable;

import javax.swing.*;
import java.awt.*;

public class UserFrame {

    private JFrame userFrame;
    private JButton refreshButton;
    private JButton sellButton;
    private JButton addButton;
    private JButton statsButton;
    private JButton settingsButton;
    private JButton logOutButton;


    public void OpenUserFrame(String username) {

        userFrame = new JFrame("User panel");
        userFrame.setSize(540,300);

        configureButtons();

        UserTable userTable = new UserTable();
        JTable table = userTable.showTable();
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.add(new JLabel("Logged in as " + username + "   "));
        buttonsPanel.add(refreshButton);
        buttonsPanel.add(addButton);
        buttonsPanel.add(sellButton);
        buttonsPanel.add(statsButton);
        buttonsPanel.add(settingsButton);
        buttonsPanel.add(logOutButton);

        userFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        userFrame.add(BorderLayout.CENTER, scrollPane);
        userFrame.add(BorderLayout.SOUTH, buttonsPanel);
        userFrame.setVisible(true);

    }

    private void configureButtons() {

        refreshButton = new JButton("refresh");
        addButton = new JButton("add");
        sellButton = new JButton("sell");
        statsButton = new JButton("stats");
        settingsButton = new JButton("settings");
        logOutButton = new JButton("log out");

    }


}
