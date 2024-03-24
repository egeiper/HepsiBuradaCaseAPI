package tests;

import io.restassured.response.Response;
import org.egeiper.client.RestClient;
import org.egeiper.models.request.PostUserRequest;
import org.egeiper.util.PropertyUtils;
import org.testng.annotations.*;

import static org.testng.AssertJUnit.assertEquals;

public class APITest extends RestClient {

    private static final String USERNAME = "egeiper";
    private static final String FIRST_NAME = "Ege";
    private static final String LAST_NAME = "İper";
    private static final String EMAIL = "egeiper@hotmail.com";
    public APITest() {
        super(PropertyUtils.getProperty("test.properties", "apiURL"));
    }

    @BeforeTest
    public void beforeTest() {
        // delete if data exists with given username to avoid conflict
        if(get("/user/"+USERNAME,null, null, null).getStatusCode() == 200) {
            assertEquals(200, delete("/user/"+USERNAME, null, null, null).getStatusCode());

        }
    }

    @Test(priority = 1)
    public void postUser() {
        final PostUserRequest request = PostUserRequest.builder()
            .username(USERNAME)
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .email(EMAIL).userStatus(0).build();

        final Response response = post("/user", null, null, request);
        assertEquals(response.getStatusCode(), 200);
        assertEquals("unknown", response.jsonPath().getString("type"));
        assertEquals(200, response.jsonPath().getInt("code"));

    }

    @Test(priority = 2)
    public void getUser() {
        final Response response = get("/user/"+USERNAME, null, null, null);
        assertEquals(200, response.getStatusCode());
        assertEquals(USERNAME, response.jsonPath().getString("username"));
        assertEquals(FIRST_NAME, response.jsonPath().getString("firstName"));
        assertEquals(LAST_NAME, response.jsonPath().getString("lastName"));
        assertEquals(EMAIL, response.jsonPath().getString("email"));
        assertEquals(0, response.jsonPath().getInt("userStatus"));
    }

    @AfterTest
    public void deleteData() {
        final Response response = delete("/user/"+USERNAME, null, null, null);
        assertEquals(response.getStatusCode(), 200);
        assertEquals(USERNAME, response.jsonPath().getString("message"));
        assertEquals(200, response.jsonPath().getInt("code"));
        assertEquals("unknown", response.jsonPath().getString("type"));
    }
}
