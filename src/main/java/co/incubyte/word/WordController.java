package co.incubyte.word;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import java.util.List;

@Controller("/words")
public class WordController {

  private final WordService wordService;

  public WordController(WordService wordService) {
    this.wordService = wordService;
  }

  @Get
  public Response<List<WordDto>> getAllWords() {
    return Response.success(wordService.getAllWords());
  }

  @Post
  public void save(@Body WordDto wordDto) {
    wordService.save(wordDto);
  }
}
