package com.invest.Gui.frames;

import com.invest.Gui.tables.UserTable;
import com.invest.dtos.UserDto;
import org.apache.log4j.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserFrame {

    private final static Logger LOGGER = Logger.getLogger(UserFrame.class);

    private UserDto userDto;
    private JFrame userFrame;
    private JButton refreshButton;
    private JButton sellButton;
    private JButton addButton;
    private JButton statsButton;
    private JButton settingsButton;
    private JButton logOutButton;

    protected UserFrame(UserDto userDto) {
        this.userDto = userDto;
    }

    protected void OpenUserFrame() {

        userFrame = new JFrame("User panel");
        userFrame.setSize(600,300);
        userFrame.setLocation(500,300);

        configureButtons();

        UserTable userTable = new UserTable();
        JTable table = userTable.showTable(userDto.getId());
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.add(new JLabel("Logged in as " + userDto.getLogin() + "   "));
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
        refreshButton.addActionListener(new RefreshButtonActionListener());

        addButton = new JButton("add");
        addButton.addActionListener(new AddButtonActionListener());

        sellButton = new JButton("sell");

        statsButton = new JButton("stats");

        settingsButton = new JButton("settings");

        logOutButton = new JButton("log out");
        logOutButton.addActionListener(new LogOutButtonActionListener());

    }

    class AddButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddInstrumentFrame addInstrumentFrame = new AddInstrumentFrame(userDto);
            addInstrumentFrame.openAddingInstrumentFrame();
        }
    }

    class RefreshButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            userFrame.setVisible(true);
        }
    }

    class LogOutButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LogInFrame logInFrame = new LogInFrame();
            logInFrame.run();
            LOGGER.info("User " + userDto.getLogin() + " logged out");
            userFrame.dispose();
        }
    }

}
