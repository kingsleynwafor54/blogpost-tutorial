import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.kingsCorner.kingsCorner.kingsCorner.security")
public class KingsCornerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KingsCornerApplication.class, args);
	}

}
