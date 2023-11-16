package org.zaccarias.fernandes.christian.aramis.classes;

import org.zaccarias.fernandes.christian.aramis.enums.CargosEnum;
import org.zaccarias.fernandes.christian.aramis.enums.TiposEnum;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioClass {
    private CargosEnum cargo;
    private TiposEnum tipo;
    private String name;
    private List<RegistroPonto> registrosPonto;

    public CargosEnum getCargo() {
        return this.cargo;
    }

    public String getName() {
        return name;
    }

    public TiposEnum getTipo() {
        return tipo;
    }

    public void setCargo(CargosEnum cargo) {
        this.cargo = cargo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTipo(TiposEnum tipo) {
        this.tipo = tipo;
    }


    public FuncionarioClass(CargosEnum cargo, String name, TiposEnum tipo) {
        this.cargo = cargo;
        this.name = name;
        this.tipo = tipo;
        this.registrosPonto = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Nome: " + this.getName() + "\n" +
                "Tipo: " + this.getTipo() + "\n" +
                "Cargo: " + this.getCargo().getLabel() + "\n";
    }

    public void baterPonto(LocalTime entradaStr, LocalTime saidaStr) {
        registrosPonto.add(new RegistroPonto(entradaStr, saidaStr));
    }

    public float calcularHorasTrabalhadas() {
        float horasTotais = 0f;
        for (RegistroPonto registro : registrosPonto) {
            horasTotais += registro.calcularHorasTrabalhadas();
        }
        return horasTotais;
    }

    public float calcularHoraExtra(){
        float horasTrabalhadas = this.calcularHorasTrabalhadas();
        return horasTrabalhadas >=220f ? horasTrabalhadas - 220f : 0f;
    }

    public float calcularSalario(){
        float salarioRegular = 0f,salarioHoraExtra = 0f;
        float horasTrabalhadas = this.calcularHorasTrabalhadas();

        if (horasTrabalhadas >= 220f){
            salarioRegular = (float) this.getCargo().getSalario()*220f;
            salarioHoraExtra = this.getTipo().getLabel().equals(TiposEnum.HORISTA.getLabel())
                    ? (float) (this.getCargo().getSalario()*1.5f*this.calcularHoraExtra()) : 0f;
        }
        else {
            salarioRegular = (float) this.getCargo().getSalario()*horasTrabalhadas;
        }
        return salarioRegular + salarioHoraExtra;
    }

}