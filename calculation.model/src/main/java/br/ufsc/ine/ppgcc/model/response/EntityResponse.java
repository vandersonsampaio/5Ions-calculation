package br.ufsc.ine.ppgcc.model.response;

import br.ufsc.ine.ppgcc.model.ConsolidatedMetric;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityResponse {

    private String name;
    private String type;
    private String source;

    private List<ConsolidatedMetric> metrics;
}
