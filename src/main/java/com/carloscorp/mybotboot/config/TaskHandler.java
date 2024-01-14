package com.carloscorp.mybotboot.config;

import com.carloscorp.mybotboot.services.task.TaskTO;
import com.carloscorp.mybotboot.services.ws.contract.to.WsPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.lang.reflect.Type;

@Slf4j
public class TaskHandler implements StompFrameHandler {

    public TaskHandler() {
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return WsPayload.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("Received payload task");
        WsPayload wsPayload = (WsPayload) payload;
        TaskTO task = wsPayload.getPayload();
        log.info(task.toString());
    }
}
