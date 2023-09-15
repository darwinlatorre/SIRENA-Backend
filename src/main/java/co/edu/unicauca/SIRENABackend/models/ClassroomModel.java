package co.edu.unicauca.SIRENABackend.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "Classroom")
public class ClassroomModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cls_id", unique = true)
    private  Integer clsId;

    @Column(name = "cls_name", nullable = false, length = 20)
    private  String clsName;

    @Column(name = "cls_capacity", nullable = false, length = 20)
    private Integer clsCapacity;

    @Column(name = "cls_state", nullable = false, length = 20)
    private  String clsState;

    @Column(name = "cls_building", nullable = false, length = 20)
    private  String clsBuilding;

    @Column(name = "cls_type", nullable = false, length = 20)
    private  String clsType;
}
