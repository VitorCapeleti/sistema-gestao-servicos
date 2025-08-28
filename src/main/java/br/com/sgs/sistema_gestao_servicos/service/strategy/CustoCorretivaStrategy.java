package br.com.sgs.sistema_gestao_servicos.service.strategy;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("CORRETIVA")
public class CustoCorretivaStrategy implements CalculoCustoStrategy {
    @Override
    public BigDecimal calcular() {
        // Lógica para OS Corretiva (ex: taxa de visita + peças)
        // Vamos simular um valor por enquanto
        return new BigDecimal("350.00");
    }

    @Override
    public String getTipo() {
        return "CORRETIVA";
    }
}