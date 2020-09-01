package br.com.rafaelmattos.personaltrainer.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rafaelmattos.personaltrainer.domain.Agendamentos;
import br.com.rafaelmattos.personaltrainer.domain.Alunos;
import br.com.rafaelmattos.personaltrainer.domain.Aulas;
import br.com.rafaelmattos.personaltrainer.domain.Enderecos;
import br.com.rafaelmattos.personaltrainer.domain.NumeroAulas;
import br.com.rafaelmattos.personaltrainer.domain.Pagamentos;
import br.com.rafaelmattos.personaltrainer.domain.enums.EstadoPagamento;
import br.com.rafaelmattos.personaltrainer.domain.enums.Perfil;
import br.com.rafaelmattos.personaltrainer.repositories.AgendamentosRepository;
import br.com.rafaelmattos.personaltrainer.repositories.AlunosRepository;
import br.com.rafaelmattos.personaltrainer.repositories.EnderecosRepository;
import br.com.rafaelmattos.personaltrainer.repositories.NumeroAulasRepository;
import br.com.rafaelmattos.personaltrainer.repositories.PagamentosRepository;

@Service
public class TestService {

		//@Autowired
		//private BCryptPasswordEncoder pe;
		@Autowired
		private AgendamentosRepository agendamentosRepository;
		@Autowired
		private EnderecosRepository enderecosRepository;
		@Autowired
		private AlunosRepository alunosRepository;
		@Autowired
		private PagamentosRepository pagamentosRepository;
		@Autowired
		private NumeroAulasRepository numeroAulasRepository;
		
		public void istantiateTestDatabase () throws ParseException {
			
			Alunos alu1 = new Alunos(null, null, "Rafael Mattos", "rafaelrj@live.com", "04973412040", "26/07/1989", "123");
			alu1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
			alu1.addPerfil(Perfil.ADMIN);
			
			Alunos alu2 = new Alunos(null, null, "Paulo Eduardo", "peom@gmail.com", "44725473090", "14/11/1989", "123");
			alu2.getTelefones().addAll(Arrays.asList("93883321", "34252625"));
			alu2.addPerfil(Perfil.ALUNO);
			
			Alunos alu3 = new Alunos(null, null, "Gemeos Correia", "gemeos@gmail.com", "56255354008", "30/07/1990", "123");
			alu3.getTelefones().addAll(Arrays.asList("93883323", "34252627"));
			alu3.addPerfil(Perfil.ALUNO);
			
			Enderecos end1 = new Enderecos(null, null, "Rua Santa Clara", "100", "Apart 701", "Copacabana", "22040010", "Rio de Janeiro", "RJ", alu1); 
			Enderecos end2 = new Enderecos(null, null, "Rua Anita Garibalde", "51", "Apart 801", "Copacabana", "22041010", "Rio de Janeiro", "RJ", alu2); 
			Enderecos end3 = new Enderecos(null, null, "Rua Tonelero", "69", "Apart 901", "Copacabana", "22042010", "Rio de Janeiro", "RJ", alu3); 
						
			alunosRepository.saveAll(Arrays.asList(alu1, alu2, alu3));
			enderecosRepository.saveAll(Arrays.asList(end1, end2, end3));
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			Agendamentos age1 = new Agendamentos(null, null, sdf.parse("01/09/2017 10:00"), alu2);
			Agendamentos age2 = new Agendamentos(null, null, sdf.parse("02/09/2017 18:00"), alu3);
			Agendamentos age3 = new Agendamentos(null, null, sdf.parse("03/09/2017 10:00"), alu2);
			Agendamentos age4 = new Agendamentos(null, null, sdf.parse("04/09/2017 18:00"), alu3);
			Agendamentos age5 = new Agendamentos(null, null, sdf.parse("05/09/2017 10:00"), alu2);
			
			alu2.getAgendamentos().addAll(Arrays.asList(age1, age3, age5));
			alu3.getAgendamentos().addAll(Arrays.asList(age2, age4));
			
			Pagamentos pag1 = new Pagamentos(null, null, sdf.parse("05/09/2017 00:00"), sdf.parse("01/09/2020 18:00"), "Transferência Bancária", EstadoPagamento.PAGO, age1);
			Pagamentos pag2 = new Pagamentos(null, null, sdf.parse("05/09/2017 00:00"), sdf.parse("01/09/2020 18:00"), "Transferência Bancária", EstadoPagamento.PAGO, age2);
			Pagamentos pag3 = new Pagamentos(null, null, sdf.parse("05/09/2017 00:00"), sdf.parse("01/09/2020 18:00"), "Transferência Bancária", EstadoPagamento.PAGO, age2);
			Pagamentos pag4 = new Pagamentos(null, null, sdf.parse("05/09/2017 00:00"), sdf.parse("02/09/2020 10:00"), "Transferência Bancária", EstadoPagamento.PAGO, age3);
			Pagamentos pag5 = new Pagamentos(null, null, sdf.parse("05/09/2017 00:00"), sdf.parse("02/09/2020 10:00"), "Transferência Bancária", EstadoPagamento.PAGO, age3);
			
			agendamentosRepository.saveAll(Arrays.asList(age1, age2, age3, age4, age5));
			pagamentosRepository.saveAll(Arrays.asList(pag1, pag2, pag3, pag4, pag5));

			Aulas aul1 = new Aulas(null, null, null, "Praia","Funcional", 80.00);
			Aulas aul2 = new Aulas(null, null, null, "Praia","Funcional", 80.00);
			Aulas aul3 = new Aulas(null, null, null, "Praia","Funcional", 80.00);
			Aulas aul4 = new Aulas(null, null, null, "Academia","Musculação", 80.00);
			Aulas aul5 = new Aulas(null, null, null, "Academia","Musculação", 80.00);
			
			NumeroAulas na1 = new NumeroAulas(age1, aul1, 0.00, 3, 80.00);
			NumeroAulas na2 = new NumeroAulas(age1, aul1, 0.00, 2, 80.00);
			
			age1.getNumeroAulas().addAll(Arrays.asList(na1));
			age2.getNumeroAulas().addAll(Arrays.asList(na2));
			age3.getNumeroAulas().addAll(Arrays.asList(na1));
			age4.getNumeroAulas().addAll(Arrays.asList(na2));
			age5.getNumeroAulas().addAll(Arrays.asList(na1));

			aul1.getNumeroAulas().addAll(Arrays.asList(na1));
			aul2.getNumeroAulas().addAll(Arrays.asList(na1));
			aul3.getNumeroAulas().addAll(Arrays.asList(na1));
			aul4.getNumeroAulas().addAll(Arrays.asList(na2));
			aul5.getNumeroAulas().addAll(Arrays.asList(na2));
			
			numeroAulasRepository.saveAll(Arrays.asList(na1, na2));
		}	
	}
