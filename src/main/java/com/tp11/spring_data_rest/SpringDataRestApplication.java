package com.tp11.spring_data_rest;

import com.tp11.spring_data_rest.entities.Client;
import com.tp11.spring_data_rest.entities.Compte;
import com.tp11.spring_data_rest.entities.TypeCompte;
import com.tp11.spring_data_rest.repositories.ClientRepository;
import com.tp11.spring_data_rest.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
public class SpringDataRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataRestApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CompteRepository compteRepository, ClientRepository clientRepository, RepositoryRestConfiguration restConfiguration){
		return args -> {
			// La méthode exposeIdsFor permet d'inclure les IDs des entités dans les réponses JSON, ce qui facilite leur identification et manipulation côté client.
			restConfiguration.exposeIdsFor(Compte.class);

			// Initialisation des comptes
			Client c1 = clientRepository.save(new Client(null, "Amal", null, null));
			Client c2 = clientRepository.save(new Client(null, "Ali", null, null));

			compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.EPARGNE, c1));
			compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.COURANT, c1));
			compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.EPARGNE, c2));

			compteRepository.findAll().forEach(c -> {
				System.out.println(c.toString());
			});
		};
	}

}
