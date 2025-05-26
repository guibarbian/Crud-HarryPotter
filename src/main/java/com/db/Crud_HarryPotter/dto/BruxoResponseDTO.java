package com.db.Crud_HarryPotter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BruxoResponseDTO {
    private String nome;
    private String casa;
}
