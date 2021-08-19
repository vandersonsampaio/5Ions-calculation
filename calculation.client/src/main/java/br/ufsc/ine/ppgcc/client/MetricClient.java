package br.ufsc.ine.ppgcc.client;

import br.ufsc.ine.ppgcc.model.Entity;
import br.ufsc.ine.ppgcc.model.response.EntityResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient
public interface MetricClient {

    @GetMapping(value = "/metrics/calculate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<EntityResponse>> calculate(@RequestBody List<Entity> entities);
}
