package com.rodrigorp.harrypotterapi.bootstrap;

import com.rodrigorp.harrypotterapi.model.CharacterHP;
import com.rodrigorp.harrypotterapi.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Profile("test")
@Component
public class BootstrapData implements CommandLineRunner {

    private final CharacterRepository characterRepository;

    public BootstrapData(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        CharacterHP characterHP1 = new CharacterHP(1L, "Harry Potter", "student",
                "Hogwarts School of Witchcraft and Wizardry", "5a05e2b252f721a3cf2ea33f", "stag");

        CharacterHP characterHP2 = new CharacterHP(2L, "Hannah Abbott", "student",
                "Hogwarts School of Witchcraft and Wizardry", "5a05e2b252f721a3cf2ea33f", "stag");


        characterRepository.saveAll(Arrays.asList(characterHP1, characterHP2));
    }
}
