package xyz.syodo.utils.definition;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemDefinition extends Definition {

    private final String namespaceId;

    public static ItemDefinition of(String namespaceId) {
        return new ItemDefinition(namespaceId);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ItemDefinition definition) {
            return definition.namespaceId.equals(namespaceId);
        }
        return false;
    }
}
