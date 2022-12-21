package com.ansdoship.a3wt.graphics;

import com.ansdoship.a3wt.bundle.A3ExtensiveBundle;

public interface A3Line extends A3Shape<A3Line> {

    float getStartX();
    float getStartY();
    float getEndX();
    float getEndY();
    A3Point getStartPos();
    void getStartPos(final A3Point pos);
    A3Point getEndPos();
    void getEndPos(final A3Point pos);

    A3Line setStartX(final float startX);
    A3Line setStartY(final float startY);
    A3Line setEndX(final float endX);
    A3Line setEndY(final float endY);
    A3Line setStartPos(final A3Point pos);
    A3Line setEndPos(final A3Point pos);

    void set(final float startX, final float startY, final float endX, final float endY);
    void set(final A3Point startPos, final A3Point endPos);

    String KEY_START_X = "startX";
    String KEY_START_Y = "startY";
    String KEY_END_X = "endX";
    String KEY_END_Y = "endY";

    @Override
    default void save(final A3ExtensiveBundle.Saver saver) {
        saver.putFloat(KEY_START_X, getStartX());
        saver.putFloat(KEY_START_Y, getStartY());
        saver.putFloat(KEY_END_X, getEndX());
        saver.putFloat(KEY_END_Y, getEndY());
    }

    @Override
    default void restore(final A3ExtensiveBundle.Restorer restorer) {
        set(restorer.getFloat(KEY_START_X, 0), restorer.getFloat(KEY_START_Y, 0),
                restorer.getFloat(KEY_END_X, 0), restorer.getFloat(KEY_END_Y, 0));
    }
    
}
