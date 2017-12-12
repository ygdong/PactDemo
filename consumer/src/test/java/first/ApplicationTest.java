package first;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationTest {

    @Rule
    public PactProviderRuleMk2 providerMock = new PactProviderRuleMk2("Remote1_Provider", "localhost", 9090, this);


    @Pact(provider = "Remote1_Provider", consumer = "Foo_Consumer")
    public RequestResponsePact createFragment(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");
        return builder.given("Foo_State")
                .uponReceiving("a request for Foos")
                .path("/remote1")
                .method("GET")
                .willRespondWith()
                .headers(headers)
                .status(200)
                .body("[{\"value\":42}, {\"value\":100}]")
                .toPact();
    }

    @Test
    @PactVerification("Remote1_Provider")//可以有多个 provider, 这里指定当前 mock 哪个
    public void runTest() {
        final List<Foo> expected = Arrays.asList(new Foo(142));
        final List<Foo> actual = given().get("http://localhost:8080/foos").body().jsonPath().getList("", Foo.class);

        assertEquals(expected, actual);


    }

}
