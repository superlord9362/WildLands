package superlord.wildlands.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import superlord.wildlands.init.WLBlocks;
import superlord.wildlands.init.WLEffects;

public class JellyBlock extends Block {

	protected static final VoxelShape COLLISION_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);

	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return COLLISION_SHAPE;
	}

	public JellyBlock(Properties properties) {
		super(properties);
	}

	public void fallOn(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, float fallDistance) {
		if (entityIn.isSuppressingBounce()) {
			super.fallOn(worldIn, state, pos, entityIn, fallDistance);
		} else {
			entityIn.causeFallDamage(fallDistance, 0.0F, DamageSource.FALL);
		}
	}

	public void updateEntityAfterFallOn(BlockGetter worldIn, Entity entityIn) {
		if (entityIn.isSuppressingBounce()) {
			super.updateEntityAfterFallOn(worldIn, entityIn);
		} else {
			this.bounceEntity(entityIn);
		}
	}

	private void bounceEntity(Entity entity) {
		Vec3 vector3d = entity.getDeltaMovement();
		if (vector3d.y < 0.0D) {
			double d0 = entity instanceof LivingEntity ? 1.0D : 0.8D;
			entity.setDeltaMovement(vector3d.x, -vector3d.y * d0, vector3d.z);
		}
	}

	public void stepOn(Level worldIn, BlockPos pos, BlockState state, Entity entityIn) {
		double d0 = Math.abs(entityIn.getDeltaMovement().y);
		if (d0 < 0.1D && !entityIn.isSteppingCarefully()) {
			double d1 = 0.4D + d0 * 0.2D;
			entityIn.setDeltaMovement(entityIn.getDeltaMovement().multiply(d1, 1.0D, d1));
		}
		super.stepOn(worldIn, pos, state, entityIn);
	}

	@Override
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity) {
			((LivingEntity) entityIn).addEffect(new MobEffectInstance(WLEffects.STING.get(), 100));
		}
	}
	
	@Override
	public boolean isStickyBlock(BlockState state)
    {
        return state.getBlock() == Blocks.SLIME_BLOCK || state.getBlock() == Blocks.HONEY_BLOCK || state.getBlock() == WLBlocks.JELLY_BLOCK.get();
    }

	@Override
    public boolean canStickTo(BlockState state, BlockState other)
    {
        if (state.getBlock() == Blocks.HONEY_BLOCK && other.getBlock() == Blocks.SLIME_BLOCK || state.getBlock() == Blocks.HONEY_BLOCK && other.getBlock() == WLBlocks.JELLY_BLOCK.get()) return false;
        if (state.getBlock() == Blocks.SLIME_BLOCK && other.getBlock() == Blocks.HONEY_BLOCK || state.getBlock() == Blocks.SLIME_BLOCK && other.getBlock() == WLBlocks.JELLY_BLOCK.get()) return false;
        return state.isStickyBlock() || other.isStickyBlock();
    }

}
