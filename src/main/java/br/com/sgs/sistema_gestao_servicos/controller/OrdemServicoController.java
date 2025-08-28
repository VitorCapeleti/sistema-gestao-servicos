package br.com.sgs.sistema_gestao_servicos.controller;

import br.com.sgs.sistema_gestao_servicos.dto.OrdemServicoRequestDTO;
import br.com.sgs.sistema_gestao_servicos.dto.StatusUpdateRequestDTO;
import br.com.sgs.sistema_gestao_servicos.model.OrdemServico;
import br.com.sgs.sistema_gestao_servicos.service.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/os")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService osService;

    @PostMapping
    public ResponseEntity<OrdemServico> abrirOS(@RequestBody OrdemServicoRequestDTO dto) {
        OrdemServico novaOS = osService.abrirOS(dto);
        return new ResponseEntity<>(novaOS, HttpStatus.CREATED);
    }

    @GetMapping
    public List<OrdemServico> listarTodasOS() {
        return osService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarOSPorId(@PathVariable Long id) {
        return osService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrdemServico> atualizarStatus(@PathVariable Long id, @RequestBody StatusUpdateRequestDTO dto) {
        try {
            OrdemServico osAtualizada = osService.atualizarStatus(id, dto.getStatus());
            return ResponseEntity.ok(osAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/{id}/finalizar")
    public ResponseEntity<OrdemServico> finalizarOS(@PathVariable Long id) {
        try {
            OrdemServico osFinalizada = osService.finalizarOS(id);
            return ResponseEntity.ok(osFinalizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}