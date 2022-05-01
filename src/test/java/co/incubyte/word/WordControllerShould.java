package co.incubyte.word;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

class WordControllerShould {

  private WordController wordController;
  private WordService wordService;

  @BeforeEach
  void setUp() {
    wordService = Mockito.mock(WordService.class);
    wordController = new WordController(wordService);
  }

  @Test
  void call_service_to_get_all_words() {
    Response<List<WordDto>> response = wordController.getAllWords();
    assertEquals(Status.SUCCESS, response.status);
    Mockito.verify(wordService).getAllWords();
  }

  @Test
  void call_service_to_save_word() {
    wordController.save(new WordDto("Word1"));

    ArgumentCaptor<WordDto> wordArgumentCaptor = ArgumentCaptor.forClass(WordDto.class);
    Mockito.verify(wordService).save(wordArgumentCaptor.capture());

    WordDto wordDto = wordArgumentCaptor.getValue();
    assertEquals("Word1", wordDto.getValue());
  }
}