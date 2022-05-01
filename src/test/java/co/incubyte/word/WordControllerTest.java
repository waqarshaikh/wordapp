package co.incubyte.word;

import static org.assertj.core.api.Assertions.assertThat;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@MicronautTest
class WordControllerTest {

  @Inject
  WordClient wordClient;

  @Inject
  WordRepository wordRepository;

  @BeforeEach
  void setUp() {
    WordLoggerMockServer.startWithoutStubs();
    wordRepository.deleteAll();
  }

  @AfterEach
  void tearDown() {
    WordLoggerMockServer.stop();
  }

  @Test
  void should_return_all_words() {
    wordClient.saveWord(new WordDto("Word1"));

    WordLoggerMockServer.addGetWordsEndpoint();
    Response<List<WordDto>> wordResponse = wordClient.getAllWords();

    assertThat(wordResponse.getStatus()).isEqualTo(Status.SUCCESS);
    assertThat(wordResponse.getData()).isNotNull();

    List<WordDto> body = wordResponse.getData();
    if (body != null) {
      assertThat(body).hasSize(1);
      assertThat(body.get(0).getValue()).isEqualTo("Word1");
    }
  }

  @Test
  void should_save_word() {
    WordLoggerMockServer.addSaveWordEndpoint();

    wordClient.saveWord(new WordDto("Hello, World!"));

    List<Word> words = wordRepository.getAllWords();

    assertThat(words).isNotNull();

    assertThat(words.get(0).getValue()).isEqualTo("Hello, World!");

  }
}
