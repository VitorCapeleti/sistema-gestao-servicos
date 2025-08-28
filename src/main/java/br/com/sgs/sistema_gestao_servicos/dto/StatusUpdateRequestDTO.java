package br.com.sgs.sistema_gestao_servicos.dto;

import br.com.sgs.sistema_gestao_servicos.enums.StatusOS;
import lombok.Data;

@Data
public class StatusUpdateRequestDTO {
    private StatusOS status;
}