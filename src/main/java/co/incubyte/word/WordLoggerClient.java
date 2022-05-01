package co.incubyte.word;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import java.util.List;

@Client(value = "${client.logger.url}")
public interface WordLoggerClient {

  @Post("/retrieve")
  void logRetrieval(@Body List<WordDto> wordDtos);

  @Post("/create")
  void logCreation(@Body WordDto wordDto);
}
