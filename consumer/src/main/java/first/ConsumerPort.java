package first;

import io.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class ConsumerPort {

    private String url;
    private RestTemplate restTemplate;

    @Autowired
    public ConsumerPort(@Value("${producer}") String url) {
        this.url = url;
        this.restTemplate = new RestTemplate();
    }


    public List<Foo> foos() {
        final List<Foo> foos = RestAssured.get(url + "/remote1").body().jsonPath().getList("", Foo.class);
        int sum = 0;

        for (Foo foo : foos) {
            sum += foo.getValue();
        }

        return Arrays.asList(new Foo(sum));
    }
}
