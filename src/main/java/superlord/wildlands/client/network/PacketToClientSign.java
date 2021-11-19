package superlord.wildlands.client.network;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;
import net.minecraftforge.fml.network.PacketDistributor;
import superlord.wildlands.client.render.gui.WLEditSignScreen;
import superlord.wildlands.common.entity.tile.WLSignTileEntity;

public class PacketToClientSign {
	
	private boolean messageIsValid;
	private BlockPos pos;
	
	public PacketToClientSign(BlockPos pos) {
		this.pos = pos;
		this.messageIsValid = true;
	}
	
	public PacketToClientSign(PacketBuffer buffer) {
		this.pos = buffer.readBlockPos();
		this.messageIsValid = buffer.readBoolean();
	}
	
	public PacketToClientSign() {
		
	}
	
	public void encode(PacketBuffer buffer) {
		buffer.writeBlockPos(this.pos);
		buffer.writeBoolean(this.messageIsValid);
	}
	
	public static PacketToClientSign decoder(PacketBuffer buffer) {
		PacketToClientSign packet = new PacketToClientSign();
		packet.pos = buffer.readBlockPos();
		packet.messageIsValid = buffer.readBoolean();
		return packet;
	}
	
	public static void handle(PacketToClientSign packet, Supplier<NetworkEvent.Context> supplier) {
		Context context = supplier.get();
		if (context != null) {
			if (packet != null && packet.messageIsValid) {
				context.enqueueWork(() -> PacketToClientSign.handleOnClient(packet));
				context.setPacketHandled(true);
			} else {
				context.setPacketHandled(false);
			}
		}
	}
	
	@SuppressWarnings("resource")
	@OnlyIn(Dist.CLIENT)
	private static void handleOnClient(PacketToClientSign packet) {
		if (Minecraft.getInstance().world != null) {
			TileEntity tileEntity = Minecraft.getInstance().world.getTileEntity(packet.pos);
			if (tileEntity instanceof WLSignTileEntity) {
				WLSignTileEntity signTileEntity = (WLSignTileEntity) tileEntity;
				Minecraft.getInstance().displayGuiScreen(new WLEditSignScreen(signTileEntity));
			}
		}
	}
	
	public static void sendMessage(ServerPlayerEntity player, BlockPos pos) {
		WLPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new PacketToClientSign(pos));
	}

}
