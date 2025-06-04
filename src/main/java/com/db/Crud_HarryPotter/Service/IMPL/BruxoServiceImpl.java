package com.db.Crud_HarryPotter.Service.IMPL;

import com.db.Crud_HarryPotter.Exception.IllegalArgumentException;
import com.db.Crud_HarryPotter.Exception.NotFoundException;
import com.db.Crud_HarryPotter.Model.Bruxo;
import com.db.Crud_HarryPotter.Model.BruxoGrifinoria;
import com.db.Crud_HarryPotter.Model.BruxoSonserina;
import com.db.Crud_HarryPotter.Repository.BruxoRepository;
import com.db.Crud_HarryPotter.Service.BruxoService;
import com.db.Crud_HarryPotter.dto.BruxoRequestDTO;
import com.db.Crud_HarryPotter.dto.BruxoResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BruxoServiceImpl implements BruxoService {

    private final BruxoRepository bruxoRepository;

    @Override
    public List<BruxoResponseDTO> getAllBruxos() {
        log.info("Buscando todos os bruxos");
        return bruxoRepository.findAll().stream()
                .map(Bruxo::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BruxoResponseDTO createBruxo(BruxoRequestDTO dto) {
        log.info("Criando um novo bruxo: {}", dto.nome());
        Bruxo novoBruxo = switch (dto.casa()){
            case "Grifinoria" -> new BruxoGrifinoria();
            case "Sonserina" -> new BruxoSonserina();
            default -> {
                log.warn("Casa não reconhecida: {}", dto.casa());
                throw new IllegalArgumentException("Casa não reconhecida: " + dto.casa());
            }
        };

        novoBruxo.setNome(dto.nome());
        novoBruxo.setCasa(dto.casa());

        log.info("Sucesso em criar o bruxo {}", novoBruxo.getNome());
        Bruxo savedBruxo = bruxoRepository.save(novoBruxo);

        log.info("Bruxo " + savedBruxo.getNome() + " salvo com o ID " + savedBruxo.getId());
        return savedBruxo.toDto();
    }

    @Override
    public BruxoResponseDTO updateBruxo(Long id, BruxoRequestDTO dto) {
        log.info("Buscando por bruxo com ID: {}", id);
        Optional<Bruxo> optionalBruxo = bruxoRepository.findById(id);

        if(optionalBruxo.isEmpty()){
            throw new NotFoundException("Bruxo com ID: " + id + " não encontrado.");
        }

        log.info("Criando um novo bruxo: {}", dto.nome());
        Bruxo bruxoAtualizado = switch (dto.casa()){
            case "Grifinoria" -> new BruxoGrifinoria();
            case "Sonserina" -> new BruxoSonserina();
            default -> {
                log.warn("Casa não reconhecida: {}", dto.casa());
                throw new IllegalArgumentException("Casa não reconhecida: " + dto.casa());
            }
        };

        bruxoAtualizado.setId(id);
        bruxoAtualizado.setNome(dto.nome());
        bruxoAtualizado.setCasa(dto.casa());

        Bruxo savedBruxo = bruxoRepository.save(bruxoAtualizado);

        log.info("Bruxo com ID: {} atualizado com sucesso.", id);
        return savedBruxo.toDto();
    }

    @Override
    public void deleteBruxo(Long id) {
        log.info("Buscando por bruxo com ID: {}", id);
        Optional<Bruxo> optionalBruxo = bruxoRepository.findById(id);

        if(optionalBruxo.isEmpty()){
            log.warn("Bruxo com ID: {} não encontrado para exclusão.", id);
            throw new NotFoundException("Bruxo com ID: " + id + " não encontrado.");
        }

        log.info("Excluindo bruxo com ID: {}", id);
        bruxoRepository.deleteById(id);
    }
}
