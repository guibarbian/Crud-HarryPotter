package com.db.Crud_HarryPotter.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BruxoRequestDTO {
    private String nome;
    private String casa;
}
