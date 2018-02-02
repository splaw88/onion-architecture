package pl.splaw.onionarchitecture.springapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.splaw.onionarchitecture.BasePackegaMarker;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@SpringBootApplication(scanBasePackageClasses = BasePackegaMarker.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
