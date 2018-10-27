package com.xdjwan.wan.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by jx on 2018/5/3.
 */

public enum EcIcons implements Icon {
    icon_down('\ue619');
    private char character;


    EcIcons(char c) {
        character = c;
    }


    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
