package br.ufsc.ine.ppgcc.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class F1MetricService implements MetricService {

    @Override
    public Map<Long, Map<Long, Double>> calculate(Map<Long, Map<Long, Double>> indicators, List<Long> interval) {
        Map<Long, Map<Long, Double>> result = new HashMap<>();

        for(Long entityId : indicators.keySet()){
            Map<Long, Double> mentionsSentiment = indicators.get(entityId);

            Map<Long, Double> metricByInterval = new HashMap<>();

            for(Long timestamp : interval){
                List<Long> occurrences = mentionsSentiment.keySet().stream().filter(m -> m <= timestamp).collect(Collectors.toList());

                double positiveSentiment = 0;
                double negativeSentiment = 0;

                for(Long occurrence : occurrences){
                    double valueSentiment = mentionsSentiment.get(occurrence);

                    if(valueSentiment >= 0){
                        positiveSentiment += valueSentiment;
                    } else {
                        negativeSentiment += valueSentiment;
                    }
                }

                double calculateMetric = metric(positiveSentiment, negativeSentiment);

                metricByInterval.put(timestamp, calculateMetric);
            }

            if(metricByInterval.size() > 0){
                result.put(entityId, metricByInterval);
            }
        }

        return result;
    }

    private double metric(double positiveSentiment, double negativeSentiment){
        return positiveSentiment / negativeSentiment;
    }
}
