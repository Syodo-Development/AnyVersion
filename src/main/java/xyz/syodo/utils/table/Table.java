package xyz.syodo.utils.table;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import lombok.Getter;
import xyz.syodo.utils.ProtocolVersion;
import xyz.syodo.utils.definition.Definition;

import java.util.Arrays;

@Getter
public class Table<E extends Definition> {

    private final ProtocolVersion version;
    private final ObjectOpenHashSet<E> content = new ObjectOpenHashSet<>();

    protected Table(ProtocolVersion version, E... values) {
        this.version = version;
        content.addAll(Arrays.asList(values));
    }
}
