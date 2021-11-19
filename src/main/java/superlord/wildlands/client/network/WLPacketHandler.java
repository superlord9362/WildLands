package superlord.wildlands.client.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import superlord.wildlands.WildLands;

public class WLPacketHandler {
	
	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(WildLands.MOD_ID, "network"), () -> WLPacketHandler.PROTOCOL_VERSION, WLPacketHandler.PROTOCOL_VERSION::equals, WLPacketHandler.PROTOCOL_VERSION::equals);
	private static final String PROTOCOL_VERSION = "1";
	private static int ID = 0;
	
	public static void registerPackets() {
		WLPacketHandler.CHANNEL.messageBuilder(PacketToClientSign.class, WLPacketHandler.ID++).decoder(PacketToClientSign::decoder).encoder(PacketToClientSign::encode).consumer(PacketToClientSign::handle).add();
	}

}
