package com.juancerezole.alkemy.apirest.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.juancerezole.alkemy.apirest.backend.Constantes.Constantes;

@SpringBootApplication
public class ApiRestDisneyAlkemyApplication  implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ApiRestDisneyAlkemyApplication.class, args);
		try {
			//Abre el navegador con la url donde se puede visualizar los endpoint del Swagger
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + Constantes.URL);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
		
	@Override
	public void run(String... args) throws Exception {
		String password = "12345";
		
		for (int i = 0; i < 4; i++) {
			String passwordBcrypt = passwordEncoder.encode(password);
			System.out.println(passwordBcrypt);
		}
		
	}

}
