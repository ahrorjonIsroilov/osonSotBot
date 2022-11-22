package osonsot;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import osonsot.config.BotExecutor;

@SpringBootApplication
@EnableScheduling
@EnableAsync(proxyTargetClass = true)
@RequiredArgsConstructor
public class OsonSotApplication implements CommandLineRunner {

  private final BotExecutor executor;

  public static void main(String[] args) {
    SpringApplication.run(OsonSotApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    executor.main();
  }
}
