package co.incubyte.word;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response<T> {

  String message;
  Status status;
  T data;

  @JsonCreator
  public Response(
      @JsonProperty("message") String message,
      @JsonProperty("state") Status status,
      @JsonProperty("data") T data) {
    this.message = message;
    this.status = status;
    this.data = data;
  }

  public Response(T data) {
    this.data = data;
  }

  public static <T> Response<T> success(T data) {
    return new Response<>("Success", Status.SUCCESS, data);
  }

  public String getMessage() {
    return message;
  }

  public Status getStatus() {
    return status;
  }

  public T getData() {
    return data;
  }
}
