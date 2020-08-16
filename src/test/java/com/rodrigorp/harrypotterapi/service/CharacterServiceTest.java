package com.rodrigorp.harrypotterapi.service;

import com.rodrigorp.harrypotterapi.dto.CharacterUpdateDto;
import com.rodrigorp.harrypotterapi.model.CharacterHP;
import com.rodrigorp.harrypotterapi.repository.CharacterRepository;
import com.rodrigorp.harrypotterapi.service.exception.ObjectNotFoundException;
import com.rodrigorp.harrypotterapi.service.impl.CharacterServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class CharacterServiceTest {

    @InjectMocks
    CharacterServiceImpl characterService;

    @Mock
    CharacterRepository characterRepository;

    @Test
    @DisplayName("Test save Success")
    void saveCharacterTest() {
        //given
        CharacterHP characterHP = new CharacterHP();
        given(characterRepository.save(any(CharacterHP.class))).willReturn(characterHP);

        //when
        CharacterHP savedCharacter = characterService.save(characterHP);

        //then
        Assertions.assertNotNull(savedCharacter);
        then(characterRepository).should().save(any(CharacterHP.class));
    }

    @Test
    @DisplayName("Test findById Success")
    void findCharacterByIdTest() {
        //given
        CharacterHP characterHP = new CharacterHP(1L, "Harry Potter", "student",
                "Hogwarts School of Witchcraft and Wizardry", "5a05e2b252f721a3cf2ea33f", "stag");
        given(characterRepository.findById(1L)).willReturn(Optional.of(characterHP));

        //when
        CharacterHP savedCharacter = characterService.findById(1L);

        //then
        Assertions.assertNotNull(savedCharacter);
        Assertions.assertEquals(characterHP, savedCharacter);
    }

    @Test
    @DisplayName("Test findById Not Found")
    void findCharacterNotFoundTest() {
        Exception exception = assertThrows(
                ObjectNotFoundException.class,
                () -> characterService.findById(32L));

        Assertions.assertTrue(exception.getMessage().contains("not found"));
    }

    @Test
    @DisplayName("Test findAll Success")
    void findAllCharacterTest() {
        //given
        List<CharacterHP> characterHPList = new ArrayList<>();
        characterHPList.add(new CharacterHP());
        characterHPList.add(new CharacterHP());
        given(characterRepository.findAll()).willReturn(characterHPList);

        //when
        List<CharacterHP> returnedList = characterService.findAll();

        //then
        Assertions.assertEquals(characterHPList, returnedList);
        then(characterRepository).should().findAll();
    }

    @Test
    @DisplayName("Test findAll by HouseId Success")
    void findAllByHouseIdTest() {
        //given
        CharacterHP characterHP = new CharacterHP(1L, "Harry Potter", "student",
                "Hogwarts School of Witchcraft and Wizardry", "5a05e2b252f721a3cf2ea33f", "stag");
        List<CharacterHP> characterHPList = new ArrayList<>();
        characterHPList.add(characterHP);
        given(characterRepository.findAllByHouse("5a05e2b252f721a3cf2ea33f")).willReturn(Optional.of(characterHPList));

        //when
        List<CharacterHP> returnedList = characterService.findAllByHouse("5a05e2b252f721a3cf2ea33f");

        //then
        Assertions.assertEquals(characterHPList, returnedList);
        then(characterRepository).should().findAllByHouse("5a05e2b252f721a3cf2ea33f");
    }

    @Test
    @DisplayName("Test findAll Not Found HouseId")
    void findAllCharactersByHouseIdNotFoundTest() {
        Exception exception = assertThrows(
                ObjectNotFoundException.class,
                () -> characterService.findAllByHouse("36712h"));

        Assertions.assertTrue(exception.getMessage().contains("not found"));
    }

    @Test
    @DisplayName("Test deleteById Success")
    void deleteByIdTest() {
        //given
        CharacterHP characterHP = new CharacterHP();
        characterHP.setId(1L);
        given(characterRepository.findById(1L)).willReturn(Optional.of(characterHP));

        //when
        characterService.deleteById(1L);
        characterService.deleteById(1L);

        //then
        then(characterRepository).should(times(2)).deleteById(1L);
    }

    @Test
    @DisplayName("Test update Success")
    void updateByIdTest() {
        //given
        CharacterHP characterHP = new CharacterHP(1L, "Harry Potter", "student",
                "Hogwarts School of Witchcraft and Wizardry", "5a05e2b252f721a3cf2ea33f", "stag");
        CharacterUpdateDto characterUpdateDto = new CharacterUpdateDto();
        characterUpdateDto.setName(JsonNullable.of("Ronaldo"));

        given(characterRepository.findById(1L)).willReturn(Optional.of(characterHP));

        //when
        characterService.update(characterUpdateDto, 1L);
        CharacterHP updatedCharacterHP = characterService.findById(1L);

        //then
        Assertions.assertEquals("Ronaldo", characterHP.getName());
        Assertions.assertEquals(characterHP, updatedCharacterHP);
    }

    @Test
    @DisplayName("Test update Character Not Found Id")
    void updateByIdNotFoundTest() {
        Exception exception = assertThrows(
                ObjectNotFoundException.class,
                () -> characterService.findById(22L));

        Assertions.assertTrue(exception.getMessage().contains("not found"));
    }
}
