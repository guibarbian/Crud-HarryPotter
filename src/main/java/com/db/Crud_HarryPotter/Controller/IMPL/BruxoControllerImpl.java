package com.db.Crud_HarryPotter.Controller.IMPL;

import com.db.Crud_HarryPotter.Controller.BruxoController;
import com.db.Crud_HarryPotter.Service.BruxoService;
import com.db.Crud_HarryPotter.dto.BruxoRequestDTO;
import com.db.Crud_HarryPotter.dto.BruxoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bruxos")
public class BruxoControllerImpl implements BruxoController {

    private final BruxoService bruxoService;

    @Override
    @GetMapping
    public ResponseEntity<List<BruxoResponseDTO>> getAllBruxos() {
        return ResponseEntity.ok(bruxoService.getAllBruxos());
    }

    @Override
    @PostMapping
    public ResponseEntity<BruxoResponseDTO> createBruxo(@RequestBody BruxoRequestDTO requestDTO) {
        return ResponseEntity.status(201).body(bruxoService.createBruxo(requestDTO));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<BruxoResponseDTO> updateBruxo(@PathVariable Long id, @RequestBody BruxoRequestDTO requestDTO) {
        return ResponseEntity.ok(bruxoService.updateBruxo(id, requestDTO));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBruxo(@PathVariable Long id) {
        bruxoService.deleteBruxo(id);
        return ResponseEntity.ok(("Bruxo deletado com sucesso"));
    }
}
