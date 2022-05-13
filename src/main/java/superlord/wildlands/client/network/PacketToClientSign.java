package superlord.wildlands.client.network;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkEvent.Context;
import net.minecraftforge.network.PacketDistributor;
import superlord.wildlands.client.render.gui.WLEditSignScreen;
import superlord.wildlands.common.entity.tile.WLSignTileEntity;

public class PacketToClientSign {
	
	private boolean messageIsValid;
	private BlockPos pos;
	
	public PacketToClientSign(BlockPos pos) {
		this.pos = pos;
		this.messageIsValid = true;
	}
	
	public PacketToClientSign(FriendlyByteBuf buffer) {
		this.pos = buffer.readBlockPos();
		this.messageIsValid = buffer.readBoolean();
	}
	
	public PacketToClientSign() {
		
	}
	
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(this.pos);
		buffer.writeBoolean(this.messageIsValid);
	}
	
	public static PacketToClientSign decoder(FriendlyByteBuf buffer) {
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
		if (Minecraft.getInstance().level != null) {
			BlockEntity tileEntity = Minecraft.getInstance().level.getBlockEntity(packet.pos);
			if (tileEntity instanceof WLSignTileEntity) {
				WLSignTileEntity signTileEntity = (WLSignTileEntity) tileEntity;
				Minecraft.getInstance().setScreen(new WLEditSignScreen(signTileEntity, Minecraft.getInstance().isTextFilteringEnabled()));
			}
		}
	}
	
	public static void sendMessage(ServerPlayer player, BlockPos pos) {
		WLPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new PacketToClientSign(pos));
	}

}
