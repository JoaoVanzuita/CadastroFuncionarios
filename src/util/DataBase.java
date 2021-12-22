package util;

import system.Clt;
import system.Funcionario;
import system.Pj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataBase {

    private Locale locale = Locale.getDefault();
    private ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.ENGLISH);
    private final Scanner inputNumber = new Scanner(System.in).useLocale(locale);
    private final Scanner inputString = new Scanner(System.in).useLocale(locale);
    private final Collection<Funcionario> listaFuncionarios = new HashSet<>();


    public void cadastrar(Funcionario funcionario) {
        listaFuncionarios.add(funcionario);

        System.out.println(bundle.getString("funcionario") + funcionario.getNome() + bundle.getString("cadastrado"));
    }

    public Collection<Funcionario> retornarLista() {
        return this.listaFuncionarios;
    }


    public String inserirNome() {
        System.out.println(bundle.getString("inserirNome"));

        String nome = inputString.nextLine();

        return nome;
    }

    public String inserirSexo() {
        System.out.println(bundle.getString("inserirSexo"));
        String sexo = inputString.next();

        while (!sexo.equalsIgnoreCase("M") && !sexo.equalsIgnoreCase("F")) {
            System.out.println(bundle.getString("sexoInvalido"));
            sexo = inputString.next();
        }

        return sexo;
    }

    public Long inserirCpf() {

        //TODO: adicionar try catch

        System.out.println(bundle.getString("inserirCpf"));
        Long cpf = inputNumber.nextLong();

        while (cpf.toString().length() != 11) {
            System.out.println(bundle.getString("cpfInvalido"));
            cpf = inputNumber.nextLong();
        }

        return cpf;
    }

    public Date inserirDataNasc() throws ParseException {

        System.out.println(bundle.getString("inserirData"));
        String dataNasc = inputString.next();

        while (dataNasc.charAt(2) != '/' || dataNasc.charAt(5) != '/' || dataNasc.length() != 10) {
            System.out.println(bundle.getString("dataInvalida"));
            dataNasc = inputString.next();
        }

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/uuuu");
        formato.setLenient (false);

        return formato.parse(dataNasc);
    }

    public double inserirSalario() {

        //TODO: adicionar try catch

        System.out.println(bundle.getString("inserirSalario"));
        double salario = inputNumber.nextDouble();

        while (salario < 1192.40) {
            System.out.println(bundle.getString("salarioInvalido"));
            salario = inputNumber.nextDouble();
        }

        return salario;
    }

    public double inserirValeTransporte(double salario) {

        //TODO: adicionar try catch

        System.out.println(bundle.getString("inserirValeTransporte"));
        double valeTransporte = inputNumber.nextDouble();

        while (valeTransporte > salario * 0.2 || valeTransporte < salario * 0.1) {
            System.out.println(bundle.getString("valeTransporteInvalido") +
                    bundle.getString("valorSalario") + salario + "\n 10%: " + salario * 0.1 + "\n 20%: " + salario * 0.2);
            valeTransporte = inputNumber.nextDouble();
        }

        return valeTransporte;
    }

    public double inserirValeSaude(double salario) {

        //TODO: adicionar try catch

        System.out.println(bundle.getString("inserirValeSaude"));
        double valeSaude = inputNumber.nextDouble();

        while (valeSaude > salario * 0.25 || valeSaude < salario * 0.15) {
            System.out.println(bundle.getString("valeSaudeInvalido") +
                    bundle.getString("valorSalario") + salario + "\n 15%: " + salario * 0.15 + "\n 25%: " + salario * 0.25);
            valeSaude = inputNumber.nextDouble();
        }

        return valeSaude;
    }

    public Clt cadastrarClt() {

        String nome = inserirNome();

        String stringSexo = inserirSexo();
            //converter sexo de String para char
            char sexo = stringSexo.charAt(0);

        Long cpf = inserirCpf();

        Date dataNasc = null;

        try {

            dataNasc = inserirDataNasc();

        } catch (ParseException e) {

            //e.printStackTrace();
            System.out.println(bundle.getString("ocorreuErro"));

            cadastrarClt();

        }

        double salario = inserirSalario();

        double valeTransporte = inserirValeTransporte(salario);

        double valeSaude = inserirValeSaude(salario);

        return new Clt(nome, sexo, cpf, dataNasc, salario, valeTransporte, valeSaude);
    }

    public Pj cadastrarPj() {

        String nome = inserirNome();

        String stringSexo = inserirSexo();
            //converter sexo de String para char
            char sexo = stringSexo.charAt(0);

        Long cpf = inserirCpf();

        Date dataNasc = null;

        try {

            dataNasc = inserirDataNasc();

        } catch (ParseException e) {

            //e.printStackTrace();

            System.out.println(bundle.getString("ocorreuErro"));

            cadastrarPj();

        }

        double salario = inserirSalario();

        return new Pj(nome, sexo, cpf, dataNasc, salario);
    }

    public void consultarFuncionario(Long cpf) {

        for (Funcionario funcionario0 : listaFuncionarios) {

            if (cpf.equals(funcionario0.getCpf())) {

                if (funcionario0 instanceof Pj) {

                    Pj funcionario = (Pj) funcionario0;

                    System.out.println(funcionario);

                } else if (funcionario0 instanceof Clt) {

                    Clt funcionario = (Clt) funcionario0;

                    System.out.println(funcionario);

                }

                break;
            }
        }
    }

    public void excluirFuncionario(Funcionario funcionario) {

        listaFuncionarios.remove(funcionario);

        System.out.println(bundle.getString("registro") + funcionario.getNome() + bundle.getString("excluir"));

    }

    public void editarClt(Clt funcionarioClt) {

        System.out.println(bundle.getString("digiteOpcao") + funcionarioClt.getNome());
        System.out.println(bundle.getString("nome"));
        System.out.println(bundle.getString("sexo"));
        System.out.println(bundle.getString("cpf"));
        System.out.println(bundle.getString("dataNasc"));
        System.out.println(bundle.getString("salario"));
        System.out.println(bundle.getString("valeTransporte"));
        System.out.println(bundle.getString("valeSaude"));

        int intOpcao = inputNumber.nextInt();

        //TODO: adicionar try catch

        switch (intOpcao) {

            case 1 -> {

                String nome = inserirNome();

                funcionarioClt.setNome(nome);

            }

            case 2 -> {

                String stringSexo = inserirSexo();
                    //converter sexo de String para char
                    char sexo = stringSexo.charAt(0);

                funcionarioClt.setSexo(sexo);

            }

            case 3 -> {

                Long cpf = inserirCpf();

                funcionarioClt.setCpf(cpf);

            }

            case 4 -> {

                Date dataNasc = null;

                try {

                    dataNasc = inserirDataNasc();

                } catch (ParseException e) {

                    //e.printStackTrace();

                    System.out.println(bundle.getString("ocorreuErro"));

                    editarClt(funcionarioClt);
                }

                funcionarioClt.setDataNasc(dataNasc);

            }

            case 5 -> {

                double salario = inserirSalario();

                funcionarioClt.setSalario(salario);

            }

            case 6 -> {

                double valeTransporte = inserirValeTransporte(funcionarioClt.getSalario());

                funcionarioClt.setValeTransporte(valeTransporte);

            }

            case 7 -> {

                double valeSaude = inserirValeSaude(funcionarioClt.getSalario());

                funcionarioClt.setValeSaude(valeSaude);

            }

        }

        System.out.println(bundle.getString("registro") + funcionarioClt.getNome() + bundle.getString("atualizado"));

    }

    public void editarPj(Pj funcionarioPj) {

        System.out.println(bundle.getString("digiteOpcao") + funcionarioPj.getNome());
        System.out.println(bundle.getString("nome"));
        System.out.println(bundle.getString("sexo"));
        System.out.println(bundle.getString("cpf"));
        System.out.println(bundle.getString("dataNasc"));
        System.out.println(bundle.getString("salario"));

        //TODO: adicionar try catch

        int intOpcao = inputNumber.nextInt();

        switch (intOpcao) {

            case 1 -> {

                String nome = inserirNome();

                funcionarioPj.setNome(nome);


            }

            case 2 -> {

                String stringSexo = inserirSexo();
                    //converter sexo de String para char
                    char sexo = stringSexo.charAt(0);

                funcionarioPj.setSexo(sexo);


            }

            case 3 -> {

                Long cpf = inserirCpf();

                funcionarioPj.setCpf(cpf);


            }

            case 4 -> {

                Date dataNasc = null;

                try {

                    dataNasc = inserirDataNasc();

                } catch (ParseException e) {

                    //e.printStackTrace();

                    System.out.println(bundle.getString("ocorreuErro"));

                    editarPj(funcionarioPj);

                }

                funcionarioPj.setDataNasc(dataNasc);


            }

            case 5 -> {

                double salario = inserirSalario();

                funcionarioPj.setSalario(salario);


            }
        }

        System.out.println(bundle.getString("registro") + funcionarioPj.getNome() + bundle.getString("atualizado"));

    }
}