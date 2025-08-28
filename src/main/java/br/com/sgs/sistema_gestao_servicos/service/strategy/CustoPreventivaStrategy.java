package br.com.sgs.sistema_gestao_servicos.service.strategy;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("PREVENTIVA") // O nome do bean é o mesmo do Enum!
public class CustoPreventivaStrategy implements CalculoCustoStrategy {
    @Override
    public BigDecimal calcular() {
        // Lógica de custo para OS Preventiva (ex: taxa fixa)
        return new BigDecimal("150.00");
    }

    @Override
    public String getTipo() {
        return "PREVENTIVA";
    }
}