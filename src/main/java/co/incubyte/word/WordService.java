package co.incubyte.word;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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

  public List<WordDto> getAllWords() {
    List<Word> words = wordRepository.getAllWords();

    List<WordDto> wordDtos = Objects.requireNonNull(words).stream()
        .map(word -> new WordDto(word.getId(), word.getValue()))
        .collect(Collectors.toList());

    wordLoggerClient.logRetrieval(wordDtos);
    return wordDtos;
  }

  public void save(WordDto wordDto) {
    Word word = new Word(wordDto.getValue());
    wordRepository.save(word);
    wordLoggerClient.logCreation(wordDto);
  }

  public void delete(Long id) {
    wordRepository.deleteById(id);
    wordLoggerClient.logDeletion(id);
  }
}
