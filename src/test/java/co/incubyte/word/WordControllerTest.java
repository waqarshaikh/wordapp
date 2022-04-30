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

  @BeforeEach
  void setUp() {
    WordLoggerMockServer.startWithoutStubs();
  }

  @AfterEach
  void tearDown() {
    WordLoggerMockServer.stop();
  }

  @Test
  void should_return_all_words() {
    WordLoggerMockServer.addGetWordsEndpoint();

    Response<List<Word>> wordResponse = wordClient.getAllWords();

    assertThat(wordResponse.getStatus()).isEqualTo(Status.SUCCESS);
    assertThat(wordResponse.getData()).isNotNull();

    List<Word> body = wordResponse.getData();
    if (body != null) {
      assertThat(body).hasSize(2);
      assertThat(body.get(0).getValue()).isEqualTo("Hello");
      assertThat(body.get(1).getValue()).isEqualTo("World");
    }
  }
}
