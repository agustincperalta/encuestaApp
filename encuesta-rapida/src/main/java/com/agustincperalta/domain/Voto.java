package com.agustincperalta.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Voto {
  @Id
  @GeneratedValue
  @Column(name = "VOTE_ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "OPTION_ID")
  private Opcion opcion;
}
