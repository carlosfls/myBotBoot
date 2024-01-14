package com.carloscorp.mybotboot.services.action;

public interface SystemActionService {

    void minimizeAll();

    void emptyRecycleBin();

    void typeOnNotepad(String text);

    void selectActiveWindows(int tabAmount);
}
