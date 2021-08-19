package br.ufsc.ine.ppgcc.model;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsolidatedMetric {

    private Date timestamp;
    private double score;
}
