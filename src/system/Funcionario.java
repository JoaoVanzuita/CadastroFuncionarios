package system;

import java.util.Date;
import java.util.Objects;

public abstract class Funcionario {

    protected String nome;
    protected char sexo;
    protected Long cpf;
    protected Date dataNasc;
    protected double salario;

    public Funcionario(String nome, char sexo, long cpf, Date dataNasc, double salario) {
        this.nome = nome;
        this.sexo = sexo;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public void setDataNasc(Date dataNasc) {
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

}
