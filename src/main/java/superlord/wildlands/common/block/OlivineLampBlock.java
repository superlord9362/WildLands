package superlord.wildlands.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class OlivineLampBlock extends Block {

	public static final IntegerProperty XP_0_10 = IntegerProperty.create("experience", 0, 10);
	int experience;

	public OlivineLampBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(XP_0_10, 0));
	}

	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand hamd, BlockHitResult result) {
		int playerExperience = player.totalExperience;
		this.experience = state.getValue(XP_0_10);
		if (player.isShiftKeyDown()) {
			if (this.experience == 1) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 0), 0);
				player.giveExperiencePoints(7);
			} else if (this.experience == 2) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 1), 0);
				player.giveExperiencePoints(9);
			} else if (this.experience == 3) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 2), 0);
				player.giveExperiencePoints(11);
			} else if (this.experience == 4) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 3), 0);
				player.giveExperiencePoints(13);
			} else if (this.experience == 5) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 4), 0);
				player.giveExperiencePoints(15);
			} else if (this.experience == 6) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 5), 0);
				player.giveExperiencePoints(17);
			} else if (this.experience == 7) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 6), 0);
				player.giveExperiencePoints(19);
			} else if (this.experience == 8) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 7), 0);
				player.giveExperiencePoints(21);
			} else if (this.experience == 9) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 8), 0);
				player.giveExperiencePoints(23);
			} else if (this.experience == 10) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 9), 0);
				player.giveExperiencePoints(25);
			} else {
				return InteractionResult.sidedSuccess(worldIn.isClientSide());
			}
		} else {
			if (playerExperience >= 7 && experience == 0) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 1), 0);
				player.giveExperiencePoints(-7);
			} else if (playerExperience >= 9 && experience == 1) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 2), 0);
				player.giveExperiencePoints(-9);
			} else if (playerExperience >= 11 && experience ==  2) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 3), 0);
				player.giveExperiencePoints(-11);
			} else if (playerExperience >= 13 && experience == 3) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 4), 0);
				player.giveExperiencePoints(-13);
			} else if (playerExperience >= 15 && experience == 4) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 5), 0);
				player.giveExperiencePoints(-15);
			} else if (playerExperience >= 17 && experience == 5) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 6), 0);
				player.giveExperiencePoints(-17);
			} else if (playerExperience >= 19 && experience == 6) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 7), 0);
				player.giveExperiencePoints(-19);
			} else if (playerExperience >= 21 && experience == 7) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 8), 0);
				player.giveExperiencePoints(-21);
			} else if (playerExperience >= 23 && experience == 8) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 9), 0);
				player.giveExperiencePoints(-23);
			} else if (playerExperience >= 25 && experience == 9) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 10), 0);
				player.giveExperiencePoints(-25);
			} else {
				return InteractionResult.sidedSuccess(worldIn.isClientSide());
			}
		}
		return InteractionResult.sidedSuccess(worldIn.isClientSide());
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(XP_0_10);
	}

	@Override
	public int getExpDrop(BlockState state, LevelReader reader, RandomSource random, BlockPos pos, int fortune, int silktouch) {
		this.experience = state.getValue(XP_0_10);
		if (this.experience == 1) {
			return 7;
		} else if (this.experience == 2) {
			return 16;
		} else if (this.experience == 3) {
			return 27;
		} else if (this.experience == 4) {
			return 40;
		} else if (this.experience == 5) {
			return 55;
		} else if (this.experience == 6) {
			return 72;
		} else if (this.experience == 7) {
			return 91;
		} else if (this.experience == 8) {
			return 112;
		} else if (this.experience == 9) {
			return 135;
		} else if (this.experience == 10) {
			return 160;
		} else return 0;
	}

}
