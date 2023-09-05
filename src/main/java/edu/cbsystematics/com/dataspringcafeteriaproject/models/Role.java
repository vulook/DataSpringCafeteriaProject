package edu.cbsystematics.com.dataspringcafeteriaproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @Column(name = "role_name", nullable = false, columnDefinition = "varchar(15)")
    private String roleName;

    @Column(name = "description", nullable = false, columnDefinition = "varchar(200)")
    private String description;

    @OneToMany(mappedBy = "role")
    private List<Worker> workers;

    public Role(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }

}