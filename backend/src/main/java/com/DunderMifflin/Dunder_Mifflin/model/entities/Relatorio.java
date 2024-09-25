package com.DunderMifflin.Dunder_Mifflin.model.entities;

import java.sql.Date;
import java.util.Calendar;

public class Relatorio {

    int codigo;
    String problema;
    Calendar data;
    Setor setor;
    Funcionario funcionario;

    public int getCodigo() {
        return codigo;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public Calendar getData() {
        return data;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public void setData(Calendar data) {
        this.data = data;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }


}
