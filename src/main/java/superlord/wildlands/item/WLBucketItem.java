package superlord.wildlands.item;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.DistExecutor;
import superlord.wildlands.WildLands;
import superlord.wildlands.entity.AlligatorEntity;

public class WLBucketItem extends BucketItem {
	
	private final boolean hasTooltip;
	private final Supplier<? extends Fluid> fluid;
	
	public WLBucketItem(Supplier<? extends EntityType<?>> entityType, Supplier<? extends Fluid> fluid, Properties builder) {
		this(entityType, fluid, builder, true);
	}
	
	public WLBucketItem(Supplier<? extends EntityType<?>> entityType, Supplier<? extends Fluid> fluid, Properties builder, boolean hasToolTip) {
		super(fluid, builder);
		this.fluid = fluid;
		this.hasTooltip = hasToolTip;
		this.entityTypeSupplier = entityType;
		DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> WildLands.CALLBACKS.add(() -> ItemModelsProperties.registerProperty(this, new ResourceLocation(WildLands.MOD_ID, "variant"), (stack, world, player) -> stack.hasTag() ? stack.getTag().getInt("Variant") : 0)));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		BlockRayTraceResult raytraceresult = rayTrace(world, player, RayTraceContext.FluidMode.NONE);
		ActionResult<ItemStack> ret = ForgeEventFactory.onBucketUse(player, world, itemstack, raytraceresult);
		if (ret != null) return ret;
		if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
			return ActionResult.resultPass(itemstack);
		} else if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
			return ActionResult.resultPass(itemstack);
		} else {
			BlockPos blockpos = raytraceresult.getPos();
			Direction direction = raytraceresult.getFace();
			BlockPos blockpos1 = blockpos.offset(direction);
			if (world.isBlockModifiable(player, blockpos) && player.canPlayerEdit(blockpos1, direction, itemstack)) {
				BlockState blockstate = world.getBlockState(blockpos);
				BlockPos blockpos2 = blockstate.getBlock() instanceof ILiquidContainer && ((ILiquidContainer) blockstate.getBlock()).canContainFluid(world, blockpos, blockstate, fluid.get()) ? blockpos : blockpos1;
				this.tryPlaceContainedLiquid(player, world, blockpos2, raytraceresult);
				if (world instanceof ServerWorld) this.placeEntity((ServerWorld)world, itemstack, blockpos2);
				if (player instanceof ServerPlayerEntity) {
					CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) player, blockpos2, itemstack);
				}
				player.addStat(Stats.ITEM_USED.get(this));
				return ActionResult.func_233538_a_(this.emptyBucket(itemstack, player), world.isRemote);
			} else {
				return ActionResult.resultFail(itemstack);
			}
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> toolTip, ITooltipFlag flag) {
		super.addInformation(stack, world, toolTip, flag);
		if (hasTooltip && stack.hasTag()) {
			toolTip.add(new TranslationTextComponent(getEntityType().getTranslationKey() + "." + stack.getTag().getInt("Variant")).mergeStyle(TextFormatting.GRAY).mergeStyle(TextFormatting.ITALIC));
		}
	}
	
	private void placeEntity(ServerWorld world, ItemStack stack, BlockPos pos) {
		Entity entity = this.entityTypeSupplier.get().spawn(world, stack, (PlayerEntity)null, pos, SpawnReason.BUCKET, true, false);
		if(entity != null) {
			if (entity instanceof AlligatorEntity) {
				((AlligatorEntity)entity).setGrowingAge(-24000);
			}
			if (entity instanceof AbstractFishEntity) {
				((AbstractFishEntity)entity).setFromBucket(true);
			}
		}
	}
	
	private final Supplier<? extends EntityType<?>> entityTypeSupplier;
	protected EntityType<?> getEntityType() {
		return entityTypeSupplier.get();
	}
	
	@Override
	protected ItemStack emptyBucket(ItemStack stack, PlayerEntity player) {
		return !player.abilities.isCreativeMode ? new ItemStack(Items.BUCKET) : stack;
	}

}
