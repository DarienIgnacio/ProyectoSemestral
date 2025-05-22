package com.duoc.Semestral.Repository;

import com.duoc.Semestral.Model.Inscripciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripciones, Integer> {
}
