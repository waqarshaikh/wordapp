package co.incubyte.word;

import java.util.List;
import javax.inject.Singleton;

@Singleton
public class WordService {

  private final WordRepository wordRepository;

  public WordService(WordRepository wordRepository) {
    this.wordRepository = wordRepository;
  }

  public List<Word> getAllWords() {
    return wordRepository.findAll();
  }
}
