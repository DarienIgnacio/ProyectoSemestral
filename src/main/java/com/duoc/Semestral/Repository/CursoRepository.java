package com.duoc.Semestral.Repository;

import com.duoc.Semestral.Model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

    Integer id(int id);
}






