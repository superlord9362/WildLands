package superlord.wildlands.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import superlord.wildlands.init.WildLandsItems;
import superlord.wildlands.init.WildLandsSounds;

public class AnchovyEntity extends AbstractGroupFishEntity {

	public AnchovyEntity(EntityType<? extends AbstractGroupFishEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected ItemStack getFishBucket() {
		return WildLandsItems.ANCHOVY_BUCKET.get().getDefaultInstance();
	}

	protected SoundEvent getAmbientSound() {
		return WildLandsSounds.ANCHOVY_IDLE;
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
	
	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 4.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D);
	}
	
	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(WildLandsItems.ANCHOVY_SPAWN_EGG.get());
	}

}
