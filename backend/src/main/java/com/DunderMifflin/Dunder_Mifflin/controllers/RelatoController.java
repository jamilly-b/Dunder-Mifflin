package com.DunderMifflin.Dunder_Mifflin.controllers;

import com.DunderMifflin.Dunder_Mifflin.DTO.RelatorioDTO;
import com.DunderMifflin.Dunder_Mifflin.model.entities.Relatorio;
import com.DunderMifflin.Dunder_Mifflin.model.repositories.RelatorioRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/relatorios")
@CrossOrigin("*")
public class RelatoController {

    @GetMapping
    public List<RelatorioDTO> relatorios (){
        try{
            return RelatorioRepository.current.converterParaDTO(RelatorioRepository.current.readAll());
        } catch (SQLException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }



}
