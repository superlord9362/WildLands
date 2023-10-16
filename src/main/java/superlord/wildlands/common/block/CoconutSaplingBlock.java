package superlord.wildlands.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.eventbus.api.Event.Result;
import superlord.wildlands.init.WLBlocks;

public class CoconutSaplingBlock extends BushBlock implements BonemealableBlock {

	public CoconutSaplingBlock(Properties p_49795_) {
		super(p_49795_);
		this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, Integer.valueOf(0)));
	}

	public static final IntegerProperty STAGE = BlockStateProperties.STAGE;
	protected static final float AABB_OFFSET = 6.0F;
	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

	public VoxelShape getShape(BlockState p_56008_, BlockGetter p_56009_, BlockPos p_56010_, CollisionContext p_56011_) {
		return SHAPE;
	}

	@SuppressWarnings("deprecation")
	public void randomTick(BlockState state, ServerLevel p_56004_, BlockPos p_56005_, RandomSource p_56006_) {
		if (p_56004_.getMaxLocalRawBrightness(p_56005_.above()) >= 9 && p_56006_.nextInt(7) == 0) {
			if (!p_56004_.isAreaLoaded(p_56005_, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
			this.advanceTree(p_56004_, p_56005_, state, p_56006_);
		}
	}

	public boolean canSurvive(BlockState p_57175_, LevelReader p_57176_, BlockPos p_57177_) {
	      BlockState soil = p_57176_.getBlockState(p_57177_.below());
	      if (soil.canSustainPlant(p_57176_, p_57177_.below(), Direction.UP, this) || soil.is(BlockTags.SAND)) return true;
	      else if (!(p_57176_.getFluidState(p_57177_).isEmpty())) return false;
	      else return false;
	}

	public void advanceTree(ServerLevel world, BlockPos pos, BlockState state, RandomSource random) {
		if (state.getValue(STAGE) == 0) {
			world.setBlock(pos, state.cycle(STAGE), 4);
		} else {
			if (!saplingGrowTree(world, random, pos)) return;
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

	public boolean isValidBonemealTarget(LevelReader p_256559_, BlockPos p_50898_, BlockState p_50899_) {
		return true;
	}

	public boolean isBonemealSuccess(Level p_55996_, RandomSource p_55997_, BlockPos p_55998_, BlockState p_55999_) {
		return (double)p_55996_.random.nextFloat() < 0.45D;
	}

	public void performBonemeal(ServerLevel p_55986_, RandomSource p_55987_, BlockPos p_55988_, BlockState p_55989_) {
		this.advanceTree(p_55986_, p_55988_, p_55989_, p_55987_);
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_56001_) {
		p_56001_.add(STAGE);
	}
	
	public static boolean saplingGrowTree(LevelAccessor level, RandomSource randomSource, BlockPos pos)
    {
        return !net.minecraftforge.event.ForgeEventFactory.blockGrowFeature(level, randomSource, pos, null).getResult().equals(Result.DENY);
    }

}
