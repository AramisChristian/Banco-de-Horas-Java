package org.zaccarias.fernandes.christian.aramis.enums;

public enum CargosEnum {
    ATENDENTE1("Atendente 1", 2500f/220f),
    ATENDENTE2("Atendente 2", 2800f/220f),
    SUPERVISOR("Supervisor", 4000f/220f),
    CORDENADOR("Coordenador", 5000f/220f),
    GERENTE1("Gerente 1", 7000f/220f),
    GERENTE2("Gerente 2", 8000f/220f),
    DIRETOR("Diretor", 10000f/220f),
    VICE("Vice Presidente", 12000f/220f),
    CCO("CCO", 15000f/220f),
    CEO("CEO", 20000f/220f);

    private final String label;
    private final double salarioHora;

    CargosEnum(String label, double salario) {
        this.label = label;
        this.salarioHora = salario;
    }

    public String getLabel() {
        return this.label;
    }

    public double getSalario() {
        return this.salarioHora;
    }

    public static CargosEnum fromString(String text) {
        for (CargosEnum b : CargosEnum.values()) {
            if (b.getLabel().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}


