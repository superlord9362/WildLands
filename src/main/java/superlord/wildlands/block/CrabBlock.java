package superlord.wildlands.block;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import superlord.wildlands.entity.CrabEntity;
import superlord.wildlands.init.EntityInit;

public class CrabBlock extends Block {
	private final Block mimickedBlock;
	private static final Map<Block, Block> normalToInfectedMap = Maps.newIdentityHashMap();

	public CrabBlock(Block blockIn, AbstractBlock.Properties properties) {
		super(properties);
		this.mimickedBlock = blockIn;
		normalToInfectedMap.put(blockIn, this);
	}

	public Block getMimickedBlock() {
		return this.mimickedBlock;
	}

	public static boolean canContainCrab(BlockState state) {
		return normalToInfectedMap.containsKey(state.getBlock());
	}

	private void spawnCrab(ServerWorld world, BlockPos pos) {
		CrabEntity crabEntity = EntityInit.CRAB.get().create(world);
		crabEntity.setLocationAndAngles((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, 0.0F, 0.0F);
		world.addEntity(crabEntity);
		crabEntity.spawnExplosionParticle();
	}

	@SuppressWarnings("deprecation")
	public void spawnAdditionalDrops(BlockState state, ServerWorld worldIn, BlockPos pos, ItemStack stack) {
		super.spawnAdditionalDrops(state, worldIn, pos, stack);
		if (worldIn.getGameRules().getBoolean(GameRules.DO_TILE_DROPS) && EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) == 0) {
			this.spawnCrab(worldIn, pos);
		}

	}

	/**
	 * Called when this Block is destroyed by an Explosion
	 */
	public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
		if (worldIn instanceof ServerWorld) {
			this.spawnCrab((ServerWorld)worldIn, pos);
		}

	}

	public static BlockState burrow(Block blockIn) {
		return normalToInfectedMap.get(blockIn).getDefaultState();
	}

}
