package com.eshelsoft.mongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Application.class.getName());

    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        customerRepository.deleteAll();

        customerRepository.save(new Customer("Alice", "Smith"));
        customerRepository.save(new Customer("Bob", "Smith"));

        logger.info("Customers found with findAll():");
        logger.info("-------------------------------");
        customerRepository.findAll().forEach(customer -> logger.info(customer.toString()));
        logger.info("\n");

        logger.info("Customers found with findByFirstName('Alice'):");
        logger.info("-------------------------------");
        logger.info(String.valueOf(customerRepository.findByFirstName("Alice")));

        logger.info("Customers found with findByLastName('Smith'):");
        logger.info("-------------------------------");
        customerRepository.findByLastName("Smith").forEach(customer -> logger.info(customer.toString()));
    }
}
