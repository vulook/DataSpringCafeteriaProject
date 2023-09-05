package edu.cbsystematics.com.dataspringcafeteriaproject.service;

import edu.cbsystematics.com.dataspringcafeteriaproject.models.Role;
import edu.cbsystematics.com.dataspringcafeteriaproject.models.Worker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface CafeteriaService {

    // Get all workers
    List<Worker> getAllWorkers();

    // Get all roles
    List<Role> getAllRoles();

    // Find role by name
    Optional<Role> findRoleByName(String name);

    // Get worker by ID
    Optional<Worker> getWorkerById(Long id);

    // Save a new worker
    void saveWorker(Worker worker);

    // Save a new role
    void saveRole(Role role);

    // Count workers by Role
    int countWorkersByRole(String roleName);

    // Get workers by Role ID
    List<Worker> getWorkersByRoleId(Long roleId);

    // Delete a worker by ID
    void deleteWorker(Long id);

    // Update Worker Information
    void updateWorkerInfo(Long id, String firstName, String lastName, String email, LocalDate birthDate, Role role);

    // Search for workers by first name and last name
    List<Worker> searchByFullName(String fullName);
}