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

	@SuppressWarnings("deprecation")
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand hamd, BlockHitResult result) {
		int playerExperience = player.totalExperience;
		this.experience = state.getValue(XP_0_10);
		if (player.isShiftKeyDown()) {
			if (experience == 1) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 0), 0);
				player.giveExperiencePoints(7);
			} else if (experience == 2) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 1), 0);
				player.giveExperiencePoints(9);
			} else if (experience == 3) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 2), 0);
				player.giveExperiencePoints(11);
			} else if (experience == 4) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 3), 0);
				player.giveExperiencePoints(13);
			} else if (experience == 5) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 4), 0);
				player.giveExperiencePoints(15);
			} else if (experience == 6) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 5), 0);
				player.giveExperiencePoints(17);
			} else if (experience == 7) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 6), 0);
				player.giveExperiencePoints(19);
			} else if (experience == 8) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 7), 0);
				player.giveExperiencePoints(21);
			} else if (experience == 9) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 8), 0);
				player.giveExperiencePoints(23);
			} else if (experience == 10) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 9), 0);
				player.giveExperiencePoints(25);
			}
		} else {
			if (playerExperience >= 7 && experience == 0) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 1), 0);
				player.giveExperiencePoints(-7);
			} else if (this.experience == 1 && playerExperience >= 9) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 2), 0);
				player.giveExperiencePoints(-9);
			} else if (this.experience == 2 && playerExperience >= 11) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 3), 0);
				player.giveExperiencePoints(-11);
			} else if (this.experience == 3 && playerExperience >= 13) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 4), 0);
				player.giveExperiencePoints(-13);
			} else if (this.experience == 4 && playerExperience >= 15) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 5), 0);
				player.giveExperiencePoints(-15);
			} else if (this.experience == 5 && playerExperience >= 17) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 6), 0);
				player.giveExperiencePoints(-17);
			} else if (this.experience == 6 && playerExperience >= 19) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 7), 0);
				player.giveExperiencePoints(-19);
			} else if (this.experience == 7 && playerExperience >= 21) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 8), 0);
				player.giveExperiencePoints(-21);
			} else if (this.experience == 8 && playerExperience >= 23) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 9), 0);
				player.giveExperiencePoints(-23);
			} else if (this.experience == 9 && playerExperience >= 25) {
				worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 10), 0);
				player.giveExperiencePoints(-25);
			}
		}
		return super.use(state, worldIn, pos, player, hamd, result);
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
