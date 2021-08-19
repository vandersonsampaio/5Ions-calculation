package br.ufsc.ine.ppgcc.model;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Entity {

    private String name;
    private String type;
    private String source;
    private List<Document> documents;
    private List<ConsolidatedMetric> metrics;
}
