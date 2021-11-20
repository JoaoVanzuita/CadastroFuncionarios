package Util;

import system.Clt;
import system.Funcionario;

import java.util.ArrayList;

public class DataBase {

    private final ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();

    public void cadastrar(Funcionario funcionario){
        this.listaFuncionarios.add(funcionario);
    }

    public ArrayList retornarLista(){
        return this.listaFuncionarios;
    }

    public void editarCadastro(Clt funcionario, int opcao) {


        switch (opcao) {
            case 0:

                String nome = null;
                funcionario.setNome(nome);

                break;

            case 1:

                char sexo = ' ';
                funcionario.setSexo(sexo);

                break;

            case 2:

                long cpf = 00000000000L;
                funcionario.setCpf(cpf);

                break;

            case 3:

                String dataNasc;
                funcionario.setDataNasc("__/__/____");

                break;

            case 4:
                double salario = 0;
                funcionario.setSalario(salario);

                break;

            case 5:

                double valeTransporte = 0;
                funcionario.setValeTransporte(valeTransporte);

                break;

            case 6:

                double valeSaude = 0;
                funcionario.setValeSaude(valeSaude);

            default:
                System.out.println("Opção inválida");
        }
    }
}
