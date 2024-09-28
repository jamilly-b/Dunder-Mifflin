package com.DunderMifflin.Dunder_Mifflin.controllers;

import com.DunderMifflin.Dunder_Mifflin.model.entities.Funcionario;
import com.DunderMifflin.Dunder_Mifflin.model.entities.Relatorio;
import com.DunderMifflin.Dunder_Mifflin.model.repositories.FuncionarioRepository;
import com.DunderMifflin.Dunder_Mifflin.model.repositories.RelatorioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin("*")
public class FuncionarioController {

    @GetMapping
    public List<Funcionario> readFuncionarios(){
        try{
            return FuncionarioRepository.current.readAll();
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @PostMapping
    public ResponseEntity<?> createFuncionario(@RequestBody Funcionario f){
        try{
            FuncionarioRepository.current.create(f);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity updateFuncionario(@RequestBody Funcionario f) {
        try {
            FuncionarioRepository.current.update(f);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Funcionario> readFuncionario(@PathVariable int codigo){
        try{
            Funcionario f = FuncionarioRepository.current.read(codigo);

            if(f == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(f, HttpStatus.OK);

        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable int codigo){
        try {
            FuncionarioRepository.current.delete(codigo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{codigo}/relatorios")
    public ResponseEntity<?> criarRelatorioParaFuncionario(@PathVariable int codigo, @RequestBody Relatorio relatorio) {
        try {
            Funcionario funcionario = FuncionarioRepository.current.read(codigo);
            if (funcionario == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            if (relatorio.getData() == null) {
                Calendar dataAtual = Calendar.getInstance();
                relatorio.setData(dataAtual);
            }

            relatorio.setFuncionario(funcionario.getCodigo());
            relatorio.setSetor(funcionario.getSetor());

            RelatorioRepository.current.create(relatorio);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{codigo}/relatorios")
    public List<Relatorio> lerRelatoriosDoFuncionario(@PathVariable int codigo){
        try{
            return RelatorioRepository.current.relatoriosDoFuncionario(codigo);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
