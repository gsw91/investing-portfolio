package com.invest.Gui.frames;

import com.invest.dtos.InstrumentDto;
import com.invest.dtos.UserDto;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.util.List;

public class SellInstrumentFrame {

    private final static Logger LOGGER = Logger.getLogger(SellInstrumentFrame.class);
    private JFrame userFrame;
    private UserDto userDto;
    private Boolean visibility;
    private List<InstrumentDto> instruments;

    public SellInstrumentFrame(JFrame userFrame, UserDto userDto, Boolean visibility) {
        this.userFrame = userFrame;
        this.userDto = userDto;
        this.visibility = visibility;
    }

    public void openSellingWindow() {
        JFrame sellingFrame = new JFrame("Sell instrument");
        sellingFrame.setLocation(500,300);
        sellingFrame.setSize(300, 180);
        sellingFrame.setVisible(true);
    }



}
