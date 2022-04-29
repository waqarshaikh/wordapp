package co.incubyte.word;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import java.util.List;

@Client()
public interface WordClient {

  @Get("/users")
  HttpResponse<List<Word>> getAllWords();
}
