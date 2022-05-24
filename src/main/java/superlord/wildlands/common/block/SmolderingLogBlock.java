package superlord.wildlands.common.block;

import java.util.Queue;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Tuple;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.init.WLBlocks;

public class SmolderingLogBlock extends RotatedPillarBlock {
	protected final ParticleOptions particleData;

	public SmolderingLogBlock(Properties properties, ParticleOptions particleData) {
		super(properties);
		this.particleData = particleData;
	}

	public void stepOn(Level worldIn, BlockPos pos, BlockState state, Entity entityIn) {
		if (!entityIn.fireImmune() && entityIn instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entityIn)) {
			entityIn.hurt(DamageSource.HOT_FLOOR, 1.0F);
		}

		super.stepOn(worldIn, pos, state, entityIn);
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.7D;
		double d2 = (double)pos.getZ() + 0.5D;
		worldIn.addParticle(ParticleTypes.LARGE_SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		worldIn.addParticle(this.particleData, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		if (rand.nextInt(100) == 0) {
            worldIn.playLocalSound((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), SoundEvents.FIRE_AMBIENT, SoundSource.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
         }
	}

	public void onBlockAdded(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (!oldState.is(state.getBlock())) {
			this.tryAbsorb(worldIn, pos);
		}
	}

	@SuppressWarnings("deprecation")
	public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		this.tryAbsorb(worldIn, pos);
		super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
	}

	protected void tryAbsorb(Level worldIn, BlockPos pos) {
		if (this.absorb(worldIn, pos)) {
			worldIn.setBlock(pos, WLBlocks.CHARRED_LOG.get().defaultBlockState(), 2);
			AreaEffectCloud areaeffectcloudentity = new AreaEffectCloud(worldIn, pos.getX(), pos.getY(), pos.getZ());
			areaeffectcloudentity.setRadius(1.5F);
			areaeffectcloudentity.setRadiusOnUse(-0.5F);
			areaeffectcloudentity.setWaitTime(10);
			areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 4);
			areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float)areaeffectcloudentity.getDuration());
			areaeffectcloudentity.setParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE);
			worldIn.playSound(null, pos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 0.3F, 0.5F);
			worldIn.addFreshEntity(areaeffectcloudentity);
		}
	}

	private boolean absorb(Level worldIn, BlockPos pos) {
		Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
		queue.add(new Tuple<>(pos, 0));
		int i = 0;

		while(!queue.isEmpty()) {
			Tuple<BlockPos, Integer> tuple = queue.poll();
			BlockPos blockpos = tuple.getA();

			for(Direction direction : Direction.values()) {
				BlockPos blockpos1 = blockpos.relative(direction);
				FluidState fluidstate = worldIn.getFluidState(blockpos1);
				if (fluidstate.is(FluidTags.WATER)) {
					i++;
				}
			}

			if (i > 64) {
				break;
			}
		}

		return i > 0;
	}

}
