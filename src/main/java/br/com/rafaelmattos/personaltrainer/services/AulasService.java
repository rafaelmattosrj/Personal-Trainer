package br.com.rafaelmattos.personaltrainer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.rafaelmattos.personaltrainer.domain.Aulas;
import br.com.rafaelmattos.personaltrainer.repositories.AulasRepository;
import br.com.rafaelmattos.personaltrainer.services.exceptions.ObjectNotFoundException;

@Service
public class AulasService {

		@Autowired
		private AulasRepository aulasRepository;

		// buscar no banco de dados com base no id
		public Aulas find(Integer id) {
			Optional<Aulas> obj = aulasRepository.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Aula não encontrada! Id: " + id + ", Tipo: " + Aulas.class.getName()));
		}

		public Page<Aulas> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return aulasRepository.findAll(pageRequest);
		}
	}

	//Parâmetros:
	//nome: um trecho de nome de produto
	//ids: uma lista de códigos de categorias
	//Retorno:
	//A listagem de produtos que contém o trecho de nome dado e que pertencem a pelo menos uma das
	//categorias dadas