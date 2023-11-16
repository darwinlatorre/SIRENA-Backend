package co.edu.unicauca.SIRENABackend.models;

import co.edu.unicauca.SIRENABackend.common.enums.ClassroomTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de entidad que representa un tipo de salón de clases en el sistema.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classroom_types")
public class ClassroomTypeModel {

    /**
     * Identificador único del tipo de salón de clases.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cls_type_int_id", unique = true)
    private Integer id;

    /**
     * Nombre del tipo de salón de clases.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "cls_type_name", nullable = false, unique = true)
    private ClassroomTypeEnum name;

}
