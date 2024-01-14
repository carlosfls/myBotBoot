package com.carloscorp.mybotboot.services.action;

import com.carloscorp.mybotboot.services.action.contract.to.CoordinatesTO;
import com.carloscorp.mybotboot.services.action.contract.to.CursorModelTO;

import java.awt.*;
import java.util.List;

public interface GenericActionService {

    /**
     * Write a text in input selected
     *
     * @param robot    Robot
     * @param text     String
     * @param enterKey boolean
     */
    void writeInputText(Robot robot, String text, boolean enterKey, int delay);


    /**
     * Go to a position in the screen and click primary
     *
     * @param robot Robot
     * @param pos   Coordinates
     */
    void moveCursorAndClick(Robot robot, CoordinatesTO pos, boolean rightClick);

    /**
     * Go to a position in the screen and click primary for several locations
     */
    void moveCursorAndClickLoop(CursorModelTO to);

    /**
     * Move arrow and amount of time
     *
     * @param robot     Robot
     * @param direction int
     * @param amount    int
     */
    void moveArrowAndEnter(Robot robot, int direction, int amount);

    /**
     * press a combination of 2 keys for a key combination
     *
     * @param robot {@link Robot} the bot
     * @param key1  {@link Integer} the first key
     * @param key2  {@link Integer} the second key
     * @param delay {@link Integer} the delay between the key press and release
     */
    void pressCombine2Keys(Robot robot, int key1, int key2, int delay);
    /**
     * press a combination of 3 keys for a key combination
     *
     * @param robot {@link Robot} the bot
     * @param keys  {@link List} the list of keys
     * @param delay {@link Integer} the delay between the key press and release
     */
    void pressCombineKeys(Robot robot, List<Integer> keys, int delay);

    /**
     * Cycle of amount of same key
     *
     * @param robot  Robot
     * @param key    int
     * @param amount int
     */
    void keyCycle(Robot robot, int key, int amount);

}
