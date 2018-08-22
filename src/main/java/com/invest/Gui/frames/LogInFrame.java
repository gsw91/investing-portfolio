package com.invest.Gui.frames;

import com.invest.dtos.UserDto;
import com.invest.exceptions.LogInException;
import org.apache.log4j.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class LogInFrame {

    private final static Logger LOGGER = Logger.getLogger(LogInFrame.class);

    private JFrame loginFrame;
    private JTextField loginField;
    private JPasswordField passwordField;

    public void run() {
        loginFrame = new JFrame("Investing app log in");
        loginFrame.setLocation(500,300);

        JButton logIn = new JButton(" click to log in");
        logIn.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        logIn.addActionListener(new LogInActionListener());

        JButton signUp = new JButton("sign up");
        signUp.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        signUp.addActionListener(new SignUpActionListener());

        JButton exitButton = new JButton(" exit ");
        exitButton.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        exitButton.addActionListener(new ExitButtonActionListener());

        loginField = new JTextField();
        loginField.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        loginField.addMouseListener(new LoginAreaMouseListener());

        passwordField = new JPasswordField();
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
        loginFrame.setSize(400, 250);
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
            try {
                sendLogRequest(login, password);
                LOGGER.info("User " + login + " logged in ");
            } catch (LogInException loginExce) {
                LOGGER.warn("Wrong login or password");
            } catch (IOException exce) {
                LOGGER.error("Connection refused");
            }
        }

        private void sendLogRequest(String login, String password) throws IOException, LogInException {
            String request = "http://localhost:8080/v1/user/login?name=" + login + "&password=" + password;
            URL url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode==200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = bufferedReader.readLine()) != null) {
                    response.append(inputLine);
                }

                String allResponse = response.toString();
                allResponse = allResponse.replace("{", "");
                allResponse = allResponse.replace("}", "");
                String[] array = allResponse.split(",");

                ArrayList<String> list = new ArrayList<>();

                    for(String i: array) {
                        String[] nextArray = i.split(":");
                        if (nextArray.length ==2) {
                            list.add(nextArray[1]);
                        }
                    }

                    try {
                        UserDto userDto = new UserDto(Long.valueOf(list.get(0)), list.get(1), list.get(2), list.get(3));
                        UserFrame userFrame = new UserFrame(userDto);
                        userFrame.openUserFrame();
                        loginFrame.dispose();
                    } catch (NumberFormatException e) {
                        throw new LogInException();
                    }

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
            passwordField.setText("");
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
