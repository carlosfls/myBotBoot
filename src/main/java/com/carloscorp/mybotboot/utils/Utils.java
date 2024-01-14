package com.carloscorp.mybotboot.utils;

import java.awt.*;

public class Utils {

    public static Dimension getCenterOfScreen(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;

        return new Dimension(centerX,centerY);
    }
}
