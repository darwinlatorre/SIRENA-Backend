package co.edu.unicauca.SIRENABackend.models;

import java.util.Date;

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
@Table(name = "reserva")
public class ReservaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rsv_id")
    private Long id;

    @Column(name = "rsv_idUsuario")
    private String idUsuario;

    @Column(name = "rsv_idSalon")
    private String idSalon;

    @Column(name = "rsv_fechaSolicitud")
    private Date fechaSolicitud;

    @Column(name = "rsv_fechaReserva")
    private Date fechaReserva;

    @Column(name = "rsv_horaInicio")
    private int horaInicio;

    @Column(name = "rsv_horaFin")
    private int horaFin;

    @Column(name = "rsv_numEstudiantes")
    private int numEstudiantes;

    @Column(name = "rsv_estado")
    private String estado;

    @Column(name = "rsv_detalles")
    private String detalles;
}

