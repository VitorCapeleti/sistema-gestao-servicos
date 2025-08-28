package br.com.sgs.sistema_gestao_servicos.dto;

import br.com.sgs.sistema_gestao_servicos.enums.TipoOS;
import lombok.Data;

@Data
public class OrdemServicoRequestDTO {
    private Long clienteId;
    private Long funcionarioId;
    private TipoOS tipo;
    private String descricao;
}