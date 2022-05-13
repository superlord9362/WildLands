package superlord.wildlands.common.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import superlord.wildlands.init.WildLandsItems;
import superlord.wildlands.init.WildLandsSounds;

public class AnchovyEntity extends AbstractSchoolingFish {

	public AnchovyEntity(EntityType<? extends AbstractSchoolingFish> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	public ItemStack getBucketItemStack() {
		return new ItemStack(WildLandsItems.ANCHOVY_BUCKET.get());
	}

	protected SoundEvent getAmbientSound() {
		return null;
		//return WildLandsSounds.ANCHOVY_IDLE;
	}

	protected SoundEvent getDeathSound() {
		return WildLandsSounds.ANCHOVY_DEATH;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return WildLandsSounds.ANCHOVY_HURT;
	}

	protected SoundEvent getFlopSound() {
		return WildLandsSounds.ANCHOVY_FLOP;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, 0.3D);
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(WildLandsItems.ANCHOVY_SPAWN_EGG.get());
	}

}
