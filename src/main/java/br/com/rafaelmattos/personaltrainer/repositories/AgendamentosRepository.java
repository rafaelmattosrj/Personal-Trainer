package br.com.rafaelmattos.personaltrainer.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.rafaelmattos.personaltrainer.domain.Agendamentos;
import br.com.rafaelmattos.personaltrainer.domain.Alunos;

@Repository
public interface AgendamentosRepository extends JpaRepository<Agendamentos, Integer> {

	@Transactional(readOnly = true)
	Page<Agendamentos> findByAlunos(Alunos alunos, PageRequest pageRequest);

}
