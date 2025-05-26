package com.db.Crud_HarryPotter.Controller;

import com.db.Crud_HarryPotter.dto.BruxoRequestDTO;
import com.db.Crud_HarryPotter.dto.BruxoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BruxoController {

    @Operation(summary = "Get all bruxos",
                description = "Retorna todos os bruxos do banco",
    responses = {
            @ApiResponse(responseCode = "200", description = "Lista de bruxos retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<List<BruxoResponseDTO>> getAllBruxos();

    @Operation(summary = "Create Bruxo",
            description = "Cria e salva um bruxo no banco",
            parameters = {
                @Parameter(name = "requestDTO", description = "Objeto contendo os dados do bruxo a ser criado")
            },
            responses = {
                    @ApiResponse(responseCode = "201", description = "Criou o bruxo com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    ResponseEntity<BruxoResponseDTO> createBruxo(BruxoRequestDTO requestDTO);

    @Operation(summary = "Update Bruxo",
            description = "Atualiza um bruxo do banco",
            parameters = {
                    @Parameter(name = "id", description = "ID do bruxo a ser atualizado"),
                    @Parameter(name = "requestDTO", description = "Objeto contendo os dados do bruxo a ser criado")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Atualizou o bruxo com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "404", description = "Bruxo não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    ResponseEntity<BruxoResponseDTO> updateBruxo(Long id, BruxoRequestDTO requestDTO);

    @Operation(summary = "Delete Bruxo",
            description = "Deleta um bruxo do banco",
            parameters = {
                    @Parameter(name = "id", description = "ID do bruxo a ser deletado")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Deletou o bruxo com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Bruxo não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    ResponseEntity<String> deleteBruxo(Long id);
}
