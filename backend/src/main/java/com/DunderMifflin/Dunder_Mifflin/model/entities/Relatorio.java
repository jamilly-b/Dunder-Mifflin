package com.DunderMifflin.Dunder_Mifflin.model.entities;

import java.util.Calendar;

public class Relatorio {

    int codigo;
    TipoRelatorio tipo;
    Calendar data;
    private String dataFormatada;
    int setor;
    int funcionario;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public TipoRelatorio getTipo() {
        return tipo;
    }

    public void setTipo(TipoRelatorio tipo) {
        this.tipo = tipo;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public int getSetor() {
        return setor;
    }

    public void setSetor(int setor) {
        this.setor = setor;
    }

    public int getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

    public String getDataFormatada() {
        return dataFormatada;
    }

    public void setDataFormatada(String dataFormatada) {
        this.dataFormatada = dataFormatada;
    }
}
