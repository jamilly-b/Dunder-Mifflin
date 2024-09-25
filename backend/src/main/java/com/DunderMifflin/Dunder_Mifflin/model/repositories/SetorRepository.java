package com.DunderMifflin.Dunder_Mifflin.model.repositories;

import com.DunderMifflin.Dunder_Mifflin.model.entities.Setor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SetorRepository implements Repository <Setor, Integer> {
    // public static final SetorRepository current = new SetorRepository();
    SetorRepository() {}

    @Override
    public void create(Setor s) throws SQLException {
        String sql = "insert into setor(nome) values (?)";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setString(1, s.getNome());

        pstm.execute();
    }

    @Override
    public void update(Setor s) throws SQLException {
        String sql = "update setor(nome) values (?)";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setString(1, s.getNome());

        pstm.execute();
    }

    @Override
    public Setor read(Integer k) throws SQLException {
        String sql  = "select * from setor where codigo_setor = "+k;

        ResultSet result = ConnectionManager.getCurrentConnection().prepareStatement(sql).executeQuery();

        Setor s = null;

        if(result.next()) {

            s = new Setor();
            s.setNome(result.getString("nome"));
        }

        return s;
    }

    @Override
    public void delete(Integer k) throws SQLException {
        String sql = "delete from setor where codigo_setor = "+k;
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.execute();
    }

    public List<Setor> readAll() throws SQLException {
        String sql  = "select * from setor";

        ResultSet result = ConnectionManager.getCurrentConnection().prepareStatement(sql).executeQuery();

        Setor s = null;

        List<Setor> setores = new ArrayList<Setor>();

        if(result.next()) {

            s = new Setor();
            s.setNome(result.getString("nome"));

            setores.add(s);
        }

        return setores;
    }
}
