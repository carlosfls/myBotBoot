package com.carloscorp.mybotboot.services.action.impl;

import com.carloscorp.mybotboot.services.action.MouseActionService;
import com.carloscorp.mybotboot.services.action.contract.to.CoordinatesTO;

import java.awt.*;

public class MouseActionServiceImpl implements MouseActionService {

    public CoordinatesTO getPointerLocation(){
        Point p = MouseInfo.getPointerInfo().getLocation();
        return new CoordinatesTO(p.x,p.y);
    }
}
