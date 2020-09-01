package br.com.rafaelmattos.personaltrainer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rafaelmattos.personaltrainer.domain.Pagamentos;

@Repository
public interface PagamentosRepository extends JpaRepository<Pagamentos, Integer> {

}
