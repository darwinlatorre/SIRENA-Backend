package co.edu.unicauca.SIRENABackend.models;

import co.edu.unicauca.SIRENABackend.common.enums.IncidenceTypeEnum;
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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "incidences_types")
public class IncidenceTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ins_type_int_id", unique = true)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ins_type_name", nullable = false, unique = true)
    private IncidenceTypeEnum name;

}
