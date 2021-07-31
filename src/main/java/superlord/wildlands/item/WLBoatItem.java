package superlord.wildlands.item;

import java.util.List;
import java.util.function.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import superlord.wildlands.WildLands;
import superlord.wildlands.entity.WLBoatEntity;

public class WLBoatItem extends Item {
	
	private static final Predicate<Entity> RIDERS = EntityPredicates.NOT_SPECTATING.and(Entity::canBeCollidedWith);
	private final WLBoatEntity.WLType type;
	
	public WLBoatItem(WLBoatEntity.WLType type, Item.Properties properties) {
		super(properties);
		this.type = type;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		RayTraceResult raytraceresult = rayTrace(world, player, RayTraceContext.FluidMode.ANY);
		if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
			return ActionResult.resultPass(itemstack);
		} else {
			Vector3d vec3d = player.getLook(1.0F);
			List<Entity> list = world.getEntitiesInAABBexcluding(player, player.getBoundingBox().expand(vec3d.scale(5.0D)).grow(1.0D), RIDERS);
			if (!list.isEmpty()) {
				Vector3d vec3d1 = player.getEyePosition(1.0F);
				for (Entity entity : list) {
					AxisAlignedBB axisalignedbb = entity.getBoundingBox().grow(entity.getCollisionBorderSize());
					if (axisalignedbb.contains(vec3d1)) {
                        return ActionResult.resultPass(itemstack);
					}
				}
			}
			if (raytraceresult.getType() == RayTraceResult.Type.BLOCK) {
				WLBoatEntity wlBoatEntity = new WLBoatEntity(world, raytraceresult.getHitVec().x, raytraceresult.getHitVec().y, raytraceresult.getHitVec().z);
				WildLands.LOGGER.info("BOAT ENTITY: " + wlBoatEntity.getEntityString());
				wlBoatEntity.setWLBoatType(this.type);
				wlBoatEntity.rotationYaw = player.rotationYaw;
				if (!world.hasNoCollisions(wlBoatEntity, wlBoatEntity.getBoundingBox().grow(-0.1D))) {
					return ActionResult.resultFail(itemstack);
				} else {
					if (!world.isRemote) {
						world.addEntity(wlBoatEntity);
						if (!player.isCreative()) {
							itemstack.shrink(1);
						}
					}
					player.addStat(Stats.ITEM_USED.get(this));
					return ActionResult.resultSuccess(itemstack);
				}
 			} else {
 				return ActionResult.resultPass(itemstack);
 			}
		}
	}

}
