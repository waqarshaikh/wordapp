package co.incubyte.word;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WordDto {

  private final String value;

  @JsonCreator
  public WordDto(@JsonProperty("value") String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
