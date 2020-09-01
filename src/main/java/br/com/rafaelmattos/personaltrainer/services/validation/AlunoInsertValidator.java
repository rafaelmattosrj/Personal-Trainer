package br.com.rafaelmattos.personaltrainer.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.rafaelmattos.personaltrainer.domain.Alunos;
import br.com.rafaelmattos.personaltrainer.dto.AlunosNewDTO;
import br.com.rafaelmattos.personaltrainer.repositories.AlunosRepository;
import br.com.rafaelmattos.personaltrainer.resouces.exceptions.FieldMessage;
import br.com.rafaelmattos.personaltrainer.services.validation.util.BR;

public class AlunoInsertValidator implements ConstraintValidator<AlunoInsert, AlunosNewDTO> {

	@Autowired
	private AlunosRepository repo;

	@Override
	public void initialize(AlunoInsert ann) {
	}

	@Override
	public boolean isValid(AlunosNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
// inclua os testes aqui, inserindo erros na lista

		if  (!BR.isValidCPF(objDto.getCpf())) {
			list.add(new FieldMessage("cpf", "CPF inválido"));
		}

		Alunos aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
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