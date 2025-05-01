package xyz.syodo.handler.handlers;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementDivider;
import cn.nukkit.form.element.ElementHeader;
import cn.nukkit.form.element.custom.ElementCustom;
import cn.nukkit.form.element.simple.ElementButton;
import cn.nukkit.form.element.simple.ElementSimple;
import cn.nukkit.form.window.CustomForm;
import cn.nukkit.form.window.Form;
import cn.nukkit.form.window.SimpleForm;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.packet.ModalFormResponsePacket;
import xyz.syodo.handler.PacketHandler;
import xyz.syodo.manager.ProtocolPlayer;
import xyz.syodo.utils.ProtocolVersion;

@Slf4j
public class ModalFormResponseHandler extends PacketHandler<ModalFormResponsePacket> {
    @Override
    public void handle(ProtocolPlayer player, ModalFormResponsePacket packet) {
        if(player.protocol() < ProtocolVersion.MINECRAFT_PE_1_21_70.protocol()) {
            Player p = player.player().getPlayer();
            Form<?> form = p.getFormWindows().get(packet.getFormId());
            if(form != null) {
                if(form instanceof SimpleForm form1) {
                    int res = Integer.parseInt(packet.getFormData().trim());
                    int element = -1;
                    ElementSimple[] elements = form1.elements().keySet().toArray(new ElementSimple[0]);

                    for(int idx = 0; idx < elements.length; idx++) {
                        ElementSimple element1 = elements[idx];
                        if(element1 instanceof ElementButton) {
                            element++;
                            if(element == res) {
                                packet.setFormData(String.valueOf(idx));
                                return;
                            }
                        }
                    }
                } else if(form instanceof CustomForm form1) {
                    JsonArray res = JsonParser.parseString(packet.getFormData().trim()).getAsJsonArray();
                    JsonArray array = new JsonArray();
                    int element = -1;
                    for(int idx = 0; idx < form1.elements().size(); idx++) {
                        ElementCustom element1 = form1.elements().get(idx);
                        if(!(element1 instanceof ElementHeader || element1 instanceof ElementDivider)) {
                            element++;
                            array.add(res.get(element));
                        } else array.add("");
                    }
                }
            }
        }
    }
}
