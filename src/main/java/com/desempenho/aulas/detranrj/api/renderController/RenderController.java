package com.desempenho.aulas.detranrj.api.renderController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("render/api")
public class RenderController {

    @GetMapping()
    public ResponseEntity<String> findAll() {
        return ResponseEntity.ok().body("Render API");
    }

}