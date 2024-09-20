package com.eco.base.mapper;

import com.eco.base.dto.ColorDTO;
import com.eco.base.entity.Color;

public class ColorMapper {

    public static Color entityToDto(ColorDTO dto) {
        return Color.builder()
                .colorName(dto.getColorName())
                .build();
    }

    public static ColorDTO dtoToEntity(Color entity) {
        return ColorDTO.builder()
                .colorName(entity.getColorName())
                .build();
    }
}
