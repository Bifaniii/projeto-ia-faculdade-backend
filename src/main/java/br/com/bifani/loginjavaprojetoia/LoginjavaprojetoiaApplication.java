package br.com.bifani.loginjavaprojetoia;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginjavaprojetoiaApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure()
				.directory("/home/bifani/Área de trabalho/T.I/Java/backendfaculdadeia/projeto-ia-faculdade-backend/.env")
				.load();

		setIfPresent("DB_URL", dotenv.get("DB_URL"));
		setIfPresent("JWT_SECRET", dotenv.get("JWT_SECRET"));
		setIfPresent("EXPIRATION_TIME", dotenv.get("EXPIRATION_TIME"));

		SpringApplication.run(LoginjavaprojetoiaApplication.class, args);
	}

	private static void setIfPresent(String key, String value) {
		if (value != null && !value.isBlank()) {
			System.setProperty(key, value);
		}
	}
}
