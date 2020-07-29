package com.agustincperalta.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.agustincperalta.domain.Voto;

public interface VotoRepository extends CrudRepository<Voto, Long> {

  @Query(
      value = "SELECT v.* FROM Opcion o, Voto v WHERE o.POLL_ID = ?1 and v.OPTION_ID = o.OPTION_ID",
      nativeQuery = true)
  public Iterable<Voto> findByEncuesta(Long encuestaId);
}
