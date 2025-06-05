package com.champs.downmidia.controller;

import java.io.File;
import java.io.FileInputStream;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.champs.downmidia.service.ConverterService;


@RestController
@RequestMapping("/") // Base URL for the converter controller
public class ConverterController {

    @Autowired
    public ConverterService converterService; // Injeção de dependência do serviço de conversão

    @GetMapping("/") //mostrar a mensagem de status da API
    public Map<String, String> statusRoot() {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", "API  Jogo do Downloader está ativa!");
        return resposta;
    }
    
    @PostMapping("/converter")
    public ResponseEntity<InputStreamResource> converter(
        @RequestParam String url, // URL do vídeo ou áudio a ser convertido
        @RequestParam String format, //'mp3' ou 'mp4'
        @RequestParam (defaultValue = "best") String quality) {

        try{
            // Chama o serviço de conversão e retorna a resposta
            File file = converterService.downloadAndConvert(url, format, quality);
            // Cria um InputStreamResource a partir do arquivo convertido
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            // Retorna a resposta com o arquivo convertido
            return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getName()+"\"")
            .contentLength(file.length())
            .contentType(format.equals("mp3") ? MediaType.parseMediaType("audio/mpeg") : MediaType.parseMediaType("video/mp4"))
            .body(resource);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
