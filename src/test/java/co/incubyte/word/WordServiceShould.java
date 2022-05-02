package co.incubyte.word;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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
  void return_all_words() {
    when(wordRepository.getAllWords()).thenReturn(List.of(new Word("Word1"), new Word("Word2")));

    wordService.getAllWords();

    ArgumentCaptor<List<WordDto>> wordArgumentCaptor = ArgumentCaptor.forClass(List.class);

    Mockito.verify(wordRepository).getAllWords();
    Mockito.verify(wordLoggerClient).logRetrieval(wordArgumentCaptor.capture());

    List<WordDto> words = wordArgumentCaptor.getValue();
    assertThat(words.get(0).getValue()).isEqualTo("Word1");
    assertThat(words.get(1).getValue()).isEqualTo("Word2");
  }

  @Test
  void should_save_word() {
    Word word = new Word("Word1");
    when(wordRepository.save(any(Word.class))).thenReturn(word);

    wordService.save(new WordDto(1L, "Word1"));

    ArgumentCaptor<Word> wordArgumentCaptor = ArgumentCaptor.forClass(Word.class);
    Mockito.verify(wordRepository).save(wordArgumentCaptor.capture());

    ArgumentCaptor<WordDto> wordDtoArgumentCaptor = ArgumentCaptor.forClass(WordDto.class);
    Mockito.verify(wordLoggerClient).logCreation(wordDtoArgumentCaptor.capture());

    Word captorValue = wordArgumentCaptor.getValue();
    assertThat(captorValue.getValue()).isEqualTo("Word1");
  }

  @Test
  void delete_word() {
    wordService.delete(1L);

    verify(wordRepository).deleteById(argThat(id -> id.equals(1L)));
    verify(wordLoggerClient).logDeletion(argThat(id -> id.equals(1L)));
  }
}