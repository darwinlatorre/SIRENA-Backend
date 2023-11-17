package co.edu.unicauca.SIRENABackend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsModel {
    private Integer entity_id;
    private List<Integer> bokings_ids;
}
