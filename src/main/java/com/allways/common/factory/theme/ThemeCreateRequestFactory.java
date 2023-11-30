package com.allways.common.factory.theme;

import com.allways.domain.theme.dto.ThemeCreateRequest;

public class ThemeCreateRequestFactory {
    public static ThemeCreateRequest createThemeCreateRequest() {
        return new ThemeCreateRequest(
                "themeName",
                "imageUrl");
    }

    public static ThemeCreateRequest createThemeCreateRequest(
            String themeName,
            String imageUrl) {
        return new ThemeCreateRequest(
                themeName,
                imageUrl);
    }
}
