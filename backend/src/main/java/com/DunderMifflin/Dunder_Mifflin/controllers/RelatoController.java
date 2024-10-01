package com.DunderMifflin.Dunder_Mifflin.controllers;

import com.DunderMifflin.Dunder_Mifflin.DTO.RelatorioDTO;
import com.DunderMifflin.Dunder_Mifflin.model.entities.Relatorio;
import com.DunderMifflin.Dunder_Mifflin.model.repositories.RelatorioRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
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

    @GetMapping("/setor/{codigo}")
    public List<RelatorioDTO> filtrarRelatoriosPorSetor(@PathVariable int codigo){
        try {
            return RelatorioRepository.current.converterParaDTO(RelatorioRepository.current.relatoriosDoSetor(codigo));
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @GetMapping("/data")
    public List<RelatorioDTO> filtrarRelatoriosPorDia(@RequestParam("data") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data) {
        try {
            return RelatorioRepository.current.converterParaDTO(RelatorioRepository.current.relatoriosPorDia(data));
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @GetMapping("/setor/{codigo}/data")
    public List<RelatorioDTO> filtrarRelatoriosPorDiaESetor(@PathVariable int codigo, @RequestParam("data") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data) {
        try {
            return RelatorioRepository.current.converterParaDTO(RelatorioRepository.current.relatoriosDoSetorPorDia(codigo, data));
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
