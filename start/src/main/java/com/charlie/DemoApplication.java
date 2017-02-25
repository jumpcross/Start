package com.charlie;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.charlie.dao.CustomerDao;
import com.charlie.dao.FamilyDao;
import com.charlie.entity.Customer;
import com.charlie.entity.FamilyMember;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(CustomerDao repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findOne(1L);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findByLastName("Bauer")) {
				log.info(bauer.toString());
			}
			log.info("");
		};
	}
	@Bean
	public CommandLineRunner familySampleData(FamilyDao repository) {
		return (args) -> {
			// save a couple of customers
			FamilyMember familyMember  = new FamilyMember();
			familyMember.setBirthDay(new Date());
			familyMember.setName("first");
			familyMember.setMailAddress("2279993496@qq.com");
			repository.save(familyMember);
			FamilyMember familyMember1  = new FamilyMember();
			familyMember1.setBirthDay(new Date());
			familyMember1.setName("second");
			familyMember1.setMailAddress("2571588134@qq.com");
			repository.save(familyMember1);
			log.info("**save First Sencond");
		};
	}

}
