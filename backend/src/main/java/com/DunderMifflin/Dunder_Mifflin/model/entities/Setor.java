package com.DunderMifflin.Dunder_Mifflin.model.entities;

import java.util.List;

public class Setor {
    List<Funcionario> funcionarios;
    List<Relatorio> relatorios;

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
}
