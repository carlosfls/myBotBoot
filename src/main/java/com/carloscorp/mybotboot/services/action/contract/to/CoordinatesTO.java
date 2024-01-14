package com.carloscorp.mybotboot.services.action.contract.to;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CoordinatesTO {

    private int x;

    private int y;

    public CoordinatesTO(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
