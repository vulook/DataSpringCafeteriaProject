package edu.cbsystematics.com.dataspringcafeteriaproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "smallint(3)")
    private Long id;

    @Column(name = "last_name", nullable = false, columnDefinition = "varchar(15)")
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }

}