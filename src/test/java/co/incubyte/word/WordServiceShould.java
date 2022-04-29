package co.incubyte.word;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class WordServiceShould {

  private WordRepository wordRepository;
  private WordService wordService;

  @BeforeEach
  void setUp() {
    wordRepository = mock(WordRepository.class);
    wordService = new WordService(wordRepository);
  }

  @Test
  void should_get_all_words() {
    wordService.getAllWords();
    Mockito.verify(wordRepository).findAll();
  }
}