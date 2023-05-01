package pizzaApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import processor.Importcsvtodb;

@SpringBootApplication
@ComponentScan(basePackages = {"processor", "entity" , "pizzaApi"})
@EnableJpaRepositories("entity")
@EntityScan("entity")
@EnableTransactionManagement

public class PizzaApiApplication implements CommandLineRunner {

    @Autowired
    private Importcsvtodb importcsvtodb;

    public static void main(String[] args) {

        SpringApplication.run(PizzaApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        importcsvtodb.loadCsvToDb();
    }


}
