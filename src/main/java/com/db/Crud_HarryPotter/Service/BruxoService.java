package com.db.Crud_HarryPotter.Service;

import com.db.Crud_HarryPotter.dto.BruxoRequestDTO;
import com.db.Crud_HarryPotter.dto.BruxoResponseDTO;

import java.util.List;

public interface BruxoService {

    List<BruxoResponseDTO> getAllBruxos();

    BruxoResponseDTO createBruxo(BruxoRequestDTO dto);

    BruxoResponseDTO updateBruxo(Long id, BruxoRequestDTO dto);

    void deleteBruxo(Long id);
}
