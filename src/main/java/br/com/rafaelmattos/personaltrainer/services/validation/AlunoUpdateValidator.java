package br.com.rafaelmattos.personaltrainer.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.rafaelmattos.personaltrainer.domain.Alunos;
import br.com.rafaelmattos.personaltrainer.dto.AlunosDTO;
import br.com.rafaelmattos.personaltrainer.repositories.AlunosRepository;
import br.com.rafaelmattos.personaltrainer.resouces.exceptions.FieldMessage;

public class AlunoUpdateValidator implements ConstraintValidator<AlunoUpdate, AlunosDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private AlunosRepository repo;
	
	@Override
	public void initialize(AlunoUpdate ann) {
	}

	@Override
	public boolean isValid(AlunosDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings(value = "unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
// inclua os testes aqui, inserindo erros na lista
		
		Alunos aux = repo.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}