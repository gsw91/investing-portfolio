package com.invest.Gui;

import com.invest.Gui.frames.LogInFrame;

public class GuiConfiguration {

    protected void run() {
        LogInFrame logInFrame = new LogInFrame();
        logInFrame.run();

//        UserFrame userFrame = new UserFrame(new UserDto(421L, "admin", "admin", "g.wojcik@vp.pl"));
//        userFrame.OpenUserFrame();

    }
}
