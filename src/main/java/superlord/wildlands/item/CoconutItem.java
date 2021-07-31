package superlord.wildlands.item;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WallOrFloorItem;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import superlord.wildlands.entity.CoconutEntity;

public class CoconutItem extends WallOrFloorItem {

	public CoconutItem(Block floorBlockIn, Block wallBlockIn, Item.Properties builder) {
		super(floorBlockIn, wallBlockIn, builder);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);
		RayTraceResult raytraceresult = rayTrace(world, player, RayTraceContext.BlockMode.OUTLINE);
		if (raytraceresult.getType() == RayTraceResult.Type.MISS || raytraceresult.getType() == RayTraceResult.Type.ENTITY) {
			world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote) {
				CoconutEntity coconut = new CoconutEntity(world, player);
				coconut.getItem();
				coconut.func_234612_a_(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
				world.addEntity(coconut);
			}
			player.addStat(Stats.ITEM_USED.get(this));
			if (!player.abilities.isCreativeMode) {
				stack.shrink(1);
			}
		}
		return ActionResult.func_233538_a_(stack, world.isRemote());
	}

	protected static BlockRayTraceResult rayTrace(World worldIn, PlayerEntity player, RayTraceContext.BlockMode blockMode) {
		float f = player.rotationPitch;
		float f1 = player.rotationYaw;
		Vector3d vector3d = player.getEyePosition(1.0F);
		float f2 = MathHelper.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
		float f3 = MathHelper.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
		float f4 = -MathHelper.cos(-f * ((float)Math.PI / 180F));
		float f5 = MathHelper.sin(-f * ((float)Math.PI / 180F));
		float f6 = f3 * f4;
		float f7 = f2 * f4;
		double d0 = player.getAttribute(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get()).getValue();;
		Vector3d vector3d1 = vector3d.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
		return worldIn.rayTraceBlocks(new RayTraceContext(vector3d, vector3d1, blockMode, RayTraceContext.FluidMode.ANY, player));
	}

	public CoconutEntity createSoulBullet(World world, ItemStack stack, LivingEntity shooter) {
		CoconutEntity coconut = new CoconutEntity(world, shooter);
		return coconut;
	}

}
