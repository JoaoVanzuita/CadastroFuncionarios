package system;

import java.util.Date;

public class Pj extends Funcionario {
    public Pj(String nome, char sexo, long cpf, Date dataNasc, double salario) {
        super(nome, sexo, cpf, dataNasc, salario);
    }

    @Override
    public String toString() {
        return "\nFuncionario PJ{" +
                "nome='" + nome + '\'' +
                ", sexo=" + sexo +
                ", cpf=" + cpf +
                ", dataNasc='" + dataNasc + '\'' +
                ", salario=" + salario +
                "}\n";
    }

}
