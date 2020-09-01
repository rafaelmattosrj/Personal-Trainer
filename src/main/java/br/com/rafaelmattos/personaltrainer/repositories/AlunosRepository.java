package br.com.rafaelmattos.personaltrainer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.rafaelmattos.personaltrainer.domain.Alunos;

@Repository
public interface AlunosRepository extends JpaRepository<Alunos, Integer> {

	@Transactional(readOnly = true)
	Alunos findByEmail(String email);

}
