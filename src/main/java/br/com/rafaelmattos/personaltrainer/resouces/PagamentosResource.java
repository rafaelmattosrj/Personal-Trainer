package br.com.rafaelmattos.personaltrainer.resouces;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.rafaelmattos.personaltrainer.domain.Alunos;
import br.com.rafaelmattos.personaltrainer.dto.AlunosDTO;
import br.com.rafaelmattos.personaltrainer.dto.AlunosNewDTO;
import br.com.rafaelmattos.personaltrainer.services.AlunosService;

//TopicosController -> No @Autowired - TopicoRepository e CursoRepository
//-> Listar no @GetMapping – TopicoDTO, Topico e TopicoRepository
//(value = "/topicos", method = RequestMethod.GET)
//-> Cadastrar no @PostMapping - TopicoDTO, TopicoFrom, Topico, TopicoRepository e CursoRepository
//(value = "/topicos", method = RequestMethod.POST)
//-> Detalhar no @GetMapping("/{id}") – DetalhesDoTopicoDto, Topico e TopicoRepository.
//-> Atualizar no @PutMapping("/{id}") – TopicoDto, AtualizacaoTopicoFrom, Topico e TopicoRepository.
//-> Deletar no @DeleteMapping("/{id}") – Topico e TopicoRepository.

@RestController
@RequestMapping(value = "/pagamento")
public class PagamentosResource {
		
	// Instanciar o objeto
	@Autowired
	private AlunosService alunosService;

	//@ApiOperation(value="Busca por id")
	@Cacheable (value = "listaDeAlunos")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Alunos> find(@PathVariable Integer id) {
		Alunos obj = alunosService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//@GetMapping("/{id}") - {ID} É DINAMICO.
	//@PathVariable Long id - ASSOCIAR PELO @PATHVARIABLE COM "{ID}"
	//Optional<Topico> topico = topicoRepository.findById(id);
	//ID E ELE TE DEVOLVE UM METODO TOPICO QUE É A ENTIDADE.
	//if (topico.isPresent()) – SE ESTÁ PRESENTE.
	
	//Inserir
	//@ApiOperation(value="Insere aluno")
	@Transactional
	@CacheEvict(value = "listaDeAlunos", allEntries = true)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody AlunosNewDTO objDto) {
		Alunos obj = alunosService.fromDTO(objDto);
		obj = alunosService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	//@Transactional
	//COLOCAR SEMPRE NO POST, PUT E DELETE.
	//NÃO MANDA OS PARAMETROS VIA URL, OS PARAMETROS VÊM NO CORPO DA REQUISIÇÃO.
	//@CacheEvict(value = "listaDeTopicos", allEntries = true)
	//COLOCAR SEMPRE NO POST, PUT E DELETE.
	//LIMPE E INVALIDE DETERMINADO CACHE / LIMPAR TODOS OS REGISTROS PARA ATULIZAR TUDO E DEIXAR O CACHE ZERADO DE NOVO. REATUALIZAR.
	//public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
	//RESPONSEENTITY<TOPICODTO> -> NÃO SE DEVOLVE A ENTIDADE (CLASSE DE DOMINIO).
	//@REQUESTBODY -> INDICAR AO SPRING QUE OS PARAMETROS ENVIADOS NO CORPO
	//DA REQUISIÇÃO DEVEM SER ATRIBUÍDOS AO PARÂMETRO DO MÉTODO, E NÃO DO PARÂMETRO DA URL.
	//@VALID -> RODAR AS VALIDAÇÕES DO BEAN VALIDATION.
	//URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
	//{ID} FAZ DINAMICO. BUILDANDEXPAND PARA SUBSTITUIT O {ID}, VAI REQUISITAR O ID DO TOPICO CRIADO NO BANCO DE DADOS.
	//TOURI CONVERTE E TRANSFORMA A URL CERTA.
	//return ResponseEntity.created(uri).body(new AlunooDto(topico));
	//CRIA O CORPO DA RESPOSTA. RETORNO 201, CABEÇALHO LOCATION E CORPO DA RESPOSTA SENDO UMA REPRESENTAÇÃO DO RECURSO QUE ACABOU DE SER CRIADO.

	
	//@ApiOperation(value="Atualiza aluno")
	@Transactional
	@CacheEvict(value = "listaDeAlunos", allEntries = true)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Alunos> find(@Valid @RequestBody AlunosDTO objDto, @PathVariable Integer id) {
		Alunos obj = alunosService.fromDTO(objDto);
		obj.setId(id);
		obj = alunosService.update(obj);
		return ResponseEntity.noContent().build();
	}

	//@PutMapping("/{id}") – SOBRESCREVE POR INTEIRO.
	//@Transactional
	//GERALMENTE QDO SALVAR, ALTERAR E EXCLUIR. COMITA A TRANSAÇÃO NO FINAL DO METODO.
	//public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
	//PARA CADA RETORNO DE STATUS CODE DO HTTP, UTILIZAMOS UMA FUNÇÃO DO RESPONSEENTITY.
	
	//@ApiOperation(value="Remove cliente")
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@Transactional
	@CacheEvict(value = "listaDeAlunos", allEntries = true)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		alunosService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//@ApiOperation(value="Retorna todas clientes")
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AlunosDTO>> findAll() {
		List<Alunos> list = alunosService.findAll();
		List<AlunosDTO> listDto = list.stream().map(obj -> new AlunosDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	//@ApiOperation(value="Retorna todas clientes com paginação")
	//@PreAuthorize("hasAnyRole('ADMIN')")
	// categorias/page?page=01&linesPerPage=20...
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<AlunosDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Alunos> list = alunosService.findPage(page, linesPerPage, orderBy, direction);
		Page<AlunosDTO> listDto = list.map(obj -> new AlunosDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	//@GetMapping
	//FAZER LEITURA. MAPEAR (VALUE = "/TOPICOS", METHOD = REQUESTMETHOD.GET)
	//@Cacheable
	//IMPORTAR O org.spring...
	//@RequestParam (required = false)
	//PARÂMETRO DE REQUEST. O SPRING CONSIDERA QUE O PARÂMETRO É OBRIGATÓRIO.
	//(required = false) - NOMECURSO NÃO É OBRIGATORIO.
	//Pageable paginacao = PageRequest.of(pagina, qtd);
	//IMPORTAR O ...data.domain.
	//ASC - CRESCEMTE / DESC - DECRESCENTE
	//List<Topico> topicos = topicoRepository.findAll();
	//LISTA DE TOPICOS. FINDALL FAZ UMA CONSULTA, CARREGANDO TODOS OS REGISTROS DO
	//BANCO DE DADOS.
	//} else { - FILTRAR.
	//"FOI TROCADO A LIST POR PAGE"
	//Page - DEVOLVE REGISTROS, INFORMAÇÕES NO JSON DE RESPOSTA, COMO Nº TOTAL DE REGISTROS E PÁGS.
	//@PageableDefault(sort = "id", direction =  Direction.DESC)
	//PADRONIZAR O DEFAULT PARA PAGINAÇÃO http://localhost:8080/topicos?page=0&size=10 
	
}

