package co.incubyte.word;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import java.util.List;

@Repository
public interface WordRepository extends CrudRepository<Word, Long> {

  @Nullable
  @Query("select w from Word w")
  List<Word> getAllWords();
}
