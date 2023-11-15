package co.edu.unicauca.SIRENABackend.models;

import java.time.LocalDateTime;

import co.edu.unicauca.SIRENABackend.common.enums.BookingStateTypeEnum;
import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
    private LocalDateTime fechaSolicitud;

    @Column(name = "rsv_fecha_reserva_inicio", nullable = false)
    private LocalDateTime fechaReservaInicio;

    @Column(name = "rsv_hora_fin", nullable = false)
    private LocalDateTime horaFin;

    @Column(name = "rsv_num_estudiantes", nullable = true)
    private Integer numEstudiantes;

    @Default
    @Enumerated(EnumType.STRING)
    @Column(name = "rsv_estado", nullable = false, length = 20)
    private BookingStateTypeEnum estado = BookingStateTypeEnum.Pendiente;

    @Column(name = "rsv_detalles", nullable = true)
    private String detalles;

    @OneToOne
    @JoinColumn(name = "rsv_incidencia", nullable = true)
    private IncidenceModel incidencias;

    @ManyToOne
    @JoinColumn(name = "rsv_cls_int_id", nullable = false)
    private ClassroomModel classroom;

    @ManyToOne
    @JoinColumn(name = "rsv_usr_int_id", nullable = false)
    private UserModel user;
}
