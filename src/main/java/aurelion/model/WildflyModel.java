package aurelion.model;

import aurelion.enums.EnvironmentEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "wildflies")
public class WildflyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "environment", nullable = false)
    private EnvironmentEnum environment;

    @Column(name = "last_modified", nullable = false)
    private LocalDateTime lastModified;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectModel project;

    @ManyToOne
    @JoinColumn(name = "database_id")
    private DatabaseModel database;

    @PreUpdate
    public void preUpdate() {
        this.lastModified = LocalDateTime.now();
    }
} 