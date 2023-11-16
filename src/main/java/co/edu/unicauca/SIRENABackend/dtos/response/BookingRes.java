package co.edu.unicauca.SIRENABackend.dtos.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.unicauca.SIRENABackend.common.enums.BookingStateTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRes {

    @JsonProperty("rsv_id")
    private Integer id;
    @JsonProperty("rsv_fecha_solicitud")
    private LocalDateTime fechaSolicitud;
    @JsonProperty("rsv_fecha_reserva_inicio")
    private LocalDateTime fechaReservaInicio;
    @JsonProperty("rsv_hora_fin")
    private LocalDateTime horaFin;
    @JsonProperty("rsv_num_estudiantes")
    private Integer numEstudiantes;
    @JsonProperty("rsv_estado")
    private BookingStateTypeEnum estado;
    @JsonProperty("rsv_detalles")
    private String detalles;
    @JsonProperty("rsv_incidencia_id")
    private IncidenceRes incidencias;
    @JsonProperty("rsv_cls_id")
    private Integer classroom;
    @JsonProperty("rsv_usr_id")
    private String user;

}
