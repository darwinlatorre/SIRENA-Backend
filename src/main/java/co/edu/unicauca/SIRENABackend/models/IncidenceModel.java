package co.edu.unicauca.SIRENABackend.models;

import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de entidad que representa una incidencia en el sistema.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "incidences")
public class IncidenceModel {

    /**
     * Identificador único de la incidencia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ins_int_id", unique = true)
    private Integer id;

    /**
     * Nombre de la incidencia.
     */
    @Column(name = "ins_name", nullable = false, length = 40)
    private String name;

    /**
     * Profesor asociado a la incidencia.
     */
    @ManyToOne
    @JoinColumn(name = "ins_teacher_name", nullable = false)
    private UserModel teacherName;

    /**
     * Tipo de incidencia asociado.
     */
    @ManyToOne
    @JoinColumn(name = "ins_type", nullable = false)
    private IncidenceTypeModel insidenciaType;
}
