package co.edu.unicauca.SIRENABackend.models;

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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "insidencias")
public class InsidenciasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ins_int_id", unique = true)
    private Integer id;

    @Column(name = "ins_name", nullable = false, length = 20)
    private String name;

    //Es la mismas que reserva == rsv_codigo_insidencias
    @Column(name = "ins_key", nullable = false, length = 20)
    private String key;

    //Es la misma que user ID == rsv_usr_int_id
    @ManyToOne
    @JoinColumn(name = "ins_teacher_name", nullable = false)
    private UserModel teacherName;

    @ManyToOne
    @JoinColumn(name = "ins_insidencias", nullable = false)
    private ReservaModel insidencias;

    @ManyToOne
    @JoinColumn(name = "ins_type", nullable = false)
    private InsidenciasTypeModel insidenciaType;
}
