package br.ufsc.ine.ppgcc.service.interfaces;

import br.ufsc.ine.ppgcc.model.Entity;

import java.util.List;

public interface MetricService {

    List<Entity> calculate(List<Entity> entities);

    List<Entity> consolidateByDocument(List<Entity> entities);

    List<Entity> consolidateByEntity(List<Entity> entities);
}
