package com.DunderMifflin.Dunder_Mifflin.model.repositories;

import com.DunderMifflin.Dunder_Mifflin.DTO.RelatorioDTO;
import com.DunderMifflin.Dunder_Mifflin.model.entities.Relatorio;
import com.DunderMifflin.Dunder_Mifflin.model.entities.TipoRelatorio;
import com.DunderMifflin.Dunder_Mifflin.utils.DateFormatter;

import java.sql.*;
import java.time.LocalDate;
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

        pstm.setString(1, r.getTipo().name());
        pstm.setTimestamp(2, new java.sql.Timestamp(r.getData().getTimeInMillis()));
        pstm.setInt(3, r.getSetor());
        pstm.setInt(4, r.getFuncionario());

        pstm.execute();
    }

    @Override
    public void update(Relatorio r) throws SQLException {
        String sql = "UPDATE relatorio SET problema = ?, data = ?, setor_codigo = ?, funcionario_codigo = ? WHERE codigo_relatorio = ?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setString(1, r.getTipo().getDescricao());
        pstm.setTimestamp(2, new java.sql.Timestamp(r.getData().getTimeInMillis()));
        pstm.setInt(3, r.getSetor());
        pstm.setInt(4, r.getFuncionario());
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

            String tipoProblema = result.getString("tipo_relatorio");
            r.setTipo(TipoRelatorio.valueOf(tipoProblema));

            Calendar data = Calendar.getInstance();
            data.setTime(result.getDate("data"));
            r.setData(data);

            r.setSetor(result.getInt("setor_codigo"));
            r.setFuncionario(result.getInt("funcionario_codigo"));
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
            r.setCodigo(result.getInt("id"));

            String tipoProblema = result.getString("problema");
            r.setTipo(TipoRelatorio.valueOf(tipoProblema));

            Calendar data = Calendar.getInstance();
            data.setTime(result.getTimestamp("data"));
            String dataFormatada = DateFormatter.formatDateWithTime(data);
            r.setDataFormatada(dataFormatada);

            r.setSetor(result.getInt("setor_codigo"));
            r.setFuncionario(result.getInt("funcionario_codigo"));

            relatorios.add(r);
        }

        return relatorios;
    }

    public List<Relatorio> relatoriosDoFuncionario(int codigo) throws SQLException {
        String sql = "SELECT * FROM relatorio where funcionario_codigo = " + codigo;
        ResultSet result = ConnectionManager.getCurrentConnection().prepareStatement(sql).executeQuery();

        List<Relatorio> relatorios = new ArrayList<>();

        while (result.next()) {
            Relatorio r = new Relatorio();
            r.setCodigo(result.getInt("id"));

            String tipoProblema = result.getString("problema");
            r.setTipo(TipoRelatorio.valueOf(tipoProblema));

            Calendar data = Calendar.getInstance();
            data.setTime(result.getTimestamp("data"));
            String dataFormatada = DateFormatter.formatDateWithTime(data);
            r.setDataFormatada(dataFormatada);

            r.setSetor(result.getInt("setor_codigo"));
            r.setFuncionario(result.getInt("funcionario_codigo"));

            relatorios.add(r);
        }

        return relatorios;
    }

    public List<Relatorio> relatoriosDoSetor(int codigo) throws SQLException {
        String sql = "SELECT * FROM relatorio where setor_codigo = " + codigo;
        ResultSet result = ConnectionManager.getCurrentConnection().prepareStatement(sql).executeQuery();

        List<Relatorio> relatorios = new ArrayList<>();

        while (result.next()) {
            Relatorio r = new Relatorio();
            r.setCodigo(result.getInt("id"));

            String tipoProblema = result.getString("problema");
            r.setTipo(TipoRelatorio.valueOf(tipoProblema));

            Calendar data = Calendar.getInstance();
            data.setTime(result.getTimestamp("data"));
            String dataFormatada = DateFormatter.formatDateWithTime(data);
            r.setDataFormatada(dataFormatada);

            r.setSetor(result.getInt("setor_codigo"));
            r.setFuncionario(result.getInt("funcionario_codigo"));

            relatorios.add(r);
        }

        return relatorios;
    }

    public List<Relatorio> relatoriosPorDia(LocalDate data) throws SQLException {
        String sql = "SELECT * FROM relatorio WHERE DATE(data) = ?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setDate(1, Date.valueOf(data));

        ResultSet result = pstm.executeQuery();

        List<Relatorio> relatorios = new ArrayList<>();

        while (result.next()) {
            Relatorio r = new Relatorio();
            r.setCodigo(result.getInt("id"));

            String tipoProblema = result.getString("problema");
            r.setTipo(TipoRelatorio.valueOf(tipoProblema));

            Calendar dataRelatorio = Calendar.getInstance();
            dataRelatorio.setTime(result.getTimestamp("data"));
            String dataFormatada = DateFormatter.formatDateWithTime(dataRelatorio);
            r.setDataFormatada(dataFormatada);

            r.setSetor(result.getInt("setor_codigo"));
            r.setFuncionario(result.getInt("funcionario_codigo"));

            relatorios.add(r);
        }

        return relatorios;
    }

    public List<Relatorio> relatoriosDoSetorPorDia(int codigo, LocalDate data) throws SQLException {
        String sql = "SELECT * FROM relatorio WHERE setor_codigo = ? AND DATE(data) = ?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, codigo);
        pstm.setDate(2, Date.valueOf(data));

        ResultSet result = pstm.executeQuery();

        List<Relatorio> relatorios = new ArrayList<>();

        while (result.next()) {
            Relatorio r = new Relatorio();
            r.setCodigo(result.getInt("id"));

            String tipoProblema = result.getString("problema");
            r.setTipo(TipoRelatorio.valueOf(tipoProblema));

            Calendar dataRelatorio = Calendar.getInstance();
            dataRelatorio.setTime(result.getTimestamp("data"));
            String dataFormatada = DateFormatter.formatDateWithTime(dataRelatorio);
            r.setDataFormatada(dataFormatada);

            r.setSetor(result.getInt("setor_codigo"));
            r.setFuncionario(result.getInt("funcionario_codigo"));

            relatorios.add(r);
        }

        return relatorios;
    }

    public List<RelatorioDTO> converterParaDTO(List<Relatorio> relatorios) throws SQLException {
        List<RelatorioDTO> dtos = new ArrayList<>();
        for (Relatorio relatorio : relatorios) {
            RelatorioDTO dto = new RelatorioDTO();

            dto.setCodigo(relatorio.getCodigo());
            dto.setTipoProblema(relatorio.getTipo().getDescricao());
            dto.setData(relatorio.getDataFormatada());

            String nomeFuncionario = FuncionarioRepository.current.read(relatorio.getFuncionario()).getNome();
            dto.setFuncionario(nomeFuncionario);

            String nomeSetor = SetorRepository.current.read(relatorio.getFuncionario()).getNome();
            dto.setSetor(nomeSetor);

            dtos.add(dto);
        }
        return dtos;
    }

}