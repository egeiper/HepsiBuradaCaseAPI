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
    }

    @Test(priority = 2)
    public void getUser() {
        final Response response = get("/user/"+USERNAME, null, null, null);
        assertEquals(200, response.getStatusCode());
    }

    @AfterTest
    public void deleteData() {
        delete("/user/"+USERNAME, null, null, null);
    }
}
