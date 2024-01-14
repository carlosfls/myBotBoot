package com.carloscorp.mybotboot.config;

import com.carloscorp.mybotboot.services.ws.contract.to.WsPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.lang.reflect.Type;

@Slf4j
public class ResetApp implements StompFrameHandler {

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return WsPayload.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("Resetting the worker");
    }
}
