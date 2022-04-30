package co.incubyte.word;

import static org.mockito.Mockito.mock;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class WordServiceShould {

  private WordRepository wordRepository;
  private WordService wordService;
  private WordLoggerClient wordLoggerClient;

  @BeforeEach
  void setUp() {
    wordRepository = mock(WordRepository.class);
    wordLoggerClient = mock(WordLoggerClient.class);
    wordService = new WordService(wordRepository, wordLoggerClient);
  }

  @Test
  void should_get_all_words() {
    List<Word> words = wordService.getAllWords();
    Mockito.verify(wordRepository).findAll();
    Mockito.verify(wordLoggerClient).logRetrieval(words);
  }
}