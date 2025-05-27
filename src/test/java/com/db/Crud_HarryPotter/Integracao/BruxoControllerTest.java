package com.db.Crud_HarryPotter.Integracao;

import com.db.Crud_HarryPotter.Model.Bruxo;
import com.db.Crud_HarryPotter.Model.BruxoGrifinoria;
import com.db.Crud_HarryPotter.Repository.BruxoRepository;
import com.db.Crud_HarryPotter.dto.BruxoRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BruxoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BruxoRepository bruxoRepository;

    String baseUrl = "/api/v1/bruxos";

    @Test
    public void testGetAllBruxos() throws Exception{
        BruxoGrifinoria bruxoGrifinoria = BruxoGrifinoria.builder()
                        .nome("Harry Potter").casa("Grifinoria").build();

        bruxoRepository.save(bruxoGrifinoria);

        mockMvc.perform(get(baseUrl)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Harry Potter"));
    }

    @Test
    public void testCreateBruxoGrifionria() throws Exception{
        BruxoRequestDTO dto = BruxoRequestDTO.builder()
                .nome("Harry Potter").casa("Grifinoria").build();

        mockMvc.perform(post(baseUrl)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Harry Potter"))
                .andExpect(jsonPath("$.casa").value("Grifinoria"));
    }

    @Test
    public void testCreateBruxoSonserina() throws Exception{
        BruxoRequestDTO dto = BruxoRequestDTO.builder()
                .nome("Drako Malfoy").casa("Sonserina").build();

        mockMvc.perform(post(baseUrl)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Drako Malfoy"))
                .andExpect(jsonPath("$.casa").value("Sonserina"));
    }

    @Test
    void testUpdateBruxo() throws Exception{
        BruxoGrifinoria bruxoGrifinoria = BruxoGrifinoria.builder()
                .nome("Harry Potter").casa("Grifinoria").build();

        BruxoGrifinoria bruxoSalvo = bruxoRepository.save(bruxoGrifinoria);

        BruxoRequestDTO dto = BruxoRequestDTO.builder()
                .nome("Harry Potter Jr.").casa("Grifinoria").build();

        mockMvc.perform(put(baseUrl + "/" + bruxoSalvo.getId())
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Harry Potter Jr."))
                .andExpect(jsonPath("$.casa").value("Grifinoria"));
    }

    @Test
    void testDeleteBruxo() throws Exception{
        BruxoGrifinoria bruxoGrifinoria = BruxoGrifinoria.builder()
                .nome("Harry Potter").casa("Grifinoria").build();

        Bruxo bruxoSalvo = bruxoRepository.save(bruxoGrifinoria);

        mockMvc.perform(delete(baseUrl + "/" + bruxoSalvo.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
