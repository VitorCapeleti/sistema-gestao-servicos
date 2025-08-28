package br.com.sgs.sistema_gestao_servicos.service.strategy;

import java.math.BigDecimal;

public interface CalculoCustoStrategy {
    BigDecimal calcular();
    String getTipo(); // Método para identificar a estratégia
}