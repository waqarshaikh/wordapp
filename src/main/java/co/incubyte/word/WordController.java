package co.incubyte.word;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import java.util.List;

@Controller()
public class WordController {

  private final WordService wordService;

  public WordController(WordService wordService) {
    this.wordService = wordService;
  }

  @Get("/words")
  public List<Word> getAllWords() {
    return wordService.getAllWords();
  }

}
