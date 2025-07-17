package aurelion.model;

import aurelion.enums.EnvironmentEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "databases")
public class DatabaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "environment", nullable = false)
    private EnvironmentEnum environment;

    @OneToMany(mappedBy = "database", cascade = CascadeType.ALL)
    private List<WildflyModel> wildflyModels;
}
