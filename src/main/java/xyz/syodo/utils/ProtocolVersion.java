package xyz.syodo.utils;

import lombok.Getter;

import java.util.Arrays;

public enum ProtocolVersion {

    MINECRAFT_PE_1_21_20(712, "1.21.20"),
    MINECRAFT_PE_1_21_30(729,"1.21.30"),
    MINECRAFT_PE_1_21_40(748, "1.21.40"),
    MINECRAFT_PE_1_21_50(766, "1.21.50");

    @Getter
    private static final ProtocolVersion[] versions = values();

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
        return versions[0];
    }

    public static ProtocolVersion get(int protocol) {
        return Arrays.stream(versions).filter(p -> p.protocol() == protocol).findAny().get();
    }

}
