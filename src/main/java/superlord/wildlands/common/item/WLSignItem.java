package superlord.wildlands.common.item;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SignItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import superlord.wildlands.client.network.PacketToClientSign;

public class WLSignItem extends SignItem {
	
	public WLSignItem(Item.Properties properties, Block floorBlock, Block wallBlock) {
		super(properties, floorBlock, wallBlock);
	}
	
	@Override
	protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity entity, ItemStack stack, BlockState state) {
		boolean flag =  super.onBlockPlaced(pos, world, entity, stack, state);
		if (!world.isRemote && !flag && entity != null) {
			PacketToClientSign.sendMessage((ServerPlayerEntity) entity, pos);
		}
		return flag;
	}
 
}
