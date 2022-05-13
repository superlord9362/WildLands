package superlord.wildlands.common.world.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import superlord.wildlands.common.world.feature.config.SmolderingLogFeatureConfig;
import superlord.wildlands.init.WildLandsBlocks;

public class SmolderingLogFeature extends Feature<SmolderingLogFeatureConfig>{
	
	public SmolderingLogFeature(Codec<SmolderingLogFeatureConfig> codec) {
		super(codec);
	}
	
	protected static boolean isSoil(LevelSimulatedReader reader, BlockPos pos, net.minecraftforge.common.IPlantable sapling) {
		if (!(reader instanceof net.minecraft.world.level.BlockGetter) || sapling == null)
			return isDirtOrGrassBlock(reader, pos);
		return reader.isStateAtPosition(pos, state -> state.getBlock() == WildLandsBlocks.CHARRED_GRASS.get());
	}
	
	@Deprecated
	public static boolean isDirtOrGrassBlock(LevelSimulatedReader world, BlockPos pos) {
		return world.isStateAtPosition(pos, (p_227221_0_) -> {
			return isDirt(p_227221_0_.getBlock().defaultBlockState());
		});
	}
	
	public static boolean isAir(LevelSimulatedReader world, BlockPos pos) {
		if(world instanceof net.minecraft.world.level.BlockGetter)
			return world.isStateAtPosition(pos, state -> state.isAir());
		return world.isStateAtPosition(pos, BlockState::isAir);
	}
	
	public static boolean isAirOrLeaves(LevelSimulatedReader world, BlockPos pos) {
			return world.isStateAtPosition(pos, state -> state.isSolidRender((net.minecraft.world.level.BlockGetter)world, pos));
	}
	
	@Override
	public boolean place(FeaturePlaceContext<SmolderingLogFeatureConfig> p_159471_) {
		BlockPos startPosition = p_159471_.origin();
		WorldGenLevel world = p_159471_.level();
		Random rand = p_159471_.random();
		BlockPos pos = startPosition;
		while (pos.getY() > 1 && isAirOrLeaves(world, pos)) pos = pos.below();
		if(!isSoil(world, pos, null)) {
			return false;
		}
		pos = pos.above();
		int height = rand.nextInt(2) + 2;
		int x = pos.getX();
		int z = pos.getZ();
		if(pos.getY() >= 1 && pos.getY() + height + 2 <= 256) {
			for (int j = pos.getY(); j <= pos.getY() + height; j++) {
				if (isAir(world, new BlockPos(x, j, z))) setBlock(world, new BlockPos(x, j, z), WildLandsBlocks.CHARRED_LOG.get().defaultBlockState());
			}
			if (isAir(world, new BlockPos(x, pos.getY() + height + 1, z))) setBlock(world, new BlockPos(x, pos.getY() + height + 1, z), WildLandsBlocks.SMOLDERING_LOG.get().defaultBlockState());
			return true;
		}
		return false;
	}

}
