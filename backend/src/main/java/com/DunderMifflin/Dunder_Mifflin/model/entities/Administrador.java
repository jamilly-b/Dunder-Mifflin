package com.DunderMifflin.Dunder_Mifflin.model.entities;

public class Administrador extends Funcionario {
    String email;
    String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
