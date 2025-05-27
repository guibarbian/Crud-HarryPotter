package com.db.Crud_HarryPotter.Unitarios;

import com.db.Crud_HarryPotter.Model.BruxoGrifinoria;
import com.db.Crud_HarryPotter.Model.BruxoSonserina;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BruxoModelTest {

    @Test
    public void testImprimirBruxoGrifinoria(){
        BruxoGrifinoria bruxoGrifinoria = BruxoGrifinoria.builder()
            .nome("Harry Potter").casa("Grifinoria").build();

        String resultado = "Nome: " + bruxoGrifinoria.getNome() + ", Casa: " + bruxoGrifinoria.getCasa();

        assertEquals(resultado, bruxoGrifinoria.mostrarInformacoes());
    }

    @Test
    public void testImprimirBruxoSonserina(){
        BruxoSonserina bruxoSonserina = BruxoSonserina.builder()
                .nome("Drako Malfoy").casa("Sonserina").build();

        String resultado = "Nome: " + bruxoSonserina.getNome() + ", Casa: " + bruxoSonserina.getCasa();

        assertEquals(resultado, bruxoSonserina.mostrarInformacoes());
    }

    @Test
    public void testLancarFeiticoGrifinoria(){
        BruxoGrifinoria bruxoGrifinoria = BruxoGrifinoria.builder()
                .nome("Harry Potter").casa("Grifinoria").build();

        String resultado = "Expelliarmus! - O bruxo da Grifinória lançou seu feitiço!";

        assertEquals(resultado, bruxoGrifinoria.lancarFeitico());
    }

    @Test
    public void testLancarFeiticoSonserina(){
        BruxoSonserina bruxoSonserina = BruxoSonserina.builder()
                .nome("Drako Malfoy").casa("Sonserina").build();

        String resultado = "Serpensortia! - O bruxo da Sonserina lançou seu feitiço!";

        assertEquals(resultado, bruxoSonserina.lancarFeitico());
    }
}
