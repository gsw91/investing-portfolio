package com.invest.Gui.frames;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class LogInFrame {

    private final static Logger LOGGER = Logger.getLogger(LogInFrame.class);

    private JFrame loginFrame;
    private JTextField loginField;
    private JPasswordField passwordField;

    public void run() {
        loginFrame = new JFrame("Investing app log in");

        JButton logIn = new JButton(" click to log in");
        logIn.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        logIn.addActionListener(new LogInActionListener());

        JButton signUp = new JButton("change to sign up");
        signUp.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        signUp.addActionListener(new SignUpActionListener());

        JButton exitButton = new JButton(" exit ");
        exitButton.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        exitButton.addActionListener(new ExitButtonActionListener());

        loginField = new JTextField("login");
        loginField.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        loginField.addMouseListener(new LoginAreaMouseListener());

        passwordField = new JPasswordField("password");
        passwordField.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        passwordField.addMouseListener(new PasswordAreaMouseListener());

        JLabel loginLabel = new JLabel("Enter the user name", SwingConstants.CENTER);
        loginLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        loginLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel passwordLabel = new JLabel("Enter the password", SwingConstants.CENTER);
        passwordLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        passwordLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new GridLayout(2, 2));
        userPanel.add(loginLabel);
        userPanel.add(loginField);
        userPanel.add(passwordLabel);
        userPanel.add(passwordField);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1, 2));
        optionsPanel.add(signUp);
        optionsPanel.add(exitButton);

        loginFrame.getContentPane().add(BorderLayout.CENTER, userPanel);
        loginFrame.getContentPane().add(BorderLayout.SOUTH, logIn);
        loginFrame.getContentPane().add(BorderLayout.NORTH, optionsPanel);

        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginFrame.setSize(600, 450);
        loginFrame.setVisible(true);

    }

    class ExitButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    class SignUpActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SignUpFrame signUpFrame = new SignUpFrame();
            signUpFrame.openSignUpFrame();
            loginFrame.dispose();
        }
    }

    class LogInActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String login = loginField.getText();
            char[] chars = passwordField.getPassword();
            StringBuilder builder = new StringBuilder();
            for(char i: chars) {
                builder.append(i);
            }
            String password = builder.toString();

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
                loginFrame.dispose();
            } else {
                LOGGER.warn("No user in database: " + login + " " + password);
            }
        }
    }

    class LoginAreaMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (loginField.getText().equals("login")) {
                loginField.setText("");
            }
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

    class PasswordAreaMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            passwordField.setEchoChar('*');
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
