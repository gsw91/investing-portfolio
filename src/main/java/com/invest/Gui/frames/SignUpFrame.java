package com.invest.Gui.frames;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class SignUpFrame {

    private final static Logger LOGGER = Logger.getLogger(SignUpFrame.class);

    private JFrame singUpFrame;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JTextField emailField;

    public SignUpFrame() throws HeadlessException {
    }

    public void openSignUpFrame() {
        singUpFrame = new JFrame("Sign Up");
        singUpFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        singUpFrame.setLocation(500,300);

        loginField = new JTextField("login");
        loginField.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        loginField.addMouseListener(new LoginFieldMouseListener());

        passwordField = new JPasswordField("password");
        passwordField.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        passwordField.addMouseListener(new PasswordFieldMouseListener());

        JButton exitButton = new JButton(" exit ");
        exitButton.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        exitButton.addActionListener(new ExitButtonActionListener());

        emailField = new JTextField("email");
        emailField.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        emailField.addMouseListener(new EmailFieldMouseListener());

        JButton logIn = new JButton("log in");
        logIn.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        logIn.addActionListener(new LogInActionListener());

        JButton signUp = new JButton(" click to sign up");
        signUp.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        signUp.addActionListener(new SignUpActionListener());

        JLabel loginLabel = new JLabel("Enter the user name", SwingConstants.CENTER);
        loginLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        loginLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel passwordLabel = new JLabel("Enter the password", SwingConstants.CENTER);
        passwordLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        passwordLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel emailLabel = new JLabel("Enter the email", SwingConstants.CENTER);
        emailLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        emailLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(loginLabel);
        panel.add(loginField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(emailLabel);
        panel.add(emailField);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1, 2));
        optionsPanel.add(logIn);
        optionsPanel.add(exitButton);

        singUpFrame.getContentPane().add(BorderLayout.CENTER, panel);
        singUpFrame.getContentPane().add(BorderLayout.SOUTH, signUp);
        singUpFrame.getContentPane().add(BorderLayout.NORTH, optionsPanel);
        singUpFrame.setSize(400,250);
        singUpFrame.setVisible(true);
    }

    class SignUpActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = loginField.getText();
            char[] chars = passwordField.getPassword();
            StringBuilder builder = new StringBuilder();
            for(char i: chars) {
                builder.append(i);
            }
            String password = builder.toString();
            String email = emailField.getText();

            try {
                sendCreateRequest(name, password, email);
            } catch (IOException ex) {
                LOGGER.warn("Connection refused");
            }
        }

        private void sendCreateRequest(String login, String password, String email) throws IOException {
            String request = "http://localhost:8080/v1/user/create";
            URL url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");

            JSONObject cred = new JSONObject();
            cred.put("login", login);
            cred.put("password", password);
            cred.put("email", email);

            OutputStreamWriter wr= new OutputStreamWriter(connection.getOutputStream());
            wr.write(cred.toString());
            wr.flush();

            int responseCode = connection.getResponseCode();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            String allResponse = response.toString();

            if (responseCode==200 && allResponse.equals("User created")) {
                LOGGER.info("User created");
            } else {
                LOGGER.warn(allResponse);
            }
        }
    }

    class ExitButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    class LogInActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LogInFrame logInFrame = new LogInFrame();
            logInFrame.run();
            singUpFrame.dispose();
        }
    }

    class LoginFieldMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            loginField.setText("");
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

    class PasswordFieldMouseListener implements MouseListener {
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

    class EmailFieldMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            emailField.setText("");
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
