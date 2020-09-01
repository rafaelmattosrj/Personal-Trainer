package br.com.rafaelmattos.personaltrainer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rafaelmattos.personaltrainer.domain.Alunos;
import br.com.rafaelmattos.personaltrainer.domain.enums.Perfil;
import br.com.rafaelmattos.personaltrainer.dto.AlunosDTO;
import br.com.rafaelmattos.personaltrainer.dto.AlunosNewDTO;
import br.com.rafaelmattos.personaltrainer.repositories.AlunosRepository;
import br.com.rafaelmattos.personaltrainer.repositories.EnderecosRepository;
import br.com.rafaelmattos.personaltrainer.security.UserSS;
import br.com.rafaelmattos.personaltrainer.services.exceptions.AuthorizationException;
import br.com.rafaelmattos.personaltrainer.services.exceptions.DataIntegrityException;
import br.com.rafaelmattos.personaltrainer.services.exceptions.ObjectNotFoundException;

@Service
public class AlunosService {

	//@Autowired
	//private BCryptPasswordEncoder pe;

	@Autowired
	private AlunosRepository alunosRepository;
	
	@Autowired
	private EnderecosRepository enderecosRepository;
	
	// buscar no banco de dados com base no id
	public Alunos find(Integer id) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Alunos> obj = alunosRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Aluno não encontrado! Id: " + id + ", Tipo: " + Alunos.class.getName()));
	}

	// Inserir
	@Transactional
	public Alunos insert(Alunos obj) {
		obj.setId(null);
		obj = alunosRepository.save(obj);
		enderecosRepository.save(obj.getEnderecos());
		return obj;
	}

	// Atualizar
	public Alunos update(Alunos obj) {
		Alunos newObj = find(obj.getId());
		updateData(newObj, obj);
		return alunosRepository.save(newObj);
	}

	// Deletar
	public void delete(Integer id) {
		find(id);
		try {
			alunosRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir!");
		}
	}

	// Buscar
	public List<Alunos> findAll() {
		return alunosRepository.findAll();
	}

	// Paginação
	public Page<Alunos> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return alunosRepository.findAll(pageRequest);
	}

	// Construção
	public Alunos fromDTO(AlunosDTO objDto) {
		return new Alunos(objDto.getId(), objDto.getUuid(), objDto.getNomeCompleto(), objDto.getEmail(), null, null, null);
	}

	// Construção
	public Alunos fromDTO(AlunosNewDTO objDto) {
		Alunos alu = new Alunos(null, null, objDto.getNomeCompleto(), objDto.getEmail(), 
				objDto.getCpf(), objDto.getDataDeNascimento(),
				//pe.encode(
						objDto.getSenha());
		alu.getEnderecos();	
		alu.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			alu.getTelefones().add(objDto.getTelefone2());
		}
		return alu;
	}

	private void updateData(Alunos newObj, Alunos obj) {
		newObj.setNomeCompleto(obj.getNomeCompleto());
		newObj.setEmail(obj.getEmail());
	}
}
