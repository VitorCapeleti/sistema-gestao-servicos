package br.com.sgs.sistema_gestao_servicos.service.event;

import br.com.sgs.sistema_gestao_servicos.model.Funcionario;
import org.springframework.context.ApplicationEvent;

public class FuncionarioCriadoEvent extends ApplicationEvent {

    private final Funcionario funcionario;

    public FuncionarioCriadoEvent(Object source, Funcionario funcionario) {
        super(source);
        this.funcionario = funcionario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }
}