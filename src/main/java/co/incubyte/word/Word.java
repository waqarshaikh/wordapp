package co.incubyte.word;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Word {

  private final String value;

  @JsonCreator
  public Word(@JsonProperty("value") String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
