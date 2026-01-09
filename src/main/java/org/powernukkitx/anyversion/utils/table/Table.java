package org.powernukkitx.anyversion.utils.table;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Getter;
import org.powernukkitx.anyversion.utils.ProtocolVersion;
import org.powernukkitx.anyversion.utils.definition.Definition;

import java.util.Arrays;

@Getter
public class Table<E extends Definition> {

    private final ProtocolVersion version;
    private final ObjectArrayList<E> content = new ObjectArrayList<>();

    protected Table(ProtocolVersion version, E... values) {
        this.version = version;
        this.addAll(values);
    }

    public final void addAll(E... values) {
        Arrays.stream(values).forEach(content::addFirst);
    }
}
