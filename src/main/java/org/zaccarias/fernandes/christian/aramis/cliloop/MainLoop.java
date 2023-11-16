package org.zaccarias.fernandes.christian.aramis.cliloop;

import org.zaccarias.fernandes.christian.aramis.classes.FuncionarioClass;
import org.zaccarias.fernandes.christian.aramis.enums.CargosEnum;
import org.zaccarias.fernandes.christian.aramis.enums.TiposEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime; // Importação para LocalTime
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.time.format.DateTimeParseException; // Importação para DateTimeParseException

public class MainLoop {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final List<FuncionarioClass> funcionarioClassList = new ArrayList<>();

    public void runMainLoop() throws IOException {
        boolean exitFlag = false;
        while (!exitFlag) {
            System.out.println("1:Criar\n2:Ponto\n3:Extrato\n4:Editar\n5:Sair");

            String input = reader.readLine();
            switch (input) {
                case "1":
                    funcionarioClassList.add(runCreateLoop());
                    break;
                case "2":
                    runPontoLoop();
                    break;
                case "3":
                    runExtratoLoop();
                    break;
                case "4":
                    runEditarLoop();
                    break;
                case "5":
                    exitFlag = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    public FuncionarioClass runCreateLoop() throws IOException {
        String name;
        TiposEnum tipo;
        String cargo;
        CargosEnum cargoEnum;

        while (true) {
            System.out.println("Digite Nome:");
            name = reader.readLine();
            System.out.println("Digite Cargo:");
            cargo = reader.readLine();
            try {
                cargoEnum = CargosEnum.fromString(cargo);
            } catch (IllegalArgumentException exception) {
                System.out.println("Cargo inválido.");
                continue;
            }
            System.out.println("Digite Tipo (Horista, Mensalista):");
            try{
                tipo = TiposEnum.fromString(reader.readLine());
            }
            catch (IllegalArgumentException exception){
                System.out.println("Tipo invalido.");
                continue;
            }
            break;
        }
        return new FuncionarioClass(cargoEnum, name, tipo);
    }

    public void runPontoLoop() throws IOException {
        if (funcionarioClassList.isEmpty()) {
            System.out.println("Não há funcionários para editar.");
            return;
        }

        IntStream.range(0, funcionarioClassList.size()).forEach(index -> {
            System.out.println(index + " - " + funcionarioClassList.get(index).toString());
        });

        System.out.println("Digite o número para alterar:");
        String input = reader.readLine();

        try {
            int index = Integer.parseInt(input);
            if (index < 0 || index >= funcionarioClassList.size()) {
                System.out.println("Índice inválido.");
                return;
            }

            FuncionarioClass funcionarioEditado = funcionarioClassList.get(index);
            System.out.println(funcionarioEditado.toString());

            System.out.println("Digite o horário de entrada (formato HH:MM): ");
            String entradaStr = reader.readLine();
            LocalTime entrada = LocalTime.parse(entradaStr);

            System.out.println("Digite o horário de saída (formato HH:MM): ");
            String saidaStr = reader.readLine();
            LocalTime saida = LocalTime.parse(saidaStr);

            funcionarioEditado.baterPonto(entrada, saida);

            System.out.println("Ponto registrado para " + funcionarioEditado.getName());
            System.out.println("Horas trabalhadas: " + funcionarioEditado.calcularHorasTrabalhadas());
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println("Formato de entrada inválido para número ou horário.");
        }
    }
    public void runExtratoLoop() throws IOException{
        if (funcionarioClassList.isEmpty()) {
            System.out.println("Não há funcionários para editar.");
            return;
        }
        while (true){
            IntStream.range(0, funcionarioClassList.size()).forEach(index -> {
                System.out.println(index + " - " + funcionarioClassList.get(index).toString());
            });

            System.out.println("Digite o número para consultar:");
            String input = reader.readLine();
            FuncionarioClass funcionario = funcionarioClassList.get(Integer.parseInt(input));
            System.out.println("Horas totais trabalhadas: " + funcionario.calcularHorasTrabalhadas());
            System.out.println("Salario total: " + funcionario.calcularSalario());
            System.out.println("Total de horas Extras/Banco de horas: " + funcionario.calcularHoraExtra());
            break;
        }
    }
    public void runEditarLoop() throws IOException {
        if (funcionarioClassList.isEmpty()) {
            System.out.println("Não há funcionário para editar.\n");
            return;
        }
        boolean exitFlag = false;

        while (!exitFlag) {
            IntStream.range(0, funcionarioClassList.size()).forEach(index -> {
                System.out.println(index + " - " + funcionarioClassList.get(index).toString());
            });

            System.out.println("Digite o número para editar:");
            int input = Integer.parseInt(reader.readLine());

            FuncionarioClass funcionario = funcionarioClassList.get(input);

            System.out.println("0: Nome\n1: Tipo\n2: Cargo\n");
            input = Integer.parseInt(reader.readLine());

            switch (input) {
                case 0:
                    System.out.println("Digite o novo nome:\n");
                    String nome = reader.readLine();
                    funcionario.setName(nome);
                    break;
                case 1:
                    System.out.println("Digite o nome tipo: (Horista ou Mensalista)\n");
                    try {
                        TiposEnum tipo = TiposEnum.fromString(reader.readLine());
                        funcionario.setTipo(tipo);
                    } catch (IllegalArgumentException exception) {
                        System.out.println("Tipo inválido\n");
                    }
                    break;
                case 2:
                    System.out.println("Digite o novo cargo:\n");
                    try {
                        CargosEnum cargo = CargosEnum.fromString(reader.readLine());
                        funcionario.setCargo(cargo);
                    } catch (IllegalArgumentException exception) {
                        System.out.println("Cargo inválido\n");
                    }
                    break;
            }
            exitFlag = true;
            System.out.println("Funcionário editado:\n");
            System.out.println(funcionario.toString());
        }
    }
}