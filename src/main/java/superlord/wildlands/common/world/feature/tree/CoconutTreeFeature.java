package superlord.wildlands.common.world.feature.tree;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import superlord.wildlands.common.block.WallCoconutBlock;
import superlord.wildlands.init.WLBlocks;

public class CoconutTreeFeature extends Feature<NoneFeatureConfiguration> {

	public CoconutTreeFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> placeContext) {
		return place(placeContext.level(), placeContext.chunkGenerator(), placeContext.random(), placeContext.origin(), placeContext.config());
	}

	public boolean place(WorldGenLevel world, ChunkGenerator generator, Random rand, BlockPos pos, NoneFeatureConfiguration config) {
		if (world.getBlockState(pos.below()).is(Blocks.SAND) && world.getBlockState(pos).is(Blocks.AIR)) {
			placeTree(world, rand, pos);
			return true;
		} else {
			return false;
		}
	}


	private void placeTree(LevelAccessor world, Random random, BlockPos pos) {
		int height1 = random.nextInt(3) + 3;
		int height2 = random.nextInt(3) + 3 + height1;
		int coconutHeight = random.nextInt(3) + 1 + height1;
		int coconutChance = random.nextInt(5);
		int coconutDirection = random.nextInt(4);
		int doubleCoconutHeight = random.nextInt(3) + 1 + height1;
		int doubleCoconutChance = random.nextInt(10);
		int doubleCoconutDirection = random.nextInt(4);
		int direction = random.nextInt(4);
		for (int y = 0; y <= height1; y++) {
			BlockPos blockPos = pos.above(y);
			world.setBlock(blockPos, WLBlocks.COCONUT_LOG.get().defaultBlockState(), 2);
		}
		if (direction == 0) {
			for (int y = 0; y <= height2; y++) {
				BlockPos blockPos = pos.above(y).east();
				//Leaves
				BlockPos leafPos = pos.above(height2 + 1).east();
				world.setBlock(leafPos, WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east().below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west().below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north().below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south().below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east().below().north(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west().below().north(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south().below().west(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south().below().east(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(2).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(2).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(2).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(2).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(3).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(3).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(3).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(3).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(3).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(3).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(3).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(3).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(4).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(4).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(4).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(4).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				//The rest
				if (coconutChance == 0) {
					if (coconutDirection == 0) {
						BlockPos coconutPos = pos.above(coconutHeight).east(2);
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST), 2);
					}
					if (coconutDirection == 1) {
						BlockPos coconutPos = pos.above(coconutHeight);
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST), 2);
					}
					if (coconutDirection == 2) {
						BlockPos coconutPos = pos.above(coconutHeight).north().east();
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.NORTH), 2);
					}

					if (coconutDirection == 3) {
						BlockPos coconutPos = pos.above(coconutHeight).south().east();
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH), 2);
					}
				}
				if (doubleCoconutChance == 0) {
					if (doubleCoconutDirection == 0) {
						BlockPos coconutPos = pos.above(doubleCoconutHeight).east(2);
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST), 2);
					}
					if (doubleCoconutDirection == 1) {
						BlockPos coconutPos = pos.above(doubleCoconutHeight);
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST), 2);
					}
					if (doubleCoconutDirection == 2) {
						BlockPos coconutPos = pos.above(doubleCoconutHeight).north().east();
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.NORTH), 2);
					}

					if (doubleCoconutDirection == 3) {
						BlockPos coconutPos = pos.above(doubleCoconutHeight).south().east();
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH), 2);
					}
				}
				world.setBlock(blockPos, WLBlocks.COCONUT_LOG.get().defaultBlockState(), 2);
				for (int y1 = 0; y1 < height1; y1++) {
					BlockPos blockPos1 = pos.above(y1).east();
					world.setBlock(blockPos1, Blocks.AIR.defaultBlockState(), 2);
				}
			}
		}
		if (direction == 1) {
			for (int y = 0; y <= height2; y++) {
				if (coconutChance == 0) {
					if (coconutDirection == 0) {
						BlockPos coconutPos = pos.above(coconutHeight).east().north();
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST), 2);
					}
					if (coconutDirection == 1) {
						BlockPos coconutPos = pos.above(coconutHeight).west().north();
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST), 2);
					}
					if (coconutDirection == 2) {
						BlockPos coconutPos = pos.above(coconutHeight).north(2);
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.NORTH), 2);
					}

					if (coconutDirection == 3) {
						BlockPos coconutPos = pos.above(coconutHeight);
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH), 2);
					}
				}
				if (doubleCoconutChance == 0) {
					if (doubleCoconutDirection == 0) {
						BlockPos coconutPos = pos.above(doubleCoconutHeight).east().north();
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST), 2);
					}
					if (doubleCoconutDirection == 1) {
						BlockPos coconutPos = pos.above(doubleCoconutHeight).west().north();
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST), 2);
					}
					if (doubleCoconutDirection == 2) {
						BlockPos coconutPos = pos.above(doubleCoconutHeight).north(2);
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.NORTH), 2);
					}

					if (doubleCoconutDirection == 3) {
						BlockPos coconutPos = pos.above(doubleCoconutHeight);
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH), 2);
					}
				}
				BlockPos blockPos = pos.above(y).north();
				//Leaves
				BlockPos leafPos = pos.above(height2 + 1).north();
				world.setBlock(leafPos, WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east().below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west().below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north().below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south().below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east().below().north(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west().below().north(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south().below().west(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south().below().east(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(2).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(2).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(2).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(2).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(3).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(3).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(3).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(3).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(3).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(3).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(3).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(3).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(4).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(4).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(4).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(4).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				//The rest
				world.setBlock(blockPos, WLBlocks.COCONUT_LOG.get().defaultBlockState(), 2);
				for (int y1 = 0; y1 < height1; y1++) {
					BlockPos blockPos1 = pos.above(y1).north();
					world.setBlock(blockPos1, Blocks.AIR.defaultBlockState(), 2);
				}
			}
		}
		if (direction == 2) {
			for (int y = 0; y <= height2; y++) {
				BlockPos blockPos = pos.above(y).west();
				//Leaves
				BlockPos leafPos = pos.above(height2 + 1).west();
				world.setBlock(leafPos, WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east().below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west().below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north().below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south().below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east().below().north(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west().below().north(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south().below().west(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south().below().east(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(2).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(2).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(2).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(2).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(3).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(3).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(3).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(3).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(3).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(3).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(3).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(3).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(4).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(4).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(4).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(4).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				//The rest
				if (coconutChance == 0) {
					if (coconutDirection == 0) {
						BlockPos coconutPos = pos.above(coconutHeight);
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST), 2);
					}
					if (coconutDirection == 1) {
						BlockPos coconutPos = pos.above(coconutHeight).west(2);
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST), 2);
					}
					if (coconutDirection == 2) {
						BlockPos coconutPos = pos.above(coconutHeight).north().west();
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.NORTH), 2);
					}

					if (coconutDirection == 3) {
						BlockPos coconutPos = pos.above(coconutHeight).south().west();
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH), 2);
					}
				}
				if (doubleCoconutChance == 0) {
					if (doubleCoconutDirection == 0) {
						BlockPos coconutPos = pos.above(doubleCoconutHeight);
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST), 2);
					}
					if (doubleCoconutDirection == 1) {
						BlockPos coconutPos = pos.above(doubleCoconutHeight).west(2);
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST), 2);
					}
					if (doubleCoconutDirection == 2) {
						BlockPos coconutPos = pos.above(doubleCoconutHeight).north().west();
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.NORTH), 2);
					}

					if (doubleCoconutDirection == 3) {
						BlockPos coconutPos = pos.above(doubleCoconutHeight).south().west();
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH), 2);
					}
				}
				world.setBlock(blockPos, WLBlocks.COCONUT_LOG.get().defaultBlockState(), 2);
				for (int y1 = 0; y1 < height1; y1++) {
					BlockPos blockPos1 = pos.above(y1).west();
					world.setBlock(blockPos1, Blocks.AIR.defaultBlockState(), 2);
				}
			}
		}
		if (direction == 3) {
			for (int y = 0; y <= height2; y++) {
				BlockPos blockPos = pos.above(y).south();
				//Leaves
				BlockPos leafPos = pos.above(height2 + 1).south();
				world.setBlock(leafPos, WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east().below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west().below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north().below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south().below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east().below().north(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west().below().north(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south().below().west(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south().below().east(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(2).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(2).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(2).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(2).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(3).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(3).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(3).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(3).below(), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(3).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(3).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(3).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(3).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.east(4).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.west(4).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.north(4).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(leafPos.south(4).below(2), WLBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				//The rest
				if (coconutChance == 0) {
					if (coconutDirection == 0) {
						BlockPos coconutPos = pos.above(coconutHeight).east().south();
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST), 2);
					}
					if (coconutDirection == 1) {
						BlockPos coconutPos = pos.above(coconutHeight).west().south();
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST), 2);
					}
					if (coconutDirection == 2) {
						BlockPos coconutPos = pos.above(coconutHeight);
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.NORTH), 2);
					}

					if (coconutDirection == 3) {
						BlockPos coconutPos = pos.above(coconutHeight).south(2);
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH), 2);
					}
				}
				if (doubleCoconutChance == 0) {
					if (doubleCoconutDirection == 0) {
						BlockPos coconutPos = pos.above(doubleCoconutHeight).east().south();
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST), 2);
					}
					if (doubleCoconutDirection == 1) {
						BlockPos coconutPos = pos.above(doubleCoconutHeight).west().south();
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST), 2);
					}
					if (doubleCoconutDirection == 2) {
						BlockPos coconutPos = pos.above(doubleCoconutHeight);
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.NORTH), 2);
					}

					if (doubleCoconutDirection == 3) {
						BlockPos coconutPos = pos.above(doubleCoconutHeight).south(2);
						world.setBlock(coconutPos, WLBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH), 2);
					}
				}
				world.setBlock(blockPos, WLBlocks.COCONUT_LOG.get().defaultBlockState(), 2);
				for (int y1 = 0; y1 < height1; y1++) {
					BlockPos blockPos1 = pos.above(y1).south();
					world.setBlock(blockPos1, Blocks.AIR.defaultBlockState(), 2);
				}
			}
		}
	}

}
