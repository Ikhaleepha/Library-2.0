package cucumber;

import com.mam.io.Library20.Application;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty",
features = "src\\main\\java\\resources\\features")
public class CucumberRunner {
}
