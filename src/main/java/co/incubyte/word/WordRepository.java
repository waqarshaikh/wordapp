package co.incubyte.word;

import java.util.List;
import javax.inject.Singleton;

@Singleton
public class WordRepository {

  public List<Word> findAll() {
    return List.of(
        new Word("Hello"),
        new Word("World")
    );
  }
}
