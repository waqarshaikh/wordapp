package co.incubyte.word;

import static io.micronaut.http.HttpRequest.GET;
import static org.assertj.core.api.Assertions.assertThat;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

@MicronautTest
class WordControllerTest {

  @Inject
  @Client("/")
  private HttpClient httpClient;

  @Test
  void should_return_all_words() {
    HttpResponse<List<Word>> wordResponse = httpClient.toBlocking()
        .exchange(GET("/words"), Argument.listOf(Word.class));

    assertThat(wordResponse.code()).isEqualTo(200);
    assertThat(wordResponse.body()).isNotNull();

    List<Word> body = wordResponse.body();
    if (body != null) {
      assertThat(body.get(0).getValue()).isEqualTo("Hello");
      assertThat(body.get(1).getValue()).isEqualTo("World");
    }
  }
}
