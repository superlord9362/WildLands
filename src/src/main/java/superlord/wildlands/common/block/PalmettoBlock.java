package superlord.wildlands.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IForgeShearable;

public class PalmettoBlock extends DoublePlantBlock implements IForgeShearable {

	public PalmettoBlock(Properties properties) {
		super(properties);
	}
	
	@OnlyIn(Dist.CLIENT)
	public float getAmbientOcclusionLightValue(BlockState state, LevelReader worldIn, BlockPos pos) {
		return 1.0F;
	}

	public boolean propagatesSkylightDown(BlockState state, LevelReader reader, BlockPos pos) {
		return true;
	}
	
	public Block.OffsetType getOffsetType() {
	      return Block.OffsetType.XZ;
	   }

}
