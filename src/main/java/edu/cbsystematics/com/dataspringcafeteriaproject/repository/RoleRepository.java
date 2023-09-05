package edu.cbsystematics.com.dataspringcafeteriaproject.repository;

import edu.cbsystematics.com.dataspringcafeteriaproject.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    // Provides methods to access user role data from the database.

    // Custom method to find a role by name
    @Query("SELECT r FROM Role r WHERE lower(r.roleName) LIKE lower(concat('%', :name, '%'))")
    Optional<Role> findRoleByName(@Param("name") String name);

    // Count workers by Role
    @Query("SELECT COUNT(w) FROM Worker w WHERE lower(w.role.roleName) LIKE lower(concat('%', :name, '%'))")
    int countWorkersByRoleName(@Param("name") String name);

}