package br.com.rafaelmattos.personaltrainer.resouces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafaelmattos.personaltrainer.domain.Aulas;
import br.com.rafaelmattos.personaltrainer.dto.AulasDTO;
import br.com.rafaelmattos.personaltrainer.resouces.util.URL;
import br.com.rafaelmattos.personaltrainer.services.AulasService;

@RestController
@RequestMapping(value = "/aula")
public class AulasResource {

	//Instanciar o objeto
	@Autowired
	private AulasService aulasService;
	
	//@ApiOperation(value="Busca por id")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Aulas> find(@PathVariable Integer id) {
		Aulas obj = aulasService.find(id);
		return ResponseEntity.ok().body(obj);	
	}
	
	//@ApiOperation(value="Retorna todos produtos com paginação")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<AulasDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Aulas> list = aulasService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<AulasDTO> listDto = list.map(obj -> new AulasDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}