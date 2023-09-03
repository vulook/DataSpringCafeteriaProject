package edu.cbsystematics.com.dataspringcafeteriaproject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "workers")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "smallint(3)")
    private Long id;

    @Column(name = "first_name", nullable = false, columnDefinition = "varchar(40)")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "varchar(40)")
    private String lastName;

    @Column(name = "email", nullable = false, columnDefinition = "varchar(30)")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", columnDefinition = "smallint(3)")
    @ToString.Exclude
    private Role role;

    public Worker(String firstName, String lastName, String email, LocalDate birthDate, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.role = role;
    }

    public int getAge() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(this.birthDate, currentDate);
        return period.getYears();
    }

}