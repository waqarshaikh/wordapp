package co.incubyte.word;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WordRepositoryShould {

  private WordRepository wordRepository;

  @BeforeEach
  void setUp() {
    wordRepository = new WordRepository();
  }

  @Test
  void return_all_words() {
    List<Word> words = wordRepository.findAll();
    assertThat(words.get(0).getValue()).isEqualTo("Hello");
    assertThat(words.get(1).getValue()).isEqualTo("World");
  }
}