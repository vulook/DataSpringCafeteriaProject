package edu.cbsystematics.com.dataspringcafeteriaproject.service;

import edu.cbsystematics.com.dataspringcafeteriaproject.models.Role;
import edu.cbsystematics.com.dataspringcafeteriaproject.models.Worker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CafeteriaService {

    // Get all workers
    List<Worker> getAllWorkers();

    // Get all roles
    List<Role> getAllRoles();

    // Get worker by ID
    Optional<Worker> getWorkerById(Long id);

    // Save a new worker
    void saveWorker(Worker worker);

    // Save a new role
    void saveRole(Role role);

    // Delete a worker by ID
    void deleteWorker(Long id);

    // Search for workers by first name and last name
    List<Worker> searchByFullName(String fullName);
}