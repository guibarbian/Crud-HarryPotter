package com.db.Crud_HarryPotter.Model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Entity
@EqualsAndHashCode(callSuper = true)
public class BruxoSonserina extends Bruxo implements Magia {

    public BruxoSonserina() {
        super();
    }

    @Override
    public String lancarFeitico() {
        return "Serpensortia! - O bruxo da Sonserina lançou seu feitiço!";
    }
}
