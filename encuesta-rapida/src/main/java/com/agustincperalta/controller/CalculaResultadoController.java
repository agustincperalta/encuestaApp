package com.agustincperalta.controller;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.agustincperalta.domain.Voto;
import com.agustincperalta.dto.OpcionCount;
import com.agustincperalta.dto.VotoResultado;
import com.agustincperalta.repository.VotoRepository;

@RestController
public class CalculaResultadoController {

  @Inject
  private VotoRepository votoRepository;


  @GetMapping("/obten-resultado")
  public ResponseEntity<?> obtenResult(@RequestParam Long encuestaId) {
    VotoResultado resultado = new VotoResultado();
    Iterable<Voto> allVotos = votoRepository.findByEncuesta(encuestaId);


    int votosTotales = 0;
    Map<Long, OpcionCount> mapaTemporal = new HashMap<>();
    for (Voto v : allVotos) {
      votosTotales++;
      OpcionCount opcionCount = mapaTemporal.get(v.getOpcion().getId());
      if (opcionCount == null) {
        opcionCount = new OpcionCount();
        opcionCount.setOptionId(v.getOpcion().getId());
        mapaTemporal.put(v.getOpcion().getId(), opcionCount);
      }
      opcionCount.setCount(opcionCount.getCount() + 1);

    }

    resultado.setVotosTotales(votosTotales);
    resultado.setResultados(mapaTemporal.values());
    return new ResponseEntity<VotoResultado>(resultado, HttpStatus.OK);
  }
}
