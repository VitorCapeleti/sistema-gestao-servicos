package br.com.sgs.sistema_gestao_servicos.service.listener;

import br.com.sgs.sistema_gestao_servicos.model.OrdemServico;
import br.com.sgs.sistema_gestao_servicos.service.event.ClienteCriadoEvent;
import br.com.sgs.sistema_gestao_servicos.service.event.FuncionarioCriadoEvent; // IMPORTAR
import br.com.sgs.sistema_gestao_servicos.service.event.OrdemServicoStatusEvent; // IMPORTAR
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoListener {

    @EventListener
    public void aoCriarCliente(ClienteCriadoEvent event) {
        System.out.println("----------------------------------------------------");
        System.out.println("OBSERVER: Novo cliente cadastrado! Notificando...");
        System.out.println("Cliente: " + event.getCliente().getNome() + " | Email: " + event.getCliente().getEmail());
        System.out.println("----------------------------------------------------");
    }

    // NOVO MÉTODO OBSERVADOR AQUI
    @EventListener
    public void aoCriarFuncionario(FuncionarioCriadoEvent event) {
        System.out.println("----------------------------------------------------");
        System.out.println("OBSERVER: Novo funcionário contratado! Notificando RH...");
        System.out.println("Funcionário: " + event.getFuncionario().getNome() + " | Cargo: " + event.getFuncionario().getCargo());
        System.out.println("----------------------------------------------------");
    }
    @EventListener
    public void aoMudarStatusOS(OrdemServicoStatusEvent event) {
        OrdemServico os = event.getOrdemServico();
        System.out.println("----------------------------------------------------");
        System.out.println("OBSERVER: Status da OS #" + os.getId() + " alterado!");
        System.out.println("Novo Status: " + os.getStatus());
        System.out.println("Cliente: " + os.getCliente().getNome());
        System.out.println("----------------------------------------------------");
    }
}