package com.DunderMifflin.Dunder_Mifflin.model.entities;

import java.util.List;

public class Setor {
    int codigo;
    String nome;
    List<Funcionario> funcionarios;
    List<Relatorio> relatorios;

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Relatorio> getRelatorios() {
        return relatorios;
    }

    public void setRelatorios(List<Relatorio> relatorios) {
        this.relatorios = relatorios;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
