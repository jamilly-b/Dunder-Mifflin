package com.DunderMifflin.Dunder_Mifflin.model.repositories;

import com.DunderMifflin.Dunder_Mifflin.model.entities.Relatorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class RelatorioRepository implements Repository<Relatorio, Integer> {

    public static final RelatorioRepository current = new RelatorioRepository();

    private RelatorioRepository() {}

    @Override
    public void create(Relatorio r) throws SQLException {
        String sql = "INSERT INTO relatorio (problema, data, setor_codigo, funcionario_codigo) VALUES (?, ?, ?, ?)";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setString(1, r.getProblema());
        pstm.setDate(2, new java.sql.Date(r.getData().getTimeInMillis()));
        pstm.setInt(3, r.getSetor().getCodigo());
        pstm.setInt(4, r.getFuncionario().getCodigo());

        pstm.execute();
    }

    @Override
    public void update(Relatorio r) throws SQLException {
        String sql = "UPDATE relatorio SET problema = ?, data = ?, setor_codigo = ?, funcionario_codigo = ? WHERE codigo_relatorio = ?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setString(1, r.getProblema());
        pstm.setDate(2, new java.sql.Date(r.getData().getTimeInMillis()));
        pstm.setInt(3, r.getSetor().getCodigo());
        pstm.setInt(4, r.getFuncionario().getCodigo());
        pstm.setInt(5, r.getCodigo());

        pstm.execute();
    }

    @Override
    public Relatorio read(Integer k) throws SQLException {
        String sql  = "SELECT * FROM relatorio WHERE codigo_relatorio = " + k;

        ResultSet result = ConnectionManager.getCurrentConnection().prepareStatement(sql).executeQuery();

        Relatorio r = null;

        if (result.next()) {
            r = new Relatorio();
            r.setCodigo(result.getInt("codigo_relatorio"));
            r.setProblema(result.getString("problema"));

            Calendar data = Calendar.getInstance();
            data.setTime(result.getDate("data"));
            r.setData(data);

            r.setSetor(new SetorRepository().read(result.getInt("setor_codigo")));
            r.setFuncionario(new FuncionarioRepository().read(result.getInt("funcionario_codigo")));
        }

        return r;
    }

    @Override
    public void delete(Integer k) throws SQLException {
        String sql = "DELETE FROM relatorio WHERE codigo_relatorio = " + k;
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.execute();
    }

    public List<Relatorio> readAll() throws SQLException {
        String sql = "SELECT * FROM relatorio";
        ResultSet result = ConnectionManager.getCurrentConnection().prepareStatement(sql).executeQuery();

        List<Relatorio> relatorios = new ArrayList<>();

        while (result.next()) {
            Relatorio r = new Relatorio();
            r.setCodigo(result.getInt("codigo_relatorio"));
            r.setProblema(result.getString("problema"));

            Calendar data = Calendar.getInstance();
            data.setTime(result.getDate("data"));
            r.setData(data);

            r.setSetor(new SetorRepository().read(result.getInt("setor_codigo")));
            r.setFuncionario(new FuncionarioRepository().read(result.getInt("funcionario_codigo")));

            relatorios.add(r);
        }

        return relatorios;
    }
}