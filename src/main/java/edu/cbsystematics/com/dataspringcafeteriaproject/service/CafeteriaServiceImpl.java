package edu.cbsystematics.com.dataspringcafeteriaproject.service;

import edu.cbsystematics.com.dataspringcafeteriaproject.models.Role;
import edu.cbsystematics.com.dataspringcafeteriaproject.models.Worker;
import edu.cbsystematics.com.dataspringcafeteriaproject.repository.RoleRepository;
import edu.cbsystematics.com.dataspringcafeteriaproject.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CafeteriaServiceImpl implements CafeteriaService {

    private final WorkerRepository workerRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public CafeteriaServiceImpl(WorkerRepository workerRepository, RoleRepository roleRepository) {
        this.workerRepository = workerRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findRoleByName(String roleName) {
        return roleRepository.findRoleByName(roleName);
    }

    @Override
    public Optional<Worker> getWorkerById(Long id) {
        return workerRepository.findById(id);
    }

    @Override
    public void saveWorker(Worker worker) {
        workerRepository.save(worker);
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public int countWorkersByRole(String roleName) {
        return roleRepository.countWorkersByRoleName(roleName);
    }

    @Override
    public List<Worker> getWorkersByRoleId(Long roleId) {
        return workerRepository.findAllByRoleId(roleId);
    }

    @Override
    public void deleteWorker(Long id) {
        Worker worker = workerRepository.findById(id).orElse(null);
        if (worker != null) {
            // Remove the role association if it exists
            if (worker.getRole() != null) {
                worker.setRole(null);
            }
            workerRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public void updateWorkerInfo(Long id, String firstName, String lastName, String email, LocalDate birthDate, Role role) {
        workerRepository.updateWorkerInfo(id, firstName, lastName, email, birthDate, role);
    }

    @Override
    public List<Worker> searchByFullName(String fullName) {
        return workerRepository.searchByNameOrLastName(fullName);
    }
}