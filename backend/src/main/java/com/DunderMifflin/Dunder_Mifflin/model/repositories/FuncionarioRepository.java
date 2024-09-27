package com.DunderMifflin.Dunder_Mifflin.model.repositories;

import com.DunderMifflin.Dunder_Mifflin.model.entities.Funcionario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository implements Repository <Funcionario, Integer> {
    public static final FuncionarioRepository current = new FuncionarioRepository();
    FuncionarioRepository() {}

    @Override
    public void create (Funcionario f) throws SQLException {
        String sql = "Insert into funcionario (nome_funcionario, cargo_funcionario, urlImagem_funcionario, setor_codigo) values (?, ?, ?, ?)";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setString(1, f.getNome());
        pstm.setString(2, f.getCargo());
        pstm.setString(3, f.getUrlImagem());
        pstm.setInt(4, f.getSetor());

        pstm.execute();
    }

    @Override
    public void update(Funcionario f) throws SQLException {
        String sql = "UPDATE funcionario SET nome_funcionario = ?, cargo_funcionario = ?, urlImagem_funcionario = ?, setor_codigo = ? WHERE codigo_funcionario = ?";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setString(1, f.getNome());
        pstm.setString(2, f.getCargo());
        pstm.setString(3, f.getUrlImagem());
        pstm.setInt(4, f.getSetor());
        pstm.setInt(5, f.getCodigo());

        pstm.execute();
    }

    @Override
    public Funcionario read(Integer k) throws SQLException {
        String sql = "select * from funcionario where codigo_funcionario =" + k;
        ResultSet result = ConnectionManager.getCurrentConnection().prepareStatement(sql).executeQuery();

        Funcionario f = null;

        if(result.next()){
            f = new Funcionario();

            f.setCodigo(result.getInt("codigo_funcionario"));
            f.setNome(result.getString("nome_funcionario"));
            f.setCargo(result.getString("cargo_funcionario"));
            f.setUrlImagem(result.getString("urlImagem_funcionario"));
            f.setSetor(result.getInt("setor_codigo"));
        }

        return f;
    }

    public List<Funcionario> findBySetor(int setorID) throws SQLException {
        String sql = "select * from funcionario where setor_codigo =" + setorID;
        ResultSet result = ConnectionManager.getCurrentConnection().prepareStatement(sql).executeQuery();

        List<Funcionario> funcionarios = new ArrayList<>();

        while (result.next()){
            Funcionario f = new Funcionario();

            f.setCodigo(result.getInt("codigo_funcionario"));
            f.setNome(result.getString("nome_funcionario"));
            f.setCargo(result.getString("cargo_funcionario"));
            f.setUrlImagem(result.getString("urlImagem_funcionario"));
            f.setSetor(result.getInt("setor_codigo"));

            funcionarios.add(f);
        }

        return funcionarios;
    }

    @Override
    public void delete(Integer k) throws SQLException {
        String sql = "delete from funcionario where codigo_funcionario =" + k;
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.execute();
    }

    public List<Funcionario> readAll() throws SQLException {
        String sql = "select * from funcionario";
        ResultSet result = ConnectionManager.getCurrentConnection().prepareStatement(sql).executeQuery();

        List<Funcionario> funcionarios = new ArrayList<Funcionario>();

        while (result.next()){
            Funcionario f = new Funcionario();

            f.setCodigo(result.getInt("codigo_funcionario"));
            f.setNome(result.getString("nome_funcionario"));
            f.setCargo(result.getString("cargo_funcionario"));
            f.setUrlImagem(result.getString("urlImagem_funcionario"));
            f.setSetor(result.getInt("setor_codigo"));

            funcionarios.add(f);
        }

        return funcionarios;
    }

}
