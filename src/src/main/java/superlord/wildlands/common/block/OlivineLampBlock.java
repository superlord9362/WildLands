package superlord.wildlands.common.block;

import net.minecraft.core.BlockPos;
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
		int newPlayerExperience;
		int i = state.getValue(XP_0_10);
		this.experience = i;
		if (playerExperience >= 7 && i == 0) {
			worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 1), 0);
			newPlayerExperience = playerExperience - 7;
			player.totalExperience = newPlayerExperience;
		} else if (playerExperience >= 15 && i == 1) {
			worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 2), 0);
			newPlayerExperience = playerExperience - 15;
			player.totalExperience = newPlayerExperience;
		} else if (playerExperience >= 26 && i ==  2) {
			worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 3), 0);
			newPlayerExperience = playerExperience - 26;
			player.totalExperience = newPlayerExperience;
		} else if (playerExperience >= 39 && i == 3) {
			worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 4), 0);
			newPlayerExperience = playerExperience - 39;
			player.totalExperience = newPlayerExperience;
		} else if (playerExperience >= 56 && i == 4) {
			worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 5), 0);
			newPlayerExperience = playerExperience - 56;
			player.totalExperience = newPlayerExperience;
		} else if (playerExperience >= 75 && i == 5) {
			worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 6), 0);
			newPlayerExperience = playerExperience - 75;
			player.totalExperience = newPlayerExperience;
		} else if (playerExperience >= 96 && i == 6) {
			worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 7), 0);
			newPlayerExperience = playerExperience - 96;
			player.totalExperience = newPlayerExperience;
		} else if (playerExperience >= 119 && i == 7) {
			worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 8), 0);
			newPlayerExperience = playerExperience - 119;
			player.totalExperience = newPlayerExperience;
		} else if (playerExperience >= 144 && i == 8) {
			worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 9), 0);
			newPlayerExperience = playerExperience - 144;
			player.totalExperience = newPlayerExperience;
		} else if (playerExperience >= 171 && i == 9) {
			worldIn.setBlock(pos, this.defaultBlockState().setValue(XP_0_10, 10), 0);
			newPlayerExperience = playerExperience - 171;
			player.totalExperience = newPlayerExperience;
		} else {
			return InteractionResult.sidedSuccess(worldIn.isClientSide());
		}
		return InteractionResult.sidedSuccess(worldIn.isClientSide());
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(XP_0_10);
	}

	protected int getExperience() {
		if(this.experience == 1) {
			return 7;
		} else if (this.experience == 2) {
			return 15;
		} else if (this.experience == 3) {
			return 26;
		} else if (this.experience == 4) {
			return 39;
		} else if (this.experience == 5) {
			return 56;
		} else if (this.experience == 6) {
			return 75;
		} else if (this.experience == 7) {
			return 96;
		} else if (this.experience == 8) {
			return 119;
		} else if (this.experience == 9) {
			return 144;
		} else if (this.experience == 10) {
			return 171;
		} else {
			return 0;
		}
	}

	@Override
	public int getExpDrop(BlockState state, LevelReader reader, BlockPos pos, int fortune, int silktouch) {
		return this.getExperience();
	}

}
