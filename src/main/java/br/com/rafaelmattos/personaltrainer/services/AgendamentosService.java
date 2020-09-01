package br.com.rafaelmattos.personaltrainer.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rafaelmattos.personaltrainer.domain.Agendamentos;
import br.com.rafaelmattos.personaltrainer.domain.Alunos;
import br.com.rafaelmattos.personaltrainer.domain.NumeroAulas;
import br.com.rafaelmattos.personaltrainer.domain.enums.EstadoPagamento;
import br.com.rafaelmattos.personaltrainer.repositories.AgendamentosRepository;
import br.com.rafaelmattos.personaltrainer.repositories.NumeroAulasRepository;
import br.com.rafaelmattos.personaltrainer.repositories.PagamentosRepository;
import br.com.rafaelmattos.personaltrainer.security.UserSS;
import br.com.rafaelmattos.personaltrainer.services.exceptions.AuthorizationException;
import br.com.rafaelmattos.personaltrainer.services.exceptions.ObjectNotFoundException;

@Service
public class AgendamentosService {
	
		@Autowired
		private AgendamentosRepository agendamentosRepository;
		
		@Autowired
		private PagamentosRepository pagamentosRepository;
		
		@Autowired
		private NumeroAulasRepository numeroAulasRepository;
		
		@Autowired
		private AulasService aulasService;
		
		@Autowired
		private AlunosService alunosService;

		// buscar no banco de dados com base no id
		public Agendamentos find(Integer id) {
			Optional<Agendamentos> obj = agendamentosRepository.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Agendamento não encontrado! Id: " + id + ", Tipo: " + Agendamentos.class.getName()));
		}

		@Transactional
		public Agendamentos insert(Agendamentos obj) {
			obj.setId(null);
			obj.setDataRegistro(new Date());
			obj.setAlunos(alunosService.find(obj.getAlunos().getId()));
			obj.getPagamentos().setEstadoPagamento(EstadoPagamento.PENDENTE);
			obj.getPagamentos().setAgendamentos(obj);

			obj = agendamentosRepository.save(obj);
			pagamentosRepository.save(obj.getPagamentos());
			for (NumeroAulas na : obj.getNumeroAulas()) {
				na.setDesconto(0.0);
				na.setAulas(aulasService.find(na.getAulas().getId()));
				na.setValor(na.getAulas().getValor());
				na.setAgendamentos(obj);
			}
			numeroAulasRepository.saveAll(obj.getNumeroAulas());
			return obj;
		}

		public Page<Agendamentos> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
			UserSS user = UserService.authenticated();
			if (user == null) {
				throw new AuthorizationException("Acesso negado");
			}
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			Alunos alunos = alunosService.find(user.getId());
			return agendamentosRepository.findByAlunos(alunos, pageRequest);
		}
	}

	//Checklist:
	//- Criar AgendamentossServico
	//- Criar AgendamentossResource
	//- Proteger contra serialização Json cíclica

	//10. O alunos escolhe uma das opções:
	//10.1. Variante: Pagamento com boleto
	//10.2. Variante: Pagamento com cartão
	//11. [OUT] O sistema informa a confirmação do pedido (***).

	//Classe AgendamentossService:
	//Parâmetros:
	//pedido: um novo pedido a ser inserido na base de dados
	//Retorno:
	//A instância monitorada do pedido inserido
	//public Agendamentoss insert(Agendamentoss obj)

