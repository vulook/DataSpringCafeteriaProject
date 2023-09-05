package edu.cbsystematics.com.dataspringcafeteriaproject.repository;

import edu.cbsystematics.com.dataspringcafeteriaproject.models.Role;
import edu.cbsystematics.com.dataspringcafeteriaproject.models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    // Update Worker Information
    @Modifying
    @Transactional
    @Query("UPDATE Worker w SET w.firstName = :firstName, w.lastName = :lastName, w.email = :email, w.birthDate = :birthDate, w.role = :role WHERE w.id = :id")
    void updateWorkerInfo(
            @Param("id") Long id,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email,
            @Param("birthDate") LocalDate birthDate,
            @Param("role") Role role
    );

    // Search by FirstName Or/And LastName
    @Query("SELECT w FROM Worker w WHERE lower(w.firstName) LIKE lower(concat('%', :query, '%')) OR lower(w.lastName) LIKE lower(concat('%', :query, '%'))")
    List<Worker> searchByNameOrLastName(@Param("query") String query);


    // Get workers by Role ID
    List<Worker> findAllByRoleId(Long roleId);

}