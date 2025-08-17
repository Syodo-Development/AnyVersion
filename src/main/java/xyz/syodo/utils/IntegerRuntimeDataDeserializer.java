package xyz.syodo.utils;

import cn.nukkit.level.format.palette.RuntimeDataDeserializer;

public class IntegerRuntimeDataDeserializer implements RuntimeDataDeserializer<Integer> {
    @Override
    public Integer deserialize(int i) {
        return i;
    }
}
