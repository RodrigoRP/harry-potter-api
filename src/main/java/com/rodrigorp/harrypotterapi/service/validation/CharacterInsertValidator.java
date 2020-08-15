package com.rodrigorp.harrypotterapi.service.validation;

import com.rodrigorp.harrypotterapi.controller.exception.FieldMessage;
import com.rodrigorp.harrypotterapi.dto.CharacterNewDTO;
import com.rodrigorp.harrypotterapi.model.CharacterHP;
import com.rodrigorp.harrypotterapi.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CharacterInsertValidator implements ConstraintValidator<CharacterInsert, CharacterNewDTO> {

    @Autowired
    private CharacterRepository repository;

    @Override
    public void initialize(CharacterInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(CharacterNewDTO characterNewDTO, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        Optional<CharacterHP> characterByName = repository.findByName(characterNewDTO.getName());

        if (characterByName.isPresent()) {
            list.add(new FieldMessage("name", "Character already registered"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
