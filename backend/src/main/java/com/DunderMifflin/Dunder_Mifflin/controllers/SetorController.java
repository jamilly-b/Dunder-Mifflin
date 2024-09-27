package com.DunderMifflin.Dunder_Mifflin.controllers;

import com.DunderMifflin.Dunder_Mifflin.model.entities.Funcionario;
import com.DunderMifflin.Dunder_Mifflin.model.entities.Setor;
import com.DunderMifflin.Dunder_Mifflin.model.repositories.FuncionarioRepository;
import com.DunderMifflin.Dunder_Mifflin.model.repositories.SetorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/setores")
@CrossOrigin("*")
public class SetorController {

    @GetMapping
    public List<Setor> readSetores() {
        try{
            return SetorRepository.current.readAll();
        } catch (SQLException e){
            return new ArrayList<>();
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Setor s){
        try{
            SetorRepository.current.create(s);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Setor s){
        try{
            System.out.println("Atualizando setor: " + s.getCodigo() + " - " + s.getNome());
            SetorRepository.current.update(s);
            System.out.println("setor atualizado: " + s.getCodigo() + " - " + s.getNome());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Setor> readSetor(@PathVariable("codigo") int codigo) {
        try {
            Setor s = SetorRepository.current.read(codigo);

            if (s == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Setor>(s, HttpStatus.OK);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deleteSetor(@PathVariable("codigo") int codigo){
        try{
            SetorRepository.current.delete(codigo);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{codigo}/funcionarios")
    public List<Funcionario> readFuncionariosSetor(@PathVariable int codigo){
        try{
            return FuncionarioRepository.current.findBySetor(codigo);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
}