package org.zaccarias.fernandes.christian.aramis.classes;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class RegistroPonto {
    private LocalTime entrada;
    private LocalTime saida;

    public RegistroPonto(LocalTime entrada, LocalTime saida) {
        this.entrada = entrada;
        this.saida = saida;
    }

    public float calcularHorasTrabalhadas() {
        long minutosTrabalhados = ChronoUnit.MINUTES.between(entrada, saida);
        return minutosTrabalhados >= 0 ?  arredondarFloatParaCasasDecimais(minutosTrabalhados /60f,2) : 0;
    }
    private float arredondarFloatParaCasasDecimais(float number, int scale) {
        int pow = 10;
        for (int i = 1; i < scale; i++)
            pow  *= 10;
        float tmp = number * pow;
        return ( (float) ( (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) ) ) / pow;
    }
}