package aurelion.model;

import aurelion.enums.EnvironmentEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    public WildflyModel(String name, EnvironmentEnum environment, LocalDateTime lastModified, ProjectModel project, DatabaseModel database) {
        this.name = name;
        this.environment = environment;
        this.lastModified = lastModified;
        this.project = project;
        this.database = database;
    }

    @PreUpdate
    public void preUpdate() {
        this.lastModified = LocalDateTime.now();
    }
} 