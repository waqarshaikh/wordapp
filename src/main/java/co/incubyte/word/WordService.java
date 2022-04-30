package co.incubyte.word;

import java.util.List;
import javax.inject.Singleton;

@Singleton
public class WordService {

  private final WordRepository wordRepository;
  private final WordLoggerClient wordLoggerClient;

  public WordService(WordRepository wordRepository,
      WordLoggerClient wordLoggerClient) {
    this.wordRepository = wordRepository;
    this.wordLoggerClient = wordLoggerClient;
  }

  public List<Word> getAllWords() {
    List<Word> words = wordRepository.findAll();
    wordLoggerClient.logRetrieval(words);
    return words;
  }
}
