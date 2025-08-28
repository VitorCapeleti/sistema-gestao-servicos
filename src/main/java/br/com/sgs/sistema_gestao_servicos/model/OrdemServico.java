package br.com.sgs.sistema_gestao_servicos.model;

import br.com.sgs.sistema_gestao_servicos.enums.StatusOS;
import br.com.sgs.sistema_gestao_servicos.enums.TipoOS;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ordens_servico")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Muitos(as) Ordens de Serviço para UM Cliente
    @JoinColumn(name = "cliente_id", nullable = false) // Chave estrangeira na tabela
    private Cliente cliente;

    @ManyToOne // Muitos(as) Ordens de Serviço para UM Funcionário
    @JoinColumn(name = "funcionario_id") // Permitimos nulo, pois pode ser atribuída depois
    private Funcionario funcionario;

    @Enumerated(EnumType.STRING) // Grava o nome do enum ("PREVENTIVA") no banco, em vez do número (0)
    @Column(nullable = false)
    private TipoOS tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOS status;

    private String descricao;

    private BigDecimal custo;

    private LocalDateTime dataAbertura;

    private LocalDateTime dataFechamento;
}