package co.incubyte.word;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import java.util.List;

@Client("/words")
public interface WordClient {

  @Get
  Response<List<Word>> getAllWords();
}
