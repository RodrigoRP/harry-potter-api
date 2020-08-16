package com.rodrigorp.harrypotterapi.service.validation;

import com.rodrigorp.harrypotterapi.controller.exception.FieldMessage;
import com.rodrigorp.harrypotterapi.dto.CharacterNewDTO;
import com.rodrigorp.harrypotterapi.model.CharacterHP;
import com.rodrigorp.harrypotterapi.model.PotterApi;
import com.rodrigorp.harrypotterapi.repository.CharacterRepository;
import com.rodrigorp.harrypotterapi.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CharacterInsertValidator implements ConstraintValidator<CharacterInsert, CharacterNewDTO> {

    @Autowired
    private CharacterRepository repository;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private Environment environment;

    @Override
    public void initialize(CharacterInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(CharacterNewDTO characterNewDTO, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        Optional<CharacterHP> characterByName = repository.findByName(characterNewDTO.getName());
        Mono<PotterApi[]> mono = characterService
                .searchHouseById(environment.getProperty("app.api.key"), characterNewDTO.getHouse());

        if (characterByName.isPresent()) {
            list.add(new FieldMessage("name", "Character already registered"));
        }
        /*mono.map(potterApis -> {
            return Arrays.stream(potterApis).findAny().get().getName().isEmpty();
        }).block();*/
        mono.block();

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
