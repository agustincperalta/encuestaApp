package com.agustincperalta.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Opcion {
  @Id
  @GeneratedValue
  @Column(name = "OPTION_ID")
  private Long id;
  @Column(name = "OPTION_VALUE")
  private String value;
}
