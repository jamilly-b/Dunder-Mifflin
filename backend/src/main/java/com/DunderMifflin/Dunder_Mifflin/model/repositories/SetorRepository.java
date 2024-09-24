package com.DunderMifflin.Dunder_Mifflin.model.repositories;

import java.sql.SQLException;

public class SetorRepository implements Repository {
    public static final SetorRepository current = new SetorRepository();
    private SetorRepository() {}

    @Override
    public void create(Object o) throws SQLException {

    }

    @Override
    public void update(Object o) throws SQLException {

    }

    @Override
    public Object read(Object k) throws SQLException {
        return null;
    }

    @Override
    public void delete(Object k) throws SQLException {

    }
}
