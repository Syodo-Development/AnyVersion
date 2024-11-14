package xyz.syodo.utils;

import java.util.Arrays;

public enum ProtocolVersion {

    MINECRAFT_PE_1_21_0(685, "1.21.0"),
    MINECRAFT_PE_1_21_2(686, "1.21.2"),
    MINECRAFT_PE_1_21_20(712, "1.21.20"),
    MINECRAFT_PE_1_21_30(729,"1.21.30"),
    MINECRAFT_PE_1_21_40(748, "1.21.40");

    private final int PROTOCOL;
    private final String VERSION;

    ProtocolVersion(int protocol, String version) {
        PROTOCOL = protocol;
        VERSION = version;
    }

    public int protocol() {
        return this.PROTOCOL;
    }

    public String version() {
        return this.VERSION;
    }

    public static ProtocolVersion getMin() {
        return values()[0];
    }

    public static ProtocolVersion get(int protocol) {
        return Arrays.stream(values()).filter(p -> p.protocol() == protocol).findAny().get();
    }

}
