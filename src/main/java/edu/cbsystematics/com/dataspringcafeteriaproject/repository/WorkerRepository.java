package edu.cbsystematics.com.dataspringcafeteriaproject.repository;

import edu.cbsystematics.com.dataspringcafeteriaproject.models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    // Search by FirstName And LastName
    @Query("SELECT w FROM Worker w WHERE lower(w.firstName) LIKE lower(concat('%', :query, '%')) OR lower(w.lastName) LIKE lower(concat('%', :query, '%'))")
    List<Worker> searchByNameOrLastName(@Param("query") String query);
}