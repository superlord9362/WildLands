package superlord.wildlands.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.PlantType;

public class DuckWeedBlock extends BushBlock {
	protected static final VoxelShape LILY_PAD_AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 1.5D, 15.0D);

	public DuckWeedBlock(Properties builder) {
		super(builder);
	}

	@Override
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
		if (!(entityIn instanceof Boat)) {
            ((LivingEntity)entityIn).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1, 3, false, false));
		}
	}

	@Override
	public PlantType getPlantType(BlockGetter world, BlockPos pos) {
		return PlantType.WATER;
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		FluidState fluidstate = worldIn.getFluidState(pos);
		FluidState fluidstate1 = worldIn.getFluidState(pos.above());
		return (fluidstate.getType() == Fluids.WATER || state.getMaterial() == Material.ICE) && fluidstate1.getType() == Fluids.EMPTY;
	}

	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return LILY_PAD_AABB;
	}
}
