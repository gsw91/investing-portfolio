package com.invest.Gui.frames;

import com.invest.dtos.UserDto;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.time.LocalDate;

public class AddInstrumentFrame {

    private final static Logger LOGGER = Logger.getLogger(AddInstrumentFrame.class);
    private UserDto userDto;

    protected AddInstrumentFrame(UserDto userDto) {
        this.userDto = userDto;
    }

    protected void openAddingInstrumentFrame() {
        JFrame frame = new JFrame("Add instrument");
        frame.setLocation(500,300);
        frame.setSize(300,180);
        frame.setLayout(new GridLayout(5,2));
        frame.getContentPane().add(new JLabel("Instrument name"));
        frame.getContentPane().add(new JTextField());
        frame.getContentPane().add(new JLabel("Quantity"));
        frame.getContentPane().add(new JTextField());
        frame.getContentPane().add(new JLabel("Price"));
        frame.getContentPane().add(new JTextField());
        frame.getContentPane().add(new JLabel("Bought YYYY-MM-DD"));
        frame.getContentPane().add(new JTextField());
        frame.getContentPane().add(new JButton("Confirm"));
        frame.getContentPane().add(new JButton("Cancel"));

        frame.setVisible(true);
    }

    public void addingInstrument(String index, Long quantity, Double buyingPrice, LocalDate buyingDate) throws IOException {

        String request = "http://localhost:8080/v1/instrument/add";
        URL url = new URL(request);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("POST");

        JSONObject cred = new JSONObject();
        cred.put("userId", userDto.getId());
        cred.put("quantity", quantity);
        cred.put("sharesIndex", index);
        cred.put("buyingPrice", buyingPrice);
        cred.put("buyingDate", buyingDate);

        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
        wr.write(cred.toString());
        wr.flush();

        int responseCode = connection.getResponseCode();
        System.out.println("response code " + responseCode);
        if (responseCode == 200) {
            LOGGER.info("Instrument added for user " + userDto.getLogin());
        } else {
            LOGGER.warn("Adding instrument failed");
        }
    }

}
