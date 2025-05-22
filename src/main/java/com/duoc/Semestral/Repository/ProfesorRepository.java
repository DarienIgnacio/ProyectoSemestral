package com.duoc.Semestral.Repository;


import com.duoc.Semestral.Model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public  interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
}
