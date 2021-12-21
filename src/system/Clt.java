package system;

public class Clt extends Funcionario {

    private double valeTransporte, valeSaude;

    public Clt(String nome, char sexo, Long cpf, String dataNasc, double salario, double valeTransporte, double valeSaude) {
        super(nome, sexo, cpf, dataNasc, salario);
        this.valeTransporte = valeTransporte;
        this.valeSaude = valeSaude;
        this.setSalario(this.salario - (valeTransporte + valeSaude));
    }

    public void setValeTransporte(double valeTransporte) {
        this.valeTransporte = valeTransporte;
    }


    public void setValeSaude(double valeSaude) {
        this.valeSaude = valeSaude;
    }

    @Override
    public String toString() {
        return "\nFuncionario CLT{" +
                ", nome='" + nome + '\'' +
                ", sexo=" + sexo +
                ", cpf=" + cpf +
                ", dataNasc='" + dataNasc + '\'' +
                ", salario=" + salario +
                " valeTransporte=" + valeTransporte +
                ", valeSaude=" + valeSaude +
                "}\n";
    }
}
