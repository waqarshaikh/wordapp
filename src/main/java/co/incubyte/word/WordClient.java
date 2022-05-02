package co.incubyte.word;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import java.util.List;

@Client("/words")
public interface WordClient {

  @Get
  Response<List<WordDto>> getAllWords();

  @Post
  void saveWord(@Body WordDto wordDto);

  @Delete("/{id}")
  void deleteWord(Long id);
}
