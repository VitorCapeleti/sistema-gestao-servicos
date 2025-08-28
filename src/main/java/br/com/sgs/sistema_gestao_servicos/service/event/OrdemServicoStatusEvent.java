package br.com.sgs.sistema_gestao_servicos.service.event;

import br.com.sgs.sistema_gestao_servicos.model.OrdemServico;
import org.springframework.context.ApplicationEvent;

public class OrdemServicoStatusEvent extends ApplicationEvent {
    private final OrdemServico ordemServico;
    public OrdemServicoStatusEvent(Object source, OrdemServico ordemServico) {
        super(source);
        this.ordemServico = ordemServico;
    }
    public OrdemServico getOrdemServico() {
        return ordemServico;
    }
}