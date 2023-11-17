package co.edu.unicauca.SIRENABackend.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa estadísticas en el sistema")
public class StatisticsModel {

    @Schema(description = "Identificador único de la entidad", example = "1")
    private Integer entity_id;

    @Schema(description = "Lista de identificadores de reservas asociadas a la entidad")
    private List<Integer> bokings_ids;
}
