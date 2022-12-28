package com.moth.game.objects;

import com.moth.game.enums.ID;

import java.awt.*;

public interface GameObject {
    void tick();
    void render(Graphics g);
    ID getId();
    Rectangle getBounds();

}
