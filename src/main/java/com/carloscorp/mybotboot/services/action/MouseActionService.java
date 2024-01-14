package com.carloscorp.mybotboot.services.action;

import com.carloscorp.mybotboot.services.action.contract.to.CoordinatesTO;

public interface MouseActionService {

    CoordinatesTO getPointerLocation();
}
