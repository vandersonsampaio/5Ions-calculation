package br.ufsc.ine.ppgcc.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    private String id;
    private Date timestamp;
    private List<Mention> mentions;
    private Sentiment sentimentPositive;
    private Sentiment sentimentNegative;
}
