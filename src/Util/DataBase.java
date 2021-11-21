package Util;

import system.Clt;
import system.Funcionario;
import system.Pj;

import java.util.Collection;
import java.util.HashSet;

public class DataBase {

    private final Collection<Funcionario> listaFuncionarios = new HashSet<>();

    public void cadastrar(Funcionario funcionario){
        listaFuncionarios.add(funcionario);
    }

    public void adicionarFuncionario(Funcionario funcionario){
        this.listaFuncionarios.add(funcionario);
    }

    public Collection<Funcionario> retornarLista(){
        return this.listaFuncionarios;
    }

    public void editarCadastro(Long cpf, int opcao) {

        //variável usada para informar se o registro com o cfp digitado foi encontrado no listaFuncionarios
        Boolean cpfValido = false;

        for (Funcionario funcionario1: listaFuncionarios) {

            //procura um funcionário com o cpf igual ao inserido pelo usuário
            if(cpf == funcionario1.getCpf()) {
                if (funcionario1 instanceof Pj) {

                    //transforma tipo do funcionario1 para Pj
                    Pj funcionarioPj = (Pj) funcionario1;

                    this.atualizarPj(funcionarioPj, opcao);

                } else {

                    //transforma o tipo do funcionario1 para Clt
                    Clt funcionarioClt = (Clt) funcionario1;

                    this.atualizarClt(funcionarioClt, opcao);
                }
                //informa que o registro com o cpf digitado foi encontrado
                cpfValido= true;
                break;
            }
        }

        //informa caso o registro com o cpf digitado não tenha sido encontrado
        if(!cpfValido) {
            System.out.println("CPF digitado inválido");
        }

    }


    public void atualizarPj(Pj funcionarioPj, int opcao){

        switch (opcao) {
            case 0 -> {
                String nome = null;
                funcionarioPj.setNome(nome);
            }
            case 1 -> {
                char sexo = ' ';
                funcionarioPj.setSexo(sexo);
            }
            case 2 -> {
                long cpf = 00000000000L;
                funcionarioPj.setCpf(cpf);
            }
            case 3 -> {
                String dataNasc;
                funcionarioPj.setDataNasc("__/__/____");
            }
            case 4 -> {
                double salario = 0;
                funcionarioPj.setSalario(salario);
            }
        }
    }


    public  void atualizarClt(Clt funcionarioClt, int opcao){

        switch (opcao) {
            case 0 -> {
                String nome = null;
                funcionarioClt.setNome(nome);
            }
            case 1 -> {
                char sexo = ' ';
                funcionarioClt.setSexo(sexo);
            }
            case 2 -> {
                long cpf = 00000000000L;
                funcionarioClt.setCpf(cpf);
            }
            case 3 -> {
                String dataNasc;
                funcionarioClt.setDataNasc("__/__/____");
            }
            case 4 -> {
                double salario = 0;
                funcionarioClt.setSalario(salario);
            }
            case 5 -> {
                double valeTransporte = 0;
                funcionarioClt.setValeTransporte(valeTransporte);
            }
            case 6 -> {
                double valeSaude = 0;
                funcionarioClt.setValeSaude(valeSaude);
            }
        }
    }
}