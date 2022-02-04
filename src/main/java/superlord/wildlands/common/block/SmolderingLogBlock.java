package superlord.wildlands.common.block;

import java.util.Queue;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.init.WildLandsBlocks;

public class SmolderingLogBlock extends RotatedPillarBlock {
	protected final IParticleData particleData;

	public SmolderingLogBlock(Properties properties, IParticleData particleData) {
		super(properties);
		this.particleData = particleData;
	}

	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (!entityIn.isImmuneToFire() && entityIn instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entityIn)) {
			entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
		}

		super.onEntityWalk(worldIn, pos, entityIn);
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.7D;
		double d2 = (double)pos.getZ() + 0.5D;
		worldIn.addParticle(ParticleTypes.LARGE_SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		worldIn.addParticle(this.particleData, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		if (rand.nextInt(100) == 0) {
            worldIn.playSound((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
         }
	}

	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (!oldState.isIn(state.getBlock())) {
			this.tryAbsorb(worldIn, pos);
		}
	}

	@SuppressWarnings("deprecation")
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		this.tryAbsorb(worldIn, pos);
		super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
	}

	protected void tryAbsorb(World worldIn, BlockPos pos) {
		if (this.absorb(worldIn, pos)) {
			worldIn.setBlockState(pos, WildLandsBlocks.CHARRED_LOG.getDefaultState(), 2);
			AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(worldIn, pos.getX(), pos.getY(), pos.getZ());
			areaeffectcloudentity.setRadius(1.5F);
			areaeffectcloudentity.setRadiusOnUse(-0.5F);
			areaeffectcloudentity.setWaitTime(10);
			areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 4);
			areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float)areaeffectcloudentity.getDuration());
			areaeffectcloudentity.setParticleData(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE);
			worldIn.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.3F, 0.5F);
			worldIn.addEntity(areaeffectcloudentity);
		}
	}

	private boolean absorb(World worldIn, BlockPos pos) {
		Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
		queue.add(new Tuple<>(pos, 0));
		int i = 0;

		while(!queue.isEmpty()) {
			Tuple<BlockPos, Integer> tuple = queue.poll();
			BlockPos blockpos = tuple.getA();

			for(Direction direction : Direction.values()) {
				BlockPos blockpos1 = blockpos.offset(direction);
				FluidState fluidstate = worldIn.getFluidState(blockpos1);
				if (fluidstate.isTagged(FluidTags.WATER)) {
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
