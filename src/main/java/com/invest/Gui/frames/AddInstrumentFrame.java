package com.invest.Gui.frames;

import com.invest.domain.MarketPrice;
import com.invest.domain.User;
import com.invest.dtos.UserDto;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

public class AddInstrumentFrame {

    private final static Logger LOGGER = Logger.getLogger(AddInstrumentFrame.class);
    private UserDto userDto;

    public AddInstrumentFrame(UserDto userDto) {
        this.userDto = userDto;
    }

    public void openAddingInstrumentFrame() {
        JFrame frame = new JFrame("Add instrument");
        frame.setLocation(500,300);
        frame.setSize(300,180);
        frame.setLayout(new GridLayout(4,2));
        frame.getContentPane().add(new JLabel("Instrument name"));
        frame.getContentPane().add(new JTextField());
        frame.getContentPane().add(new JLabel("Quantity"));
        frame.getContentPane().add(new JTextField());
        frame.getContentPane().add(new JLabel("Price"));
        frame.getContentPane().add(new JTextField());
        frame.getContentPane().add(new JButton("Confirm"));
        frame.getContentPane().add(new JButton("Cancel"));

        frame.setVisible(true);
    }


    public void addingInstrument(MarketPrice marketPrice, double buyingPrice, LocalDate buyingDate) throws IOException {

        String request = "http://localhost:8080/v1/instrument/add";
        URL url = new URL(request);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("POST");

        JSONObject cred = new JSONObject();
        cred.put("user", userDto);
        cred.put("marketPrice", marketPrice);
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
