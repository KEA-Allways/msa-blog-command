package com.allways.common.factory.theme;

import com.allways.domain.theme.entity.Theme;

public class ThemeFactory {
    public static Theme createTheme() {
        return new Theme(
                "themeName",
                1L,
                1L);
    }

    public static Theme createTheme(
            String themeName,
            Long themeOrder,
            Long userSeq) {
        return new Theme(
                themeName,
                themeOrder,
                userSeq);
    }
}
