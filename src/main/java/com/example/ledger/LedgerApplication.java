package com.example.ledger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class LedgerApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException {

        SpringApplication.run(LedgerApplication.class, args);
	}

}
