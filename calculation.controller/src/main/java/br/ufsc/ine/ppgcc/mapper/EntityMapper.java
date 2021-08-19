package br.ufsc.ine.ppgcc.mapper;

import br.ufsc.ine.ppgcc.model.Entity;
import br.ufsc.ine.ppgcc.model.response.EntityResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    EntityResponse toDto(Entity model);

    List<EntityResponse> toEntityResponseList(List<Entity> model);
}
