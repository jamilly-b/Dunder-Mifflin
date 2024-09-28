package com.DunderMifflin.Dunder_Mifflin.model.entities;

public enum TipoRelatorio {
    FALHA_EQUIPAMENTO("Falha em equipamento"),
    FALTA_INSUMO("Falta de insumo"),
    ASSEDIO("Assédio"),
    FALHA_PESSOAL("Falha pessoal"),
    ACIDENTE("Acidente");

    private final String descricao;

    TipoRelatorio(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
