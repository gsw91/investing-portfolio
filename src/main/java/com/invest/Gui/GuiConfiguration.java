package com.invest.Gui;

import com.invest.Gui.frames.SignUpFrame;
import com.invest.Gui.frames.UserFrame;

public class GuiConfiguration {

    protected void run() {
//        SignUpFrame frame = new SignUpFrame();
//        frame.openSignUpFrame();

        UserFrame userFrame = new UserFrame();
        userFrame.OpenUserFrame("gw001");

    }
}
