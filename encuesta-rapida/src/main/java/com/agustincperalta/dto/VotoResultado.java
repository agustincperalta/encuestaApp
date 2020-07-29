package com.agustincperalta.dto;

import java.util.Collection;
import lombok.Data;

@Data
public class VotoResultado {
  private int votosTotales;
  private Collection<OpcionCount> resultados;
}
