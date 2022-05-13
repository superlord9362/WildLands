package superlord.wildlands.common.item;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import superlord.wildlands.client.network.PacketToClientSign;

public class WLSignItem extends SignItem {
	
	public WLSignItem(Item.Properties properties, Block floorBlock, Block wallBlock) {
		super(properties, floorBlock, wallBlock);
	}
	
	@Override
	protected boolean updateCustomBlockEntityTag(BlockPos pos, Level world, @Nullable Player entity, ItemStack stack, BlockState state) {
		boolean flag =  super.updateCustomBlockEntityTag(pos, world, entity, stack, state);
		if (!world.isClientSide && !flag && entity != null) {
			PacketToClientSign.sendMessage((ServerPlayer) entity, pos);
		}
		return flag;
	}
 
}
