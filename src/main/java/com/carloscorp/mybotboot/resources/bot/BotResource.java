package com.carloscorp.mybotboot.resources.bot;

import com.carloscorp.mybotboot.services.bot.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bot")
@RequiredArgsConstructor
public class BotResource {

    private final BotService botService;

    @PostMapping("/connect")
    public ResponseEntity<Void> connect(){
        botService.connect();
        return ResponseEntity.ok().build();
    }
}
