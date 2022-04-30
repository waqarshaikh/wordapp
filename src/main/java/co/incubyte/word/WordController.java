package co.incubyte.word;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import java.util.List;

@Controller("/words")
public class WordController {

  private final WordService wordService;

  public WordController(WordService wordService) {
    this.wordService = wordService;
  }

  @Get
  public Response<List<Word>> getAllWords() {
    return Response.success(wordService.getAllWords());
  }

}
