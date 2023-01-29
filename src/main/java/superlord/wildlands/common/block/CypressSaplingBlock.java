package superlord.wildlands.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import superlord.wildlands.init.WLBlocks;

public class CypressSaplingBlock extends BushBlock implements BonemealableBlock {

	public CypressSaplingBlock(Properties p_49795_) {
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

	@SuppressWarnings("removal")
	public void advanceTree(ServerLevel world, BlockPos pos, BlockState state, RandomSource random) {
		if (state.getValue(STAGE) == 0) {
			world.setBlock(pos, state.cycle(STAGE), 4);
		} else {
			if (!net.minecraftforge.event.ForgeEventFactory.saplingGrowTree(world, random, pos)) return;
			int height1 = random.nextInt(3) + 2;
			int height2 = random.nextInt(3) + 2;
			int height3 = random.nextInt(3) + 2;
			int height4 = random.nextInt(3) + 2;
			int number = random.nextInt(4);
			int tall = random.nextInt(4) + 3;
			if (number == 0) {
				int heightNumber = height1 + tall;
				for (int y = 0; y <= height1; y++) {
					BlockPos blockPos = pos.above(y);
					world.setBlock(blockPos, WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				}
				for (int y = 0; y <= height2; y++) {
					BlockPos blockPos = pos.above(y);
					world.setBlock(blockPos.east(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				}
				for (int y = 0; y <= height3; y++) {
					BlockPos blockPos = pos.above(y);
					world.setBlock(blockPos.east().north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				}
				for (int y = 0; y <= height4; y++) {
					BlockPos blockPos = pos.above(y);
					world.setBlock(blockPos.north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				}
				for (int y = 0; y <= heightNumber; y++) {
					BlockPos blockPos = pos.above(y);
					BlockPos highPos = pos.above(heightNumber);
					world.setBlock(blockPos, WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.east(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.east(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.east(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.west(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.west(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.west(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.north(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.north(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.south(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					//leaves
					world.setBlock(highPos.north(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(4).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(4).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(3).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(3).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(3).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(3).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(4).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(4).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(3).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(3).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(3).above(2).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(3).above(2).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(4).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(3).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(3).above(2).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(4).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(3).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(3).above(2).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.south(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.south(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(4).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(4).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(3).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(3).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(3).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(3).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(2).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(2).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(2).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(2).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west().above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west().above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east().above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east().above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east().north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east().south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east().south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east().north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west().north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west().north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west().south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west().south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				}
			}
			if (number == 1) {
				for (int y = 0; y <= height2; y++) {
					BlockPos blockPos = pos.above(y);
					world.setBlock(blockPos, WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				}
				for (int y = 0; y <= height3; y++) {
					BlockPos blockPos = pos.above(y);
					world.setBlock(blockPos.east(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				}
				for (int y = 0; y <= height4; y++) {
					BlockPos blockPos = pos.above(y);
					world.setBlock(blockPos.east().north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				}
				for (int y = 0; y <= height1; y++) {
					BlockPos blockPos = pos.above(y);
					world.setBlock(blockPos.north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				}
				int heightNumber = height2 + tall;
				for (int y = 0; y <= heightNumber; y++) {
					BlockPos blockPos = pos.above(y).east();
					BlockPos highPos = pos.above(heightNumber).east();
					world.setBlock(blockPos, WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.east(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.east(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.east(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.west(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.west(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.west(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.north(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.north(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.south(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.south(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.south(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					//leaves
					world.setBlock(highPos.north(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(4).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(4).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(3).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(3).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(3).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(3).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(4).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(4).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(3).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(3).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(3).above(2).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(3).above(2).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(4).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(3).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(3).above(2).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(4).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(3).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(3).above(2).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.south(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.south(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(4).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(4).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(3).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(3).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(3).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(3).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(2).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(2).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(2).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(2).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west().above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west().above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east().above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east().above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east().north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east().south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east().south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east().north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west().north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west().north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west().south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west().south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				}
			}
			if (number == 2) {
				for (int y = 0; y <= height3; y++) {
					BlockPos blockPos = pos.above(y);
					world.setBlock(blockPos, WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				}
				for (int y = 0; y <= height4; y++) {
					BlockPos blockPos = pos.above(y);
					world.setBlock(blockPos.east(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				}
				for (int y = 0; y <= height1; y++) {
					BlockPos blockPos = pos.above(y);
					world.setBlock(blockPos.east().north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				}
				for (int y = 0; y <= height2; y++) {
					BlockPos blockPos = pos.above(y);
					world.setBlock(blockPos.north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				}
				int heightNumber = height3 + tall;
				for (int y = 0; y <= heightNumber; y++) {
					BlockPos blockPos = pos.above(y).east().north();
					BlockPos highPos = pos.above(heightNumber).east().north();
					world.setBlock(blockPos, WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.east(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.east(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.east(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.west(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.west(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.west(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.north(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.north(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.south(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.south(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.south(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					//leaves
					world.setBlock(highPos.north(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(4).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(4).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(3).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(3).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(3).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(3).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(4).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(4).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(3).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(3).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(3).above(2).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(3).above(2).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(4).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(3).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(3).above(2).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(4).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(3).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(3).above(2).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.south(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.south(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(4).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(4).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(3).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(3).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(3).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(3).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(2).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(2).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(2).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(2).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west().above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west().above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east().above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east().above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east().north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east().south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east().south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east().north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west().north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west().north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west().south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west().south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				}
			}
			if (number == 3) {
				for (int y = 0; y <= height4; y++) {
					BlockPos blockPos = pos.above(y);
					world.setBlock(blockPos, WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				}
				for (int y = 0; y <= height1; y++) {
					BlockPos blockPos = pos.above(y);
					world.setBlock(blockPos.east(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				}
				for (int y = 0; y <= height2; y++) {
					BlockPos blockPos = pos.above(y);
					world.setBlock(blockPos.east().north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				}
				for (int y = 0; y <= height3; y++) {
					BlockPos blockPos = pos.above(y);
					world.setBlock(blockPos.north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				}
				int heightNumber = height4 + tall;
				for (int y = 0; y <= heightNumber; y++) {
					BlockPos blockPos = pos.above(y).north();
					BlockPos highPos = pos.above(heightNumber).north();
					world.setBlock(blockPos, WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.east(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.east(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.east(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.west(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.west(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.west(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.north(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.north(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.south(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.south(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.south(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					//leaves
					world.setBlock(highPos.north(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(4).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(4).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(3).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(3).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(3).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(3).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(4).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(4).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(3).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(3).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(3).above(2).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(3).above(2).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(4).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(3).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(3).above(2).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(4).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(3).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(3).above(2).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.south(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
					world.setBlock(highPos.south(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(4).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(4).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(3).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(3).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(3).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(3).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.south(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.north(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(2).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east(2).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(2).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west(2).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west().above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.west().above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east().above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.east().above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).north().west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(3).south().west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east().north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east().south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east().south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).east().north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west().north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west().north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west().south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west().south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
					world.setBlock(highPos.above(4).west(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				}
			}
		}
	}

	public boolean isValidBonemealTarget(BlockGetter p_55991_, BlockPos p_55992_, BlockState p_55993_, boolean p_55994_) {
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

}
