package edu.cbsystematics.com.dataspringcafeteriaproject;

import edu.cbsystematics.com.dataspringcafeteriaproject.models.Role;
import edu.cbsystematics.com.dataspringcafeteriaproject.models.Worker;
import edu.cbsystematics.com.dataspringcafeteriaproject.service.CafeteriaService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class DataSpringCafeteriaProjectApplication {

    private final CafeteriaService cafeteriaService;

    @Autowired
    public DataSpringCafeteriaProjectApplication(CafeteriaService cafeteriaService) {
        this.cafeteriaService = cafeteriaService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DataSpringCafeteriaProjectApplication.class, args);
    }

    @PostConstruct
    public void createAndSaveWorkers() {

        // Creating and Saving the roles in the database
        Role directorRole = new Role("Director");
        Role accountantRole = new Role("Accountant");
        Role baristaRole = new Role("Barista");

        cafeteriaService.saveRole(directorRole);
        cafeteriaService.saveRole(accountantRole);
        cafeteriaService.saveRole(baristaRole);

        // Creating and Saving workers in the database
        Worker director = new Worker("John", "Smith", "john123s@example.com", LocalDate.of(1980, 1, 1), directorRole);
        Worker accountant = new Worker("Alice", "Johnson", "alice_johnson85@example.com", LocalDate.of(1985, 2, 2), accountantRole);
        Worker barista1 = new Worker("Michael", "Davis", "michd666@example.com", LocalDate.of(2001, 3, 3), baristaRole);
        Worker barista2 = new Worker("Emily", "Brown", "brown44@example.com", LocalDate.of(2002, 4, 4), baristaRole);
        Worker barista3 = new Worker("William", "Lee", "wil2000@example.com", LocalDate.of(2000, 5, 5), baristaRole);

        cafeteriaService.saveWorker(director);
        cafeteriaService.saveWorker(accountant);
        cafeteriaService.saveWorker(barista1);
        cafeteriaService.saveWorker(barista2);
        cafeteriaService.saveWorker(barista3);
    }

}
