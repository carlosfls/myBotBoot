package com.carloscorp.mybotboot.services.action.impl;

import com.carloscorp.mybotboot.services.action.contract.to.CoordinatesTO;
import com.carloscorp.mybotboot.services.action.contract.to.CursorModelTO;
import com.carloscorp.mybotboot.services.action.GenericActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import static java.awt.event.KeyEvent.VK_ALT;
import static java.awt.event.KeyEvent.VK_NUMPAD0;
import static java.awt.event.KeyEvent.VK_NUMPAD1;
import static java.awt.event.KeyEvent.VK_NUMPAD2;
import static java.awt.event.KeyEvent.VK_NUMPAD3;
import static java.awt.event.KeyEvent.VK_NUMPAD4;
import static java.awt.event.KeyEvent.VK_NUMPAD5;
import static java.awt.event.KeyEvent.VK_NUMPAD6;
import static java.awt.event.KeyEvent.VK_NUMPAD7;
import static java.awt.event.KeyEvent.VK_NUMPAD8;
import static java.awt.event.KeyEvent.VK_NUMPAD9;

@Service
@RequiredArgsConstructor
public class GenericActionsServiceImpl implements GenericActionService {

    @Override
    public void writeInputText(Robot robot, String text, boolean enterKey, int delay) {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            boolean special = validateEspecialCharacters(robot,c);
            if (!special){
                int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                if (Character.isLetter(c) && Character.isUpperCase(c)) {
                    typeUpperCase(robot, keyCode);
                } else {
                    type(robot, keyCode);
                }
            }
            robot.delay(50);
        }

        if (enterKey) {
            robot.delay(delay);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
    }

    @Override
    public void moveCursorAndClick(Robot robot, CoordinatesTO pos, boolean rightClick) {
        robot.mouseMove(pos.getX(), pos.getY());
        if (rightClick) {
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
        } else {
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }
    }

    @Override
    public void moveCursorAndClickLoop(CursorModelTO to) {
        to.getCoordinates().forEach(coordinate -> {
            moveCursorAndClick(to.getRobot(), coordinate, to.isRightClick());
            to.getRobot().delay(to.getDelay());
        });
    }

    @Override
    public void moveArrowAndEnter(Robot robot, int direction, int amount) {
        for (int i = 0; i < amount; i++) {
            robot.keyPress(direction);
            robot.keyRelease(direction);
            robot.delay(50);
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    @Override
    public void pressCombine2Keys(Robot robot, int key1, int key2, int delay) {
        robot.keyPress(key1);
        robot.keyPress(key2);
        robot.delay(delay);
        robot.keyRelease(key1);
        robot.keyRelease(key2);
    }

    @Override
    public void pressCombineKeys(Robot robot, List<Integer> keys, int delay) {
        keys.forEach(robot::keyPress);
        robot.delay(delay);
        keys.forEach(robot::keyRelease);
    }

    @Override
    public void keyCycle(Robot robot, int key, int amount) {
        for (int i = 0; i < amount; i++) {
            robot.keyPress(key);
            robot.keyRelease(key);
            robot.delay(50);
        }
    }

    private void typeUpperCase(Robot robot, int keyCode) {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);
        robot.keyRelease(KeyEvent.VK_SHIFT);
    }

    private void type(Robot robot, int keyCode) {
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);
    }

    private boolean validateEspecialCharacters(Robot robot, Character characterKey) {
        return switch (characterKey) {
            case '!' -> altNumpad(robot, "33");
            case '"' -> altNumpad(robot, "34");
            case '#' -> altNumpad(robot, "35");
            case '$' -> altNumpad(robot, "36");
            case '%' -> altNumpad(robot, "37");
            case '&' -> altNumpad(robot, "38");
            case '\'' -> altNumpad(robot, "39");
            case '(' -> altNumpad(robot, "40");
            case ')' -> altNumpad(robot, "41");
            case '*' -> altNumpad(robot, "42");
            case '+' -> altNumpad(robot, "43");
            case ',' -> altNumpad(robot, "44");
            case '-' -> altNumpad(robot, "45");
            case '.' -> altNumpad(robot, "46");
            case '/' -> altNumpad(robot, "47");
            case '<' -> altNumpad(robot, "60");
            case '=' -> altNumpad(robot, "61");
            case '>' -> altNumpad(robot, "62");
            case '?' -> altNumpad(robot, "63");
            case '@' -> altNumpad(robot, "64");
            case '[' -> altNumpad(robot, "91");
            case '\\' -> altNumpad(robot, "92");
            case ']' -> altNumpad(robot, "93");
            case '^' -> altNumpad(robot, "94");
            case '_' -> altNumpad(robot, "95");
            case '`' -> altNumpad(robot, "96");
            case '{' -> altNumpad(robot, "123");
            case '|' -> altNumpad(robot, "124");
            case '}' -> altNumpad(robot, "125");
            case '~' -> altNumpad(robot, "126");
            default -> false;
        };
    }

    private boolean altNumpad(Robot robot, String numpadCodes) {
        if (numpadCodes == null || !numpadCodes.matches("^\\d+$")) {
            return false;
        }
        robot.keyPress(VK_ALT);
        for (char charater : numpadCodes.toCharArray()) {
            int NUMPAD_KEY = getNumpad(charater);
            if (NUMPAD_KEY != -1) {
                robot.keyPress(NUMPAD_KEY);
                robot.keyRelease(NUMPAD_KEY);
            }
        }
        robot.keyRelease(VK_ALT);
        return true;
    }

    private int getNumpad(char numberChar) {
        return switch (numberChar) {
            case '0' -> VK_NUMPAD0;
            case '1' -> VK_NUMPAD1;
            case '2' -> VK_NUMPAD2;
            case '3' -> VK_NUMPAD3;
            case '4' -> VK_NUMPAD4;
            case '5' -> VK_NUMPAD5;
            case '6' -> VK_NUMPAD6;
            case '7' -> VK_NUMPAD7;
            case '8' -> VK_NUMPAD8;
            case '9' -> VK_NUMPAD9;
            default -> -1;
        };
    }

}
