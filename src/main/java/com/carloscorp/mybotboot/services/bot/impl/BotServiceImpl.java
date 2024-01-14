package com.carloscorp.mybotboot.services.bot.impl;

import com.carloscorp.mybotboot.services.action.SystemActionService;
import com.carloscorp.mybotboot.services.bot.BotService;
import com.carloscorp.mybotboot.services.connection.ConnectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BotServiceImpl implements BotService {

    private final SystemActionService systemActionService;

    @Override
    public void connect() {
        if (ConnectionService.isRunning()){
            log.info("Connected");
        }else {
            log.info("Connecting");
            ConnectionService.connect();
        }
    }

    @Override
    public void typeText(String text){
        systemActionService.minimizeAll();
        systemActionService.typeOnNotepad(text);
    }

}
