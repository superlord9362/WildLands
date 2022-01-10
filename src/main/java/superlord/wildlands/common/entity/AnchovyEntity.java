package superlord.wildlands.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import superlord.wildlands.init.WildLandsItems;

public class AnchovyEntity extends AbstractGroupFishEntity {

	public AnchovyEntity(EntityType<? extends AbstractGroupFishEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected ItemStack getFishBucket() {
		return WildLandsItems.ANCHOVY_BUCKET.get().getDefaultInstance();
	}

	@Override
	protected SoundEvent getFlopSound() {
		return SoundEvents.ENTITY_COD_FLOP;
	}
	
	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 4.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D);
	}

}
