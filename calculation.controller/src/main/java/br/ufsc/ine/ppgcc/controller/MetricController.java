package br.ufsc.ine.ppgcc.controller;

import br.ufsc.ine.ppgcc.mapper.EntityMapper;
import br.ufsc.ine.ppgcc.model.Entity;
import br.ufsc.ine.ppgcc.model.response.EntityResponse;
import br.ufsc.ine.ppgcc.service.interfaces.MetricService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="metrics", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class MetricController {

    private final MetricService service;
    private final EntityMapper mapper;

    public MetricController(MetricService service, EntityMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(value = "/calculate")
    public ResponseEntity<List<EntityResponse>> calculate(@RequestBody List<Entity> entities){
        return ResponseEntity.ok(mapper.toEntityResponseList(service.calculate(entities)));
    }

    @GetMapping(value = "/consolidate-by-document")
    public ResponseEntity<List<EntityResponse>> consolidateByDocument(@RequestBody List<Entity> entities){
        return ResponseEntity.ok(mapper.toEntityResponseList(service.consolidateByDocument(entities)));
    }

    @GetMapping(value = "/consolidate-by-entity")
    public ResponseEntity<List<EntityResponse>> consolidateByEntity(@RequestBody List<Entity> entities){
        return ResponseEntity.ok(mapper.toEntityResponseList(service.consolidateByEntity(entities)));
    }
}
