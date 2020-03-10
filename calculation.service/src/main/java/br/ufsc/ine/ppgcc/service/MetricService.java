package br.ufsc.ine.ppgcc.service;

import java.util.List;
import java.util.Map;

public interface MetricService {

    Map<Long, Map<Long, Double>> calculate(Map<Long, Map<Long, Double>> indicators, List<Long> interval);
}
