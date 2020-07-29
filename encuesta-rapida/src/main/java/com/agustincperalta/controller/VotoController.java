package com.agustincperalta.controller;

import javax.inject.Inject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.agustincperalta.domain.Voto;
import com.agustincperalta.repository.VotoRepository;

@RestController
public class VotoController {

  @Inject
  private VotoRepository votoRepository;


  @PostMapping("/encuestas/{encuestaId}/votos")
  public HttpHeaders createVoto(@PathVariable Long encuestaId, @RequestBody Voto voto) {
    voto = votoRepository.save(voto);
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(voto.getId()).toUri());

    return responseHeaders;
  }

  @GetMapping("/encuestas/{encuestaId}/votos")
  public Iterable<Voto> getAllVotos(@PathVariable String encuestaId) {
    return votoRepository.findAll();
  }

  @PostMapping("/encuestas/{encuestaId}/votos/{votoId}")
  public ResponseEntity<?> getOneVoto(@PathVariable String encuestaId, @PathVariable Long votoId) {
    return new ResponseEntity<>(votoRepository.findById(votoId), HttpStatus.CREATED);
  }


}
