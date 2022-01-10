package superlord.wildlands.common.block;

import java.util.List;

import net.minecraft.block.AbstractPressurePlateBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class OlivinePressurePlate extends AbstractPressurePlateBlock {
	private final OlivinePressurePlate.Sensitivity sensitivity;
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	
	int i;

	public OlivinePressurePlate(Sensitivity sensitivityIn, Properties propertiesIn) {
		super(propertiesIn);
		this.setDefaultState(this.stateContainer.getBaseState().with(POWERED, Boolean.valueOf(false)));
		this.sensitivity = sensitivityIn;
	}

	protected BlockState setRedstoneStrength(BlockState state, int strength) {
		return state.with(POWERED, Boolean.valueOf(strength > 0));
	}

	protected void playClickOnSound(IWorld worldIn, BlockPos pos) {
		worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_STONE_PRESSURE_PLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
	}

	protected void playClickOffSound(IWorld worldIn, BlockPos pos) {
		worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.5F);
	}

	protected int computeRedstoneStrength(World worldIn, BlockPos pos) {
		AxisAlignedBB axisalignedbb = PRESSURE_AABB.offset(pos);
		List<? extends Entity> list;
		switch(this.sensitivity) {
		case EVERYTHING:
			list = worldIn.getEntitiesWithinAABBExcludingEntity((Entity)null, axisalignedbb);
			break;
		case MOBS:
			list = worldIn.getEntitiesWithinAABB(LivingEntity.class, axisalignedbb);
			break;
		default:
			return 0;
		}

		if (!list.isEmpty()) {
			for(Entity entity : list) {
				if (!entity.doesEntityNotTriggerPressurePlate()) {
					if (entity instanceof PlayerEntity) {
						PlayerEntity player = (PlayerEntity) entity;
						int i = player.experienceLevel;
						this.i = i;
						System.out.println(i);
						if (i >= 15) i = 15;
						return i;
					}
				}
			}
		}

		return 0;
	}
	

	protected int getRedstoneStrength(BlockState state) {
		return state.get(POWERED) ? i : 0;
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(POWERED);
	}

	public static enum Sensitivity {
		EVERYTHING,
		MOBS;
	}

}
