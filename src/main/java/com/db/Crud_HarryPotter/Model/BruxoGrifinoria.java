package com.db.Crud_HarryPotter.Model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Entity
@EqualsAndHashCode(callSuper = true)
public class BruxoGrifinoria extends Bruxo implements Magia{

    public BruxoGrifinoria() {
        super();
    }

    @Override
    public String lancarFeitico() {
        return "Expelliarmus! - O bruxo da Grifinória lançou seu feitiço!";
    }
}
