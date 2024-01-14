package com.carloscorp.mybotboot.services.action.contract.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CursorModelTO {

    private Robot robot;
    private boolean rightClick;
    private int delay;
    private List<CoordinatesTO> coordinates;
}
