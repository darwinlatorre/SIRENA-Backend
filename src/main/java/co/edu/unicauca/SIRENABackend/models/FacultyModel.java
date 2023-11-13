package co.edu.unicauca.SIRENABackend.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "faculties")
public class FacultyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fac_int_id", unique = true)
    private Integer id;

    @Column(name = "fac_name", nullable = false, length = 40)
    private String name;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private BuildingModel building;
}

