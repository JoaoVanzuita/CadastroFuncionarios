package util;

import system.Clt;
import system.Funcionario;
import system.Pj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataBase {

    private Locale locale = Locale.getDefault();
    private ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
    private final Scanner input = new Scanner(System.in).useLocale(locale);
    private final ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();


    public void cadastrar(Funcionario funcionario) {
        listaFuncionarios.add(funcionario);

        System.out.println(bundle.getString("funcionario") + funcionario.getNome() + bundle.getString("cadastrado"));
    }

    public ArrayList<Funcionario> retornarLista() {
        return this.listaFuncionarios;
    }

    public String inserirNome() {

        System.out.println(bundle.getString("inserirNome"));

        return input.nextLine();
    }

    public String inserirSexo() {

        String sexo;

        do {

            System.out.println(bundle.getString("inserirSexo"));
            sexo = input.nextLine();

            if(!sexo.equalsIgnoreCase("M") && !sexo.equalsIgnoreCase("F")){

                System.out.println(bundle.getString("cpfInvalido"));

            }

        }while (!sexo.equalsIgnoreCase("M") && !sexo.equalsIgnoreCase("F"));

        return sexo;
    }

    //CÓDIGO DO MÉTODO CONCLUÍDO
    public Long inserirCpf() {

        String cpf;
        long cpfRetorno = 0;

        do {

            System.out.println(bundle.getString("inserirCpf"));

            cpf = input.nextLine();

            if(cpf.length() != 11){

                System.out.println(bundle.getString("cpfInvalido"));

            }

        }while (cpf.length() != 11);

        try{

            cpfRetorno = Long.parseLong(cpf);

        }catch (NumberFormatException e){

            //e.printStackTrace();

            System.out.println(bundle.getString("ocorreuErro") + " (NumberFormatException)");

        }catch(Exception e){

            //e.printStackTrace();

            System.out.println(bundle.getString("erroDesconhecido"));

        }

            return cpfRetorno;
    }

    //CÓDIGO DO MÉTODO CONCLUÍDO
    public Date inserirDataNasc(){

        //TODO: substituir Date por Calendar

        String dataNasc;
        Date dataFormatada = null;

        do {

            System.out.println(bundle.getString("inserirData"));
            dataNasc = input.nextLine();

            if((dataNasc.charAt(2) != '/' || dataNasc.charAt(5) != '/' || dataNasc.length() != 10)) {

                System.out.println(bundle.getString("dataInvalida"));

            }

        }while((dataNasc.charAt(2) != '/' || dataNasc.charAt(5) != '/' || dataNasc.length() != 10));

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);

        try {

            dataFormatada = formato.parse(dataNasc);

        } catch (ParseException e) {

            //e.printStackTrace();

            System.out.println(bundle.getString("ocorreuErro") + " (ParseException)");

        }catch(Exception e){

            //e.printStackTrace();

            System.out.println(bundle.getString("erroDesconhecido"));

        }

        return dataFormatada;
    }

    //CÓDIGO DO MÉTODO CONCLUÍDO
    public double inserirSalario(String tipo) {

        String stringSalario;
        boolean isValide = false;
        double salario = 0;

        do{

            System.out.println(bundle.getString("inserirSalario"));
            stringSalario = input.nextLine();

            try {

                salario = Double.parseDouble(stringSalario);

                    if (tipo.equalsIgnoreCase("clt") && salario < 1192.40) {

                        System.out.println(bundle.getString("salarioInvalido"));

                    }else{

                        isValide = true;

                    }


            } catch (NumberFormatException e) {

                //e.printStackTrace();

                System.out.println(bundle.getString("ocorreuErro") + " (NumberFormatException)");

            }catch(Exception e){

                //e.printStackTrace();

                System.out.println(bundle.getString("erroDesconhecido"));

            }

        }while(isValide == false);

        return salario;
    }

    //CÓDIGO DO MÉTODO CONCLUÍDO
    public double inserirValeTransporte(double salario) {

        String vale;
        double valeTransporte = 0;

        do {

            System.out.println(bundle.getString("inserirValeTransporte"));

            vale = input.nextLine();

            try {

                valeTransporte = Double.parseDouble(vale);

                if(valeTransporte > salario * 0.2 || valeTransporte < salario * 0.1){

                    System.out.println(bundle.getString("valeTransporteInvalido") +
                            bundle.getString("valorSalario") + salario + "\n 10%: " + salario * 0.1 + "\n 20%: " + salario * 0.2);

                }

            } catch (NumberFormatException e) {

                //e.printStackTrace();

                System.out.println(bundle.getString("ocorreuErro") + " (NumberFormatException)");

            }catch(Exception e){

                //e.printStackTrace();

                System.out.println(bundle.getString("erroDesconhecido"));

            }

        }while(valeTransporte > salario * 0.2 || valeTransporte < salario * 0.1);

        return valeTransporte;
    }

    //CÓDIGO DO MÉTODO CONCLUÍDO
    public double inserirValeSaude(double salario) {

        String vale;
        double valeSaude = 0;

        do {

            System.out.println(bundle.getString("inserirValeSaude"));

            vale = input.nextLine();

            try {

                valeSaude = Double.parseDouble(vale);

                if(valeSaude > salario * 0.25 || valeSaude < salario * 0.15){

                    System.out.println(bundle.getString("valeSaudeInvalido") +
                            bundle.getString("valorSalario") + salario + "\n 15%: " + salario * 0.15 + "\n 25%: " + salario * 0.25);

                }

            } catch (NumberFormatException e) {

                //e.printStackTrace();

                System.out.println(bundle.getString("ocorreuErro") + " (NumberFormatException)");

            }catch(Exception e){

                //e.printStackTrace();

                System.out.println(bundle.getString("erroDesconhecido"));

            }

        }while(valeSaude > salario * 0.25 || valeSaude < salario * 0.15);

        return valeSaude;
    }

    public Clt cadastrarClt() {

        String nome = inserirNome();

        String stringSexo = inserirSexo();
            //converter sexo de String para char
            char sexo = stringSexo.charAt(0);

        Long cpf = inserirCpf();

        Date dataNasc = inserirDataNasc();

        double salario = inserirSalario("clt");

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

        Date dataNasc = inserirDataNasc();

        double salario = inserirSalario("pj");

        return new Pj(nome, sexo, cpf, dataNasc, salario);
    }

    public void consultarFuncionario(Long cpf) {

        for (Funcionario funcionario0 : listaFuncionarios) {

            if (cpf.equals(funcionario0.getCpf())) {

                if (funcionario0 instanceof Pj funcionario) {

                    System.out.println(funcionario);

                } else if (funcionario0 instanceof Clt funcionario) {

                    System.out.println(funcionario);

                }

                break;
            }
        }
    }

    //CÓDIGO DO MÉTODO CONCLUÍDO
    public void excluirFuncionario(Funcionario funcionario) {

        listaFuncionarios.remove(funcionario);

        System.out.println(bundle.getString("registro") + funcionario.getNome() + bundle.getString("excluido"));

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


        String opcao = input.nextLine();
        int intOpcao = Integer.parseInt(opcao);

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

                Date dataNasc = inserirDataNasc();

                funcionarioClt.setDataNasc(dataNasc);

            }

            case 5 -> {

                double salario = inserirSalario("clt");

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

        String opcao;
        int intOpcao = 0;

        do{

            opcao = input.nextLine();

            try {

                intOpcao = Integer.parseInt(opcao);

            } catch (NumberFormatException e) {

                //e.printStackTrace();

                System.out.println(bundle.getString("ocorreuErro") + " (NumberFormatException)");

            }catch(Exception e){

                //e.printStackTrace();

                System.out.println(bundle.getString("erroDesconhecido"));

            }

        }while (intOpcao == 0);

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

                Date dataNasc = inserirDataNasc();

                funcionarioPj.setDataNasc(dataNasc);


            }

            case 5 -> {

                double salario = inserirSalario("pj");

                funcionarioPj.setSalario(salario);


            }
        }

        System.out.println(bundle.getString("registro") + funcionarioPj.getNome() + bundle.getString("atualizado"));

    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public Locale getLocale() {
        return locale;
    }
}