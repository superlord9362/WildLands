package superlord.wildlands.common.item;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.common.entity.Alligator;

public class WLBucketItem extends BucketItem {

	private final boolean hasTooltip;



	public WLBucketItem(Supplier<? extends EntityType<?>> entityType, Supplier<? extends Fluid> fluid, Properties builder) {
		this(entityType, fluid, builder, true);
	}

	public WLBucketItem(Supplier<? extends EntityType<?>> entityType, Supplier<? extends Fluid> fluid, Properties builder, boolean hasToolTip) {
		super(fluid, builder);
		this.hasTooltip = hasToolTip;
		this.entityTypeSupplier = entityType;
	}

	public void checkExtraContent(@Nullable Player p_151146_, Level p_151147_, ItemStack p_151148_, BlockPos p_151149_) {
		if (p_151147_ instanceof ServerLevel) {
			this.spawn((ServerLevel)p_151147_, p_151148_, p_151149_);
			p_151147_.gameEvent(p_151146_, GameEvent.ENTITY_PLACE, p_151149_);
		}

	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> toolTip, TooltipFlag flag) {
		super.appendHoverText(stack, world, toolTip, flag);
		if (hasTooltip && stack.hasTag()) {
			toolTip.add(new TranslatableComponent(getEntityType().getDescriptionId() + "." + stack.getTag().getInt("Variant")).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
		}
	}

	private void spawn(ServerLevel world, ItemStack stack, BlockPos pos) {
		Entity entity = this.entityTypeSupplier.get().spawn(world, stack, (Player)null, pos, MobSpawnType.BUCKET, true, false);
		if(entity != null) {
			if (entity instanceof Alligator) {
				((Alligator)entity).setAge(-24000);
			}
			if (entity instanceof AbstractFish) {
				((AbstractFish)entity).setFromBucket(true);
			}
		}
	}

	private final Supplier<? extends EntityType<?>> entityTypeSupplier;
	protected EntityType<?> getEntityType() {
		return entityTypeSupplier.get();
	}


}
