package com.invest.Gui;

import org.apache.log4j.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class LogInFrame {

    private final static Logger LOGGER = Logger.getLogger(LogInFrame.class);

    private JFrame frame;
    private JTextField loginArea;
    private JTextField passwordArea;
    private JButton logIn;
    private JButton signUp;

    public void run() {
        frame = new JFrame("Log In");

        logIn = new JButton("log in");
        logIn.addActionListener(new LogInActionListener());

        signUp = new JButton("sign up");
        signUp.addActionListener(null);

        loginArea = new JTextField("login");
        loginArea.setPreferredSize(new Dimension(50, 20));
        loginArea.addMouseListener(new LoginAreaMouseListener());

        passwordArea = new JTextField("password");
        passwordArea.setPreferredSize(new Dimension(50, 20));
        passwordArea.addMouseListener(new PasswordAreaMouseListener());

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new GridLayout(2, 2));
        userPanel.add(new JLabel("Entry user name"));
        userPanel.add(loginArea);
        userPanel.add(new JLabel("Entry password"));
        userPanel.add(passwordArea);

        frame.getContentPane().add(BorderLayout.CENTER, userPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, logIn);
        frame.getContentPane().add(BorderLayout.NORTH, signUp);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);

    }

    class LogInActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String login = loginArea.getText();
            String password = passwordArea.getText();
            System.out.println(login + password);
            try {
                sendLogRequest(login, password);
            } catch (IOException exce) {
                System.out.println("Be careful");
            }
        }

        private void sendLogRequest(String login, String password) throws IOException {
            String request = "http://localhost:8080/v1/user/login?name=" + login + "&password=" + password;
            URL url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode==200) {
                LOGGER.info("Log in successfully");
                frame.dispose();
            } else {
                LOGGER.warn("No user in database: " + login + " " + password);
            }
        }
    }

    class LoginAreaMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            loginArea.setText("");
            loginArea.requestFocus();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (loginArea.getText().equals("login")) {
                loginArea.setText("");
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (loginArea.getText().equals("")) {
                loginArea.setText("login");
            }
        }
    }

    class PasswordAreaMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            passwordArea.setText("");
            passwordArea.requestFocus();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }



}
