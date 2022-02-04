package superlord.wildlands.common.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import superlord.wildlands.common.entity.JellyBallEntity;

public class JellyItem extends Item {

	public JellyItem(Properties properties) {
		super(properties);
	}

	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);
		world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		if (!world.isRemote) {
			JellyBallEntity jellyball = new JellyBallEntity(world, player);
			jellyball.getItem();
			jellyball.func_234612_a_(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
			world.addEntity(jellyball);
		}
		player.addStat(Stats.ITEM_USED.get(this));
		if (!player.abilities.isCreativeMode) {
			stack.shrink(1);
		}
		return ActionResult.func_233538_a_(stack, world.isRemote());
	}
	
	public JellyBallEntity createJellyBall(World world, ItemStack stack, LivingEntity shooter) {
		JellyBallEntity jellyball = new JellyBallEntity(world, shooter);
		return jellyball;
	}

}
