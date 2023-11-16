package co.edu.unicauca.SIRENABackend.models;

import java.time.LocalDateTime;

import co.edu.unicauca.SIRENABackend.common.enums.BookingStateTypeEnum;
import co.edu.unicauca.SIRENABackend.security.models.UserModel;
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

/**
 * Modelo de entidad que representa una reserva (booking) en el sistema.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class BookingModel {

    /**
     * Identificador único de la reserva.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rsv_int_id")
    private Integer id;

    /**
     * Fecha y hora en que se realizó la solicitud de la reserva.
     */
    @Column(name = "rsv_fecha_solicitud", nullable = false)
    private LocalDateTime fechaSolicitud;

    /**
     * Fecha y hora de inicio de la reserva.
     */
    @Column(name = "rsv_fecha_reserva_inicio", nullable = false)
    private LocalDateTime fechaReservaInicio;

    /**
     * Fecha y hora de finalización de la reserva.
     */
    @Column(name = "rsv_hora_fin", nullable = false)
    private LocalDateTime horaFin;

    /**
     * Número de estudiantes para los que se realiza la reserva.
     */
    @Column(name = "rsv_num_estudiantes", nullable = true)
    private Integer numEstudiantes;

    /**
     * Estado actual de la reserva.
     */
    @Default
    @Enumerated(EnumType.STRING)
    @Column(name = "rsv_estado", nullable = false, length = 20)
    private BookingStateTypeEnum estado = BookingStateTypeEnum.Pendiente;

    /**
     * Detalles adicionales de la reserva.
     */
    @Column(name = "rsv_detalles", nullable = true)
    private String detalles;

    /**
     * Incidencia asociada a la reserva, si la hay.
     */
    @OneToOne
    @JoinColumn(name = "rsv_incidencia", nullable = true)
    private IncidenceModel incidencias;

    /**
     * Aula de clases asociada a la reserva.
     */
    @ManyToOne
    @JoinColumn(name = "rsv_cls_int_id", nullable = false)
    private ClassroomModel classroom;

    /**
     * Usuario que realizó la reserva.
     */
    @ManyToOne
    @JoinColumn(name = "rsv_usr_int_id", nullable = false)
    private UserModel user;
}
