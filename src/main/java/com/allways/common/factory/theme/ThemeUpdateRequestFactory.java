package com.allways.common.factory.theme;

import com.allways.domain.theme.dto.ThemeUpdateRequest;

public class ThemeUpdateRequestFactory {
    public static ThemeUpdateRequest createThemeUpdateRequest() {
        return new ThemeUpdateRequest(
                "updateThemeName",
                "updateImageUrl");
    }

    public static ThemeUpdateRequest createThemeUpdateRequest(
            String updateThemeName,
            String updateImageUrl) {
        return new ThemeUpdateRequest(
                updateThemeName,
                updateImageUrl);
    }
}
