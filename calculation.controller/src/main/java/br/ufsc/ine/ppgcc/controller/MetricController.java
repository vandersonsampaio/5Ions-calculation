package br.ufsc.ine.ppgcc.controller;

import br.ufsc.ine.ppgcc.service.MetricService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("metrics")
public class MetricController {

    private final MetricService metricService;

    public MetricController(MetricService metricService){
        this.metricService = metricService;
    }
}
