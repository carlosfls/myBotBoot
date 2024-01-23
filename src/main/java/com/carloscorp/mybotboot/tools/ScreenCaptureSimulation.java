package com.carloscorp.mybotboot.tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ScreenCaptureSimulation {

    public static void main(String[] args) {

        try {
            // Capture the screen region containing text
            Rectangle captureRect = new Rectangle(690, 397, 180, 25);
            Robot robot = new Robot();
            BufferedImage screenCapturedImage = robot.createScreenCapture(captureRect);

            // Save the captured image to a file
            File outputfile = new File("screenshot.png");
            ImageIO.write(screenCapturedImage, "png", outputfile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
