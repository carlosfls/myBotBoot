package com.carloscorp.mybotboot.utils;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
public class ImageProcess {

    public ImageProcess() { // Empty constructor
    }

    /**
     * Take screen shoot and compare with the image base take the tolerance
     *
     * @param robot       Robot
     * @param base        BufferedImage
     * @param captureRect Rectangle
     * @param tolerance   float
     * @return boolean
     */
    public boolean screenCapturedAndWaitForPercentMatch(Robot robot,
                                                        BufferedImage base,
                                                        Rectangle captureRect,
                                                        float tolerance,
                                                        int timer) {
        boolean flag = true;
        do {
            log.info("Creating screen capture");
            BufferedImage screenCapturedImage = robot.createScreenCapture(captureRect);
            if (compareImagesWithTolerance(base, screenCapturedImage, tolerance)) {
                flag = false;
            }
            robot.delay(timer);
        } while (flag);
        return true;
    }

    public void saveScreenShootImage(BufferedImage screenCapturedImage){
        File outputfile = new File("screenshot.png");
        try {
            ImageIO.write(screenCapturedImage, "png", outputfile);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Compare two images and check if are equals
     *
     * @param base     BufferedImage
     * @param captured BufferedImage
     * @return boolean
     */
    private boolean compareImages(BufferedImage base, BufferedImage captured) {
        // Compare the images pixel by pixel
        boolean imagesEqual = true;
        for (int x = 0; x < base.getWidth(); x++) {
            for (int y = 0; y < base.getHeight(); y++) {
                int rgb1 = base.getRGB(x, y);
                int rgb2 = captured.getRGB(x, y);
                if (rgb1 != rgb2) {
                    imagesEqual = false;
                    break;
                }
            }
        }
        return imagesEqual;
    }

    /**
     * Compare two images and calculate the error tolerance percent
     *
     * @param base      BufferedImage
     * @param captured  BufferedImage
     * @param tolerance float
     * @return boolean
     */
    private boolean compareImagesWithTolerance(BufferedImage base, BufferedImage captured, float tolerance) {
        // Compare the images pixel by pixel
        int total = base.getHeight() * base.getWidth();
        int fail = 0;
        for (int x = 0; x < base.getWidth(); x++) {
            for (int y = 0; y < base.getHeight(); y++) {
                int rgb1 = base.getRGB(x, y);
                int rgb2 = captured.getRGB(x, y);
                if (rgb1 != rgb2) {
                    fail++;
                }
            }
        }
        float percent = (float) (fail * 100) / total;
        log.info("total: " + total + " | fail: " + fail + " | percent: " + percent);
        return percent <= tolerance;
    }
}
