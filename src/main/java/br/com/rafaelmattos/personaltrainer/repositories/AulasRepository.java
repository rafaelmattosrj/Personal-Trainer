package br.com.rafaelmattos.personaltrainer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rafaelmattos.personaltrainer.domain.Aulas;

@Repository
public interface AulasRepository extends JpaRepository<Aulas, Integer> {

}
