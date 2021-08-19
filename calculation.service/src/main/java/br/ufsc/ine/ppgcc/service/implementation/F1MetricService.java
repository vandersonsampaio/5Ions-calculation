package br.ufsc.ine.ppgcc.service.implementation;

import br.ufsc.ine.ppgcc.model.*;
import br.ufsc.ine.ppgcc.service.interfaces.MetricService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class F1MetricService implements MetricService {

    @Override
    public List<Entity> calculate(List<Entity> entities) {
        consolidateByDocument(entities);
        consolidateByEntity(entities);

        return entities;
    }

    @Override
    public List<Entity> consolidateByEntity(List<Entity> entities) {
        for(Entity entity : entities){
            Map<Date, List<Document>> groupByDate = entity.getDocuments().stream().collect(Collectors.groupingBy(Document::getTimestamp));

            List<ConsolidatedMetric> consolidatedMetricList = new ArrayList<>();
            double scorePos = 0;
            double scoreNeg = 0;

            for(Date date : groupByDate.keySet().stream().sorted(Date::compareTo).collect(Collectors.toList())){
                scorePos += groupByDate.get(date).stream().mapToDouble(d -> d.getSentimentPositive().getScore()).sum();
                scoreNeg += groupByDate.get(date).stream().mapToDouble(d -> d.getSentimentNegative().getScore()).sum();

                double metricValue = metric(scorePos, scoreNeg);

                 consolidatedMetricList.add(builderConsolidatedMetric(date, metricValue));
            }

            entity.setMetrics(consolidatedMetricList);
        }

        return entities;
    }

    @Override
    public List<Entity> consolidateByDocument(List<Entity> entities) {
        for(Entity entity : entities){
            for(Document document : entity.getDocuments()){
                double sentPos = document.getMentions().stream().filter(m -> m.getSentiment().getScore() > 0).mapToDouble(m -> m.getSentiment().getScore()).sum();
                double sentNeg = document.getMentions().stream().filter(m -> m.getSentiment().getScore() < 0).mapToDouble(m -> m.getSentiment().getScore()).sum();

                document.setSentimentPositive(builderSentiment(sentPos));
                document.setSentimentNegative(builderSentiment(sentNeg));
            }
        }
        return entities;
    }

    private ConsolidatedMetric builderConsolidatedMetric(Date date, double score) {
        return ConsolidatedMetric.builder().timestamp(date).score(score).build();
    }

    private Sentiment builderSentiment(double score) {
        return Sentiment.builder().score(score).build();
    }

    private double metric(double positiveSentiment, double negativeSentiment){
        return negativeSentiment == 0 ? 0 : positiveSentiment / negativeSentiment;
    }
}
