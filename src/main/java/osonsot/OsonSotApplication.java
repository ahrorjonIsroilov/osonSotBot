package osonsot;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import osonsot.config.BotExecutor;

@SpringBootApplication
@RequiredArgsConstructor
public class OsonSotApplication implements CommandLineRunner {

    private final BotExecutor executor;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(OsonSotApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        executor.main();
    }
}
