package co.incubyte.word;

import static org.assertj.core.api.Assertions.assertThat;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import java.util.List;
import java.util.Objects;
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
    wordClient.saveWord(new WordDto(1L, "Word1"));

    WordLoggerMockServer.addGetWordsEndpoint();
    Response<List<WordDto>> wordResponse = wordClient.getAllWords();

    assertThat(wordResponse.getStatus()).isEqualTo(Status.SUCCESS);
    assertThat(wordResponse.getData()).isNotNull();

    List<WordDto> body = wordResponse.getData();
    if (body != null) {
      assertThat(body).hasSize(1);
      assertThat(body.get(0).getValue()).isEqualTo("Word1");
      WordLoggerMockServer.verifyGetWordsRequest(1, body.get(0));
    }
  }

  @Test
  void should_save_word() {
    WordLoggerMockServer.addSaveWordEndpoint();

    WordDto wordDto = new WordDto(1L, "Hello, World!");
    wordClient.saveWord(wordDto);

    List<Word> words = wordRepository.getAllWords();

    assertThat(words).isNotNull();

    assertThat(words.get(0).getValue()).isEqualTo("Hello, World!");
    WordLoggerMockServer.verifySaveWordRequest(1, wordDto);
  }

  @Test
  void should_delete_word() {
    WordDto wordDto = new WordDto(1L, "Word1");
    wordClient.saveWord(wordDto);

    List<Word> words = wordRepository.getAllWords();

    WordLoggerMockServer.addDeleteWordEndpoint();

    wordClient.deleteWord(Objects.requireNonNull(words).get(0).getId());

    List<Word> updatedWords = wordRepository.getAllWords();

    assertThat(updatedWords).isEmpty();

    WordLoggerMockServer.verifyDeleteWordRequest(1, updatedWords.get(0).getId());
  }
}
