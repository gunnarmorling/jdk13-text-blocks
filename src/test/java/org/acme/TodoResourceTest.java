package org.acme;

import static io.restassured.RestAssured.given;
import static org.acme.MatchesJson.matchesJson;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class TodoResourceTest {

  @Test
  public void canPostNewTodoAndReceiveId() throws Exception {
    given()
      .when()
        .body("""
              {
                "title" : "Learn Java 13",
                "completed" : false
              }
              """
        )
        .body(
              "{\n" +
              "  \"title\" : \"Learn Java 13\",\n" +
              "  \"completed\" : false\n" +
              "}"
        )
        .contentType(ContentType.JSON)
        .post("/todo")
      .then()
        .statusCode(201)
        .body(matchesJson("""
                          {
                            "id" : 1,
                            "title" : "Learn Java 13",
                            "completed" : false
                          }
                          """)
        );
  }
}
