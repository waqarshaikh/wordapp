package co.incubyte.word;

import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;

public class WordLoggerMockServer {

  private static WireMockServer wireMockServer;

  public static void start() {
    if (wireMockServer == null || !wireMockServer.isRunning()) {
      startWithoutStubs();
    }
    addGetWordsEndpoint();
  }

  public static void addGetWordsEndpoint() {
    wireMockServer.stubFor(WireMock.post(WireMock.urlMatching("/retrieve"))
        .willReturn(WireMock.okJson(getWordsLoggedData())));
  }

  private static String getWordsLoggedData() {
    return "{\n" + "    \"message\":  \"Logged Word Retrieval successfully\"\n" + "}";
  }

  public static void verifyGetWordsRequest(int count, WordDto wordDto) {
    wireMockServer.verify(count, postRequestedFor(urlEqualTo("/retrieve")).withRequestBody(
        equalToJson(getWordRequsetBody(wordDto), true, false)));
  }

  private static String getWordRequsetBody(WordDto wordDto) {
    return "[{ " + " \"id\":" + wordDto.getId() + "," + "\"value\":\"" + wordDto.getValue() + "\" "
        + "}]";
  }

  public static void addSaveWordEndpoint() {
    wireMockServer.stubFor(WireMock.post(WireMock.urlMatching("/create"))
        .willReturn(WireMock.okJson(saveWordLoggedData())));
  }

  private static String saveWordLoggedData() {
    return "{\n" + "    \"message\":  \"Logged Word Creation successfully\"\n" + "}";
  }

  public static void verifySaveWordRequest(int count, WordDto wordDto) {
    wireMockServer.verify(count, postRequestedFor(urlEqualTo("/create")).withRequestBody(
        equalToJson(saveWordRequsetBody(wordDto), true, false)));
  }

  private static String saveWordRequsetBody(WordDto wordDto) {
    return "{" + " \"id\":" + wordDto.getId() + "," + "\"value\":\"" + wordDto.getValue() + "\"}";
  }

  public static void startWithoutStubs() {
    wireMockServer = new WireMockServer(options().port(8000).notifier(new ConsoleNotifier(true)));
    wireMockServer.start();
  }

  public static void stop() {
    if (wireMockServer != null && wireMockServer.isRunning()) {
      wireMockServer.stop();
    }
  }
}
