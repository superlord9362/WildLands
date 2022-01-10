package superlord.wildlands.common.world.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import superlord.wildlands.common.world.feature.config.SmolderingLogFeatureConfig;
import superlord.wildlands.init.WildLandsBlocks;

public class SmolderingLogFeature extends Feature<SmolderingLogFeatureConfig>{
	
	public SmolderingLogFeature(Codec<SmolderingLogFeatureConfig> codec) {
		super(codec);
	}
	
	protected static boolean isSoil(ISeedReader reader, BlockPos pos, net.minecraftforge.common.IPlantable sapling) {
		if (!(reader instanceof net.minecraft.world.IBlockReader) || sapling == null)
			return isDirtOrGrassBlock(reader, pos);
		return reader.hasBlockState(pos, state -> state.getBlock() == WildLandsBlocks.CHARRED_GRASS);
	}
	
	@Deprecated
	public static boolean isDirtOrGrassBlock(ISeedReader world, BlockPos pos) {
		return world.hasBlockState(pos, (p_227221_0_) -> {
			return isDirt(p_227221_0_.getBlock());
		});
	}
	
	@SuppressWarnings("deprecation")
	public static boolean isAir(ISeedReader world, BlockPos pos) {
		if(world instanceof net.minecraft.world.IBlockReader)
			return world.hasBlockState(pos, state -> state.isAir((net.minecraft.world.IBlockReader)world, pos));
		return world.hasBlockState(pos, BlockState::isAir);
	}
	
	@SuppressWarnings("deprecation")
	public static boolean isAirOrLeaves(ISeedReader world, BlockPos pos) {
		if (world instanceof net.minecraft.world.IWorldReader)
			return world.hasBlockState(pos, state -> state.canBeReplacedByLeaves((net.minecraft.world.IWorldReader)world, pos));
		return world.hasBlockState(pos, (p_227223_0_) -> {
			return p_227223_0_.isAir() || p_227223_0_.isIn(BlockTags.LEAVES);
		});
	}
	
	@Override
	public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos startPosition, SmolderingLogFeatureConfig config) {
		BlockPos pos = startPosition;
		while (pos.getY() > 1 && isAirOrLeaves(world, pos)) pos = pos.down();
		if(!isSoil(world, pos, null)) {
			return false;
		}
		pos = pos.up();
		int height = rand.nextInt(2) + 2;
		int x = pos.getX();
		int z = pos.getZ();
		if(pos.getY() >= 1 && pos.getY() + height + 2 <= 256) {
			for (int j = pos.getY(); j <= pos.getY() + height; j++) {
				if (isAir(world, new BlockPos(x, j, z))) setBlockState(world, new BlockPos(x, j, z), WildLandsBlocks.CHARRED_LOG.getDefaultState());
			}
			if (isAir(world, new BlockPos(x, pos.getY() + height + 1, z))) setBlockState(world, new BlockPos(x, pos.getY() + height + 1, z), WildLandsBlocks.SMOLDERING_LOG.get().getDefaultState());
			return true;
		}
		return false;
	}

}
