package System;

public class Clt extends Funcionario {

    private double valeTransporte, valeSaude;

    public Clt(String nome, char sexo, Long cpf, String dataNasc, double salario, double valeTransporte, double valeSaude) {
        super(nome, sexo, cpf, dataNasc, salario);
        this.valeTransporte = valeTransporte;
        this.valeSaude = valeSaude;
    }


    public double getValeTransporte() {
        return valeTransporte;
    }

    public void setValeTransporte(double valeTransporte) {
        this.valeTransporte = valeTransporte;
    }

    public double getValeSaude() {
        return valeSaude;
    }

    public void setValeSaude(double valeSaude) {
        this.valeSaude = valeSaude;
    }

    public double descontarBeneficios(double valeTransporte, double valeSaude){
        return this.getSalario() - (valeTransporte + valeSaude);
    }

}
