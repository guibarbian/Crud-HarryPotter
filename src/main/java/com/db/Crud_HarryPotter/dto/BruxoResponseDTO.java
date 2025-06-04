package com.db.Crud_HarryPotter.dto;

import lombok.Builder;

@Builder
public record BruxoResponseDTO(
        String nome,
        String casa
) {
}
