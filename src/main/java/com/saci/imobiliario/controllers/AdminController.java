package com.saci.imobiliario.controllers;



import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.saci.imobiliario.dtos.SaciCadastroCorretorDto;
import com.saci.imobiliario.models.CadastrarCorretor;
import com.saci.imobiliario.repositorys.SaciRepositorio;


import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class AdminController {

    final SaciRepositorio saciRepositorio;

    AdminController(SaciRepositorio saciRepositorio) {
        this.saciRepositorio = saciRepositorio;
    }
    @PostMapping("/cadastro")
    public ResponseEntity<String> saveCorretor(SaciCadastroCorretorDto saciCadastroCorretorDto) {
    var cadastrarCorretor = new CadastrarCorretor();
    BeanUtils.copyProperties(saciCadastroCorretorDto, cadastrarCorretor);

    saciRepositorio.save(cadastrarCorretor);

    // Retorno da mensagem customizada com redirecionamento em JavaScript
    String mensagem = "Cadastro salvo com sucesso!";
    return ResponseEntity.status(HttpStatus.OK).body(
        "<script>" +
        "   alert('" + mensagem + "');" +
        "   window.location.href = '/';" +
        "</script>"
    );
}

}