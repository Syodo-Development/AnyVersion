package org.powernukkitx.anyversion.handler.handlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.cloudburstmc.protocol.bedrock.packet.ModalFormRequestPacket;
import org.powernukkitx.anyversion.handler.PacketHandler;
import org.powernukkitx.anyversion.manager.ProtocolPlayer;
import org.powernukkitx.anyversion.utils.ProtocolVersion;

public class ModalFormRequestHandler extends PacketHandler<ModalFormRequestPacket> {
    @Override
    public void handle(ProtocolPlayer player, ModalFormRequestPacket packet) {
        if(player.protocol() < ProtocolVersion.MINECRAFT_PE_1_21_70.protocol()) {
            JsonObject formData = JsonParser.parseString(packet.getFormData()).getAsJsonObject();
            String type = formData.get("type").getAsString();
            switch (type) {
                case "form" -> {
                    JsonArray elements = formData.get("elements").getAsJsonArray();
                    JsonArray copiedElements = new JsonArray();
                    for(JsonElement element : elements.asList()) {
                        JsonObject oElement = element.getAsJsonObject();
                        String oType = oElement.get("type").getAsString();
                        if(!(oType.equals("divider") || oType.equals("header") || oType.equals("label"))) copiedElements.add(element);
                    }
                    formData.remove("elements");
                    formData.add("buttons", copiedElements);
                }
                case "custom_form" -> {
                    JsonArray elements = formData.get("content").getAsJsonArray();
                    JsonArray copiedElements = new JsonArray();
                    for(JsonElement element : elements.asList()) {
                        JsonObject oElement = element.getAsJsonObject();
                        String oType = oElement.get("type").getAsString();
                        if(!(oType.equals("divider") || oType.equals("header"))) copiedElements.add(element);
                    }
                    formData.add("content", copiedElements);
                }
            }

            packet.setFormData(formData.toString());
        }
    }
}
