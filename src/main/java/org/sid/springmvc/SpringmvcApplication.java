package org.sid.springmvc;

import org.sid.springmvc.dao.PatientRepository;
import org.sid.springmvc.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SpringmvcApplication implements CommandLineRunner {
	@Autowired
	private PatientRepository patientRepository;
//	Autowired injection de dependedence

	public static void main(String[] args) {
		SpringApplication.run(SpringmvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(new Patient(null,"Hassan",new Date(),false,8));
		patientRepository.save(new Patient(null,"Mohammed",new Date(),false,9));
		patientRepository.save(new Patient(null,"Samira",new Date(),false,12));

		patientRepository.findAll().forEach(patient -> System.out.println(patient.getName()));

	}
}
