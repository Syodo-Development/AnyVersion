package xyz.syodo.utils.definition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.syodo.utils.transformer.entity.EntityTransformer;
import xyz.syodo.utils.transformer.entity.UnknownEntityTransformer;

@Getter
@AllArgsConstructor
public class EntityDefinition extends Definition {

    private final String networkId;
    private final EntityTransformer downgrade;

    public static EntityDefinition of(String networkId) {
        return of(networkId, new UnknownEntityTransformer());
    }

    public static EntityDefinition of(String networkId, EntityTransformer transformer) {
        return new EntityDefinition(networkId, transformer);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof String definition) {
            return definition.equals(networkId);
        }
        return super.equals(obj);
    }
}
