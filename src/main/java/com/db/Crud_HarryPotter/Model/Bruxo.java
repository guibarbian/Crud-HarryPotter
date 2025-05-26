package com.db.Crud_HarryPotter.Model;

import com.db.Crud_HarryPotter.dto.BruxoResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Table(name = "bruxos")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Bruxo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "casa", nullable = false)
    private String casa;

    public String mostrarInformacoes(){
        return "Nome: " + nome + ", Casa: " + casa;
    }

    public BruxoResponseDTO toDto(){
        return BruxoResponseDTO.builder()
                .nome(this.nome)
                .casa(this.casa).build();
    }
}
