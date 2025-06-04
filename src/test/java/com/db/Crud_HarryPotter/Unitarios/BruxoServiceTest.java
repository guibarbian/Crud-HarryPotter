package com.db.Crud_HarryPotter.Unitarios;

import com.db.Crud_HarryPotter.Exception.NotFoundException;
import com.db.Crud_HarryPotter.Model.Bruxo;
import com.db.Crud_HarryPotter.Model.BruxoGrifinoria;
import com.db.Crud_HarryPotter.Model.BruxoSonserina;
import com.db.Crud_HarryPotter.Repository.BruxoRepository;
import com.db.Crud_HarryPotter.Service.IMPL.BruxoServiceImpl;
import com.db.Crud_HarryPotter.dto.BruxoRequestDTO;
import com.db.Crud_HarryPotter.dto.BruxoResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BruxoServiceTest {

    @InjectMocks
    private BruxoServiceImpl bruxoService;

    @Mock
    private BruxoRepository bruxoRepository;

    BruxoGrifinoria bruxo1 = BruxoGrifinoria.builder().id(1L).nome("Harry Potter").casa("Grifinoria").build();
    BruxoSonserina bruxo2 = BruxoSonserina.builder().id(2L).nome("Drako Malfoy").casa("Sonserina").build();


    @Test
    public void testGetAllBruxos(){
        List<Bruxo> bruxos = List.of(bruxo1, bruxo2);

        when(bruxoRepository.findAll()).thenReturn(bruxos);

        List<BruxoResponseDTO> result = bruxoService.getAllBruxos();

        assertEquals(2, result.size());
        assertEquals("Harry Potter", result.get(0).getNome());
    }

    @Test
    public void CreateSonserinaBruxo(){
        BruxoRequestDTO requestDTO = new BruxoRequestDTO("Drako Malfoy", "Sonserina");
        BruxoSonserina bruxoAux = bruxo2;
        bruxoAux.setId(null);

        when(bruxoRepository.save(bruxoAux)).thenReturn(bruxo2);

        BruxoResponseDTO response = bruxoService.createBruxo(requestDTO);

        assertEquals("Drako Malfoy", response.getNome());
        assertEquals("Sonserina", response.getCasa());
        assertEquals("BruxoSonserina", bruxoAux.getClass().getSimpleName());
    }

    @Test
    public void CreateGrifinoriaBruxo(){
        BruxoRequestDTO requestDTO = new BruxoRequestDTO("Harry Potter", "Grifinoria");
        BruxoGrifinoria bruxoAux = bruxo1;
        bruxoAux.setId(null);

        when(bruxoRepository.save(bruxoAux)).thenReturn(bruxo1);

        BruxoResponseDTO response = bruxoService.createBruxo(requestDTO);

        assertEquals("Harry Potter", response.getNome());
        assertEquals("Grifinoria", response.getCasa());
        assertEquals("BruxoGrifinoria", bruxoAux.getClass().getSimpleName());
    }

    @Test
    public void ErrorCreatingBruxo(){
        BruxoRequestDTO requestDTO = new BruxoRequestDTO("Hermione Granger", "Hufflepuff");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bruxoService.createBruxo(requestDTO);
        });

        String message = exception.getMessage();

        assertTrue(message.contains("Casa não reconhecida: Hufflepuff"));
    }

    @Test
    public void testUpdateBruxo() {
        BruxoRequestDTO requestDTO = new BruxoRequestDTO("Harry Potter", "Grifinoria");
        bruxo1.setNome(requestDTO.nome());
        bruxo1.setCasa(requestDTO.casa());

        when(bruxoRepository.findById(1L)).thenReturn(java.util.Optional.of(bruxo1));
        when(bruxoRepository.save(bruxo1)).thenReturn(bruxo1);

        BruxoResponseDTO response = bruxoService.updateBruxo(1L, requestDTO);

        assertEquals("Harry Potter", response.getNome());
        assertEquals("Grifinoria", response.getCasa());
    }

    @Test
    public void testNotFoundUpdateBruxo() {
        BruxoRequestDTO requestDTO = new BruxoRequestDTO("Hermione Granger", "Grifinoria");

        when(bruxoRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            bruxoService.updateBruxo(1L, requestDTO);
        });

        String message = exception.getMessage();
        assertTrue(message.contains("Bruxo com ID: 1 não encontrado."));
    }

    @Test
    public void testUpdateBruxoCasaInvalida() {
        BruxoRequestDTO requestDTO = new BruxoRequestDTO("Hermione Granger", "Hufflepuff");

        when(bruxoRepository.findById(1L)).thenReturn(java.util.Optional.of(bruxo1));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bruxoService.updateBruxo(1L, requestDTO);
        });

        String message = exception.getMessage();
        assertTrue(message.contains("Casa não reconhecida: Hufflepuff"));
    }

    @Test
    public void testDeleteBruxo(){
        when(bruxoRepository.findById(1L)).thenReturn(java.util.Optional.of(bruxo1));

        bruxoService.deleteBruxo(1L);

        verify(bruxoRepository).deleteById(1L);
    }

    @Test
    public void testNotFoundWhenDelete(){
        when(bruxoRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            bruxoService.deleteBruxo(1L);
        });

        String message = exception.getMessage();
        assertTrue(message.contains("Bruxo com ID: 1 não encontrado."));
    }
}
