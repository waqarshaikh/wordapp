package co.incubyte.word;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WordDto {

  private final Long id;
  private final String value;

  @JsonCreator
  public WordDto(@JsonProperty("id") Long id, @JsonProperty("value") String value) {
    this.id = id;
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public Long getId() {
    return id;
  }
}
