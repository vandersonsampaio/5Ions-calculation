package br.ufsc.ine.ppgcc.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mention {

    private String surfaceName;
    private Sentiment sentiment;
}
