package co.edu.unicauca.SIRENABackend.models;

import java.io.Serializable;
import java.util.Set;

import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de entidad que representa un salón de clases en el sistema.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classrooms")
public class ClassroomModel implements Serializable {

    /**
     * Identificador único del salón de clases.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cls_int_id", unique = true)
    private Integer id;

    /**
     * Nombre del salón de clases.
     */
    @Column(name = "cls_name", nullable = false, length = 40, unique = true)
    private String name;

    /**
     * Capacidad del salón de clases.
     */
    @Column(name = "cls_capacity", nullable = false)
    private Integer capacity;

    /**
     * Estado del salón de clases.
     */
    @Column(name = "cls_state", nullable = false, length = 100)
    private String state;

    /**
     * Edificio al que pertenece el salón de clases.
     */
    @ManyToOne
    @JoinColumn(name = "cls_building", nullable = false)
    private BuildingModel building;

    /**
     * Tipo de salón de clases.
     */
    @ManyToOne
    @JoinColumn(name = "cls_type", nullable = false)
    private ClassroomTypeModel classroomType;

    /**
     * Lista de usuarios asignados al salón de clases.
     */
    @ManyToMany(mappedBy = "classroom_assigned")
    private Set<UserModel> userList;

}
