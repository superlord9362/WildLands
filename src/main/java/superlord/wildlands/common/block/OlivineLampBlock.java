package superlord.wildlands.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class OlivineLampBlock extends Block {

	public static final IntegerProperty XP_0_10 = IntegerProperty.create("experience", 0, 10);
	int experience;

	public OlivineLampBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(XP_0_10, 0));
	}

	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		int experience = player.experienceLevel;
		int i = state.get(XP_0_10);
		this.experience = i;
		if (experience > 0 && i <= 9) {
			player.addExperienceLevel(-1);
			i++;
			worldIn.setBlockState(pos, this.getDefaultState().with(XP_0_10, i));
		} else {
			return ActionResultType.func_233537_a_(worldIn.isRemote);
		}
		return ActionResultType.func_233537_a_(worldIn.isRemote);
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
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
	public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
		return this.getExperience();
	}

}
