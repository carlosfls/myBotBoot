package com.carloscorp.mybotboot.services.ws.contract.to;

import com.carloscorp.mybotboot.services.task.TaskTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WsPayload {

    private String appId;
    private TaskTO payload;
}
