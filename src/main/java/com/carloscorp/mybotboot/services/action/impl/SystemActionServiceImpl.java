package com.carloscorp.mybotboot.services.action.impl;

import com.carloscorp.mybotboot.services.action.GenericActionService;
import com.carloscorp.mybotboot.services.action.SystemActionService;
import com.carloscorp.mybotboot.services.action.contract.to.CoordinatesTO;
import com.carloscorp.mybotboot.services.action.contract.to.CursorModelTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemActionServiceImpl implements SystemActionService {

    private final Robot bot;
    private final GenericActionService genericActionService;

    @Override
    public void minimizeAll(){
        genericActionService.pressCombineKeys(bot,List.of(KeyEvent.VK_WINDOWS,KeyEvent.VK_M),0);
    }

    @Override
    public void emptyRecycleBin(){
        bot.delay(500);
        genericActionService.moveCursorAndClick(bot,new CoordinatesTO(47,147),true);
        bot.delay(500);
        genericActionService.moveArrowAndEnter(bot, KeyEvent.VK_DOWN,1);
        bot.delay(500);
        genericActionService.pressCombine2Keys(bot,KeyEvent.VK_CONTROL,KeyEvent.VK_A,0);
        bot.delay(1000);
        genericActionService.moveCursorAndClick(bot,new CoordinatesTO(652,112),false);
        bot.delay(500);
        genericActionService.moveCursorAndClickLoop(new CursorModelTO(bot,false,500,
                List.of(new CoordinatesTO(746,387),
                        new CoordinatesTO(758,451),
                        new CoordinatesTO(763,432)
                ))
        );
        bot.delay(500);
        genericActionService.pressCombine2Keys(bot,KeyEvent.VK_ALT,KeyEvent.VK_F4,0);
        bot.delay(500);
    }

    @Override
    public void typeOnNotepad(String text){
        genericActionService.pressCombineKeys(bot,List.of(KeyEvent.VK_WINDOWS,KeyEvent.VK_R),500);
        bot.delay(500);
        genericActionService.writeInputText(bot,"notepad",true,500);
        bot.delay(500);
        genericActionService.moveCursorAndClick(bot,new CoordinatesTO(508,310),false);
        bot.delay(500);
        genericActionService.writeInputText(bot,text,false,500);
    }

    @Override
    public void selectActiveWindows(int tabAmount){
        bot.keyPress(KeyEvent.VK_ALT);
        bot.keyPress(KeyEvent.VK_TAB);
        bot.delay(500);
        genericActionService.keyCycle(bot,KeyEvent.VK_TAB,tabAmount);
        bot.delay(500);
        bot.keyRelease(KeyEvent.VK_ALT);
        bot.keyRelease(KeyEvent.VK_TAB);
    }
}
