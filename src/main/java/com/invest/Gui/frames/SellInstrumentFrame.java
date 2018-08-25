package com.invest.Gui.frames;

import com.invest.dtos.InstrumentDto;
import com.invest.dtos.UserDto;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

public class SellInstrumentFrame {

    private final static Logger LOGGER = Logger.getLogger(SellInstrumentFrame.class);
    private JFrame sellingFrame;
    private JFrame userFrame;
    private UserDto userDto;
    private Boolean visibility;
    private JTextField instrumentName;
    private JTextField quantity;
    private JTextField price;
    private JButton confirmButton;
    private JButton cancelButton;

    public SellInstrumentFrame(JFrame userFrame, UserDto userDto, Boolean visibility) {
        this.userFrame = userFrame;
        this.userDto = userDto;
        this.visibility = visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
        sellingFrame.setVisible(visibility);
    }

    public void openSellingWindow() {
        sellingFrame = new JFrame("Sell instrument");
        sellingFrame.setLocation(500,300);
        sellingFrame.setSize(300, 180);
        sellingFrame.setVisible(true);

        confirmButton = new JButton("Sell");
        confirmButton.addActionListener(new ConfirmButtonActionListener());
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CancelButtonActionListener());

        instrumentName = new JTextField();
        quantity = new JTextField();
        price = new JTextField();

        sellingFrame.setLayout(new GridLayout(4, 2));
        sellingFrame.add(new JLabel("Instrument"));
        sellingFrame.add(instrumentName);
        sellingFrame.add(new JLabel("Quantity"));
        sellingFrame.add(quantity);
        sellingFrame.add(new JLabel("Price"));
        sellingFrame.add(price);
        sellingFrame.add(confirmButton);
        sellingFrame.add(cancelButton);
        sellingFrame.setVisible(visibility);
    }

    class ConfirmButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Long userId = userDto.getId();
            String name = instrumentName.getText().toUpperCase();
            Long qtyToSell = convertToLong(quantity.getText());
            Double sellingPrice = convertToDouble(price.getText());

            try {
//                String[] array = getListOfInstruments(userId, name);
//                addStats(userId, name, qtyToSell, sellingPrice, array);
                boolean isSold = sellInstrument(userId, name, qtyToSell, sellingPrice);
                if (isSold) {
                    setVisibility(false);
                    UserFrame newUserFrame = new UserFrame(userDto);
                    newUserFrame.openUserFrame();
                    userFrame.dispose();
                }
            } catch (IOException exce) {
                LOGGER.warn(exce.getMessage());
            }
        }

        private Long convertToLong(String quantity) throws NumberFormatException {
            return Long.valueOf(quantity);
        }

        private Double convertToDouble(String price) throws NumberFormatException {
            price = price.replace(",", ".");
            return Double.valueOf(price);
        }

//        private void addStats(Long userId, String index, Long quantity, Double sellingPrice, String[] data) throws IOException {
//            String request = "http://localhost:8080/v1/statistics/add";
//            URL url = new URL(request);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
//            connection.setRequestProperty("Content-Type", "application/json");
//            connection.setRequestProperty("Accept", "application/json");
//            connection.setRequestMethod("POST");
//
//            JSONObject cred = new JSONObject();
//            cred.put("user", userId);
//            cred.put("instrumentName", index);
//            cred.put("buyingPrice", data[i]);
//            cred.put("buyingDate", data[i+1]);
//            cred.put("quantity", quantity);
//            cred.put("sellingPrice", sellingPrice);
//            cred.put("sellingDate", LocalDate.now());
//        }

        private boolean sellInstrument(Long userId, String name, Long quantity, Double sellingPrice) throws IOException {

            String request = "http://localhost:8080/v1/instrument/sell?userId=" + userId + "&name=" + name + "&quantity=" + quantity + "&price=" + sellingPrice;
            URL url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String message = reader.readLine();
            boolean isSold = Boolean.valueOf(message);

            int responseCode = connection.getResponseCode();

            if (responseCode == 200 && isSold) {
                LOGGER.info("Instrument was sold, user " + userDto.getLogin() + ", instrument " + name + ", quantity/price " + quantity + "/" + sellingPrice);
                return true;
            } else {
                LOGGER.warn("Selling instrument failed, response code " + responseCode);
                return false;
            }
        }

//        private String[] getListOfInstruments(Long userId, String index) throws IOException {
//            String path = "http://localhost:8080/v1/instrument/showOnlyOne?userId=" + userId +"&index=" + index;
//            URL url = new URL(path);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String line;
//            StringBuffer buffer = new StringBuffer();
//            while ((line = reader.readLine()) != null) {
//                buffer.append(line);
//            }
//            String response = buffer.toString();
//            response = response.replace("{","");
//            response = response.replace("}","");
//            response = response.replace("[","");
//            response = response.replace("]","");
//            response = response.replace("\"","");
//            String[] array = response.split(",");
//            ArrayList<String> data = new ArrayList<>();
//            for(String element: array) {
//                String[] ar = element.split(":");
//                data.add(ar[1]);
//            }
//
//            String[] buyDate = new String[data.size()/3];
//            int x = 0;
//            for(int i = 0; i<data.size(); i+=6) {
//                buyDate[x] = data.get(i+4);
//                x++;
//                buyDate[x] = data.get(i+5);
//                x++;
//            }
//
//        return buyDate;
//
//
//        }

    }

    class CancelButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sellingFrame.setVisible(false);
        }
    }



}
