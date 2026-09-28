package com.example.CandidatosAPI.controller;

import com.example.CandidatosAPI.service.CandidatosTseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.CandidatosAPI.model.Candidato;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class CandidatosTseController {

    private final CandidatosTseService candidatosTseService;

    public CandidatosTseController(CandidatosTseService candidatosTseService) {
        this.candidatosTseService = candidatosTseService;
    }

    @GetMapping("/")
    public String index(
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) String escolaridade,
            @RequestParam(required = false) Integer idadeMin,
            @RequestParam(required = false) Integer idadeMax,
            Model model) {

        List<Candidato> candidatos = candidatosTseService.filtrarPerfil(genero, escolaridade,  idadeMin, idadeMax);
            

        model.addAttribute("candidatos", candidatos);
        model.addAttribute("totalEncontrado", candidatos.size());

        
        model.addAttribute("generos", candidatosTseService.listarGeneros());
        model.addAttribute("escolaridade", candidatosTseService.listarEscolaridades());
        

    
        model.addAttribute("generoSelecionado", genero == null ? "" : genero);
        model.addAttribute("escolaridadeSelecionado", escolaridade == null ? "" : escolaridade);
        model.addAttribute("idadeMinSelecionado", idadeMin == null ? "" : idadeMin);
        model.addAttribute("idadeMaxSelecionado", idadeMax == null ? "" : idadeMax);

            

    return "index";
    }
}
