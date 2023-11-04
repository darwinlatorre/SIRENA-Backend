package co.edu.unicauca.SIRENABackend.models;

import java.util.Date;

import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class BookingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rsv_int_id")
    private Integer id;

    @Column(name = "rsv_fecha_solicitud", nullable = false)
    private Date fechaSolicitud;

    @Column(name = "rsv_fecha_reserva_inicio", nullable = false)
    private Date fechaReservaInicio;

    @Column(name = "rsv_hora_fin", nullable = false)
    private Date horaFin;

    @Column(name = "rsv_num_estudiantes", nullable = true)
    private Integer numEstudiantes;

    @Default
    @Column(name = "rsv_estado", nullable = false, length = 20)
    private String estado = "Pendiente";

    @Column(name = "rsv_detalles", nullable = true)
    private String detalles;

    @OneToMany(mappedBy = "booking")
    @JoinColumn(name = "rsv_int_id", nullable = false)
    private IncidenceModel incidencias;

    @ManyToOne
    @JoinColumn(name = "rsv_cls_int_id", nullable = false)
    private ClassroomModel classroomID;

    @ManyToOne
    @JoinColumn(name = "rsv_usr_int_id", nullable = false)
    private UserModel userID;
}
