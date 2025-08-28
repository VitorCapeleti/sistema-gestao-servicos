package br.com.sgs.sistema_gestao_servicos.service.event;

import br.com.sgs.sistema_gestao_servicos.model.Cliente;
import org.springframework.context.ApplicationEvent;

public class ClienteCriadoEvent extends ApplicationEvent {

    private final Cliente cliente;

    public ClienteCriadoEvent(Object source, Cliente cliente) {
        super(source);
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }
}