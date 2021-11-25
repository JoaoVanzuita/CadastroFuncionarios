package system;

import java.util.Objects;

public abstract class Funcionario {

    protected String nome;
    protected char sexo;
    protected Long cpf;
    protected String dataNasc;
    protected double salario;

    public Funcionario(String nome, char sexo, Long cpf, String dataNasc, double salario) {
        this.setNome(nome);
        this.setSexo(sexo);
        this.setCpf(cpf);
        this.setDataNasc(dataNasc);
        this.setSalario(salario);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(getCpf(), that.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCpf());
    }


    public void bonus(int porcentagem) {
        this.setSalario(this.getSalario() + (this.getSalario() / 100) * porcentagem);
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", sexo=" + sexo +
                ", cpf=" + cpf +
                ", dataNasc='" + dataNasc + '\'' +
                ", salario=" + salario +
                '}';
    }
}
