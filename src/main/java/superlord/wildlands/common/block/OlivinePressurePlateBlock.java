package superlord.wildlands.common.block;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BasePressurePlateBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;

public class OlivinePressurePlateBlock extends BasePressurePlateBlock {
	private final OlivinePressurePlateBlock.Sensitivity sensitivity;
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

	int i;

	public OlivinePressurePlateBlock(Sensitivity sensitivityIn, Properties propertiesIn) {
		super(propertiesIn);
		this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, Boolean.valueOf(false)));
		this.sensitivity = sensitivityIn;
	}

	protected BlockState setSignalForState(BlockState state, int strength) {
		return state.setValue(POWERED, Boolean.valueOf(strength > 0));
	}

	protected int getSignalForState(BlockState state) {
		return state.getValue(POWERED) ? 15 : 0;
	}

	protected void playOnSound(LevelAccessor worldIn, BlockPos pos) {
		worldIn.playSound((Player)null, pos, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundSource.BLOCKS, 0.3F, 0.6F);
	}

	protected void playOffSound(LevelAccessor worldIn, BlockPos pos) {
		worldIn.playSound((Player)null, pos, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundSource.BLOCKS, 0.3F, 0.5F);
	}

	protected int getSignalStrength(Level worldIn, BlockPos pos) {
		AABB axisalignedbb = TOUCH_AABB.move(pos);
		List<? extends Entity> list;
		switch(this.sensitivity) {
		case EVERYTHING:
			list = worldIn.getEntities((Entity)null, axisalignedbb);
			break;
		case MOBS:
			list = worldIn.getEntitiesOfClass(LivingEntity.class, axisalignedbb);
			break;
		default:
			return 0;
		}

		if (!list.isEmpty()) {
			for(Entity entity : list) {
				if (!entity.isIgnoringBlockTriggers()) {
					if (entity instanceof Player) {
						Player player = (Player) entity;
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
		return state.getValue(POWERED) ? i : 0;
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(POWERED);
	}

	public static enum Sensitivity {
		EVERYTHING,
		MOBS;
	}

}
