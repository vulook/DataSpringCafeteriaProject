package edu.cbsystematics.com.dataspringcafeteriaproject.repository;

import edu.cbsystematics.com.dataspringcafeteriaproject.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    // Provides methods to access user role data from the database.

}