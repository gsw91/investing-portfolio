package com.invest.Gui.frames;

import com.invest.Gui.tables.UserTable;
import com.invest.dtos.UserDto;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFrame {

    private final static Logger LOGGER = Logger.getLogger(UserFrame.class);
    private AddInstrumentFrame addInstrumentFrame;
    private SellInstrumentFrame sellInstrumentFrame;
    private UserDto userDto;
    private JFrame userFrame;
    private JButton refreshButton;
    private JButton sellButton;
    private JButton addButton;
    private JButton statsButton;
    private JButton settingsButton;
    private JButton logOutButton;
    private JTable table;
    private UserTable userTable;

    protected UserFrame(UserDto userDto) {
        this.userDto = userDto;
    }

    protected void openUserFrame() {

        userFrame = new JFrame("User panel");
        userFrame.setSize(600,300);
        userFrame.setLocation(500,300);

        configureOtherFrames();
        configureButtons();

        userTable = new UserTable();
        table = userTable.showTable(userDto.getId());
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
        userFrame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        userFrame.getContentPane().add(BorderLayout.SOUTH, buttonsPanel);
        userFrame.setVisible(true);

    }

    private void configureOtherFrames() {
        addInstrumentFrame = new AddInstrumentFrame(userFrame, userDto, false);
        addInstrumentFrame.openAddingInstrumentFrame();

        sellInstrumentFrame = new SellInstrumentFrame(userFrame, userDto, false);
        sellInstrumentFrame.openSellingWindow();
    }

    private void configureButtons() {

        refreshButton = new JButton("refresh");
        refreshButton.addActionListener(new RefreshButtonActionListener());

        addButton = new JButton("add");
        addButton.addActionListener(new AddButtonActionListener());

        sellButton = new JButton("sell");
        sellButton.addActionListener(new SellButtonActionListener());

        statsButton = new JButton("stats");

        settingsButton = new JButton("settings");

        logOutButton = new JButton("log out");
        logOutButton.addActionListener(new LogOutButtonActionListener());

    }

    class SellButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sellInstrumentFrame.setVisibility(true);
        }
    }

    class AddButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addInstrumentFrame.setVisibility(true);
        }
    }

    class RefreshButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LOGGER.info("Refreshing shares panel");
            UserFrame newUserFrame = new UserFrame(userDto);
            newUserFrame.openUserFrame();
            userFrame.dispose();
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
