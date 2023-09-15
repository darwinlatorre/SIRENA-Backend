package co.edu.unicauca.SIRENABackend.models;

import java.util.Date;
import jakarta.persistence.*;

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

    // Constructor, getters, and setters

    public ReservaModel() {
    }

    public ReservaModel(String idUsuario, String idSalon, Date fechaSolicitud, Date fechaReserva,
                        int horaInicio, int horaFin, int numEstudiantes, String estado, String detalles) {
        this.idUsuario = idUsuario;
        this.idSalon = idSalon;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaReserva = fechaReserva;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.numEstudiantes = numEstudiantes;
        this.estado = estado;
        this.detalles = detalles;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdSalon() {
        return idSalon;
    }

    public void setIdSalon(String idSalon) {
        this.idSalon = idSalon;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    public int getNumEstudiantes() {
        return numEstudiantes;
    }

    public void setNumEstudiantes(int numEstudiantes) {
        this.numEstudiantes = numEstudiantes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}

