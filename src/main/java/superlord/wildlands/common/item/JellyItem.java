package superlord.wildlands.common.item;

import java.util.Random;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import superlord.wildlands.common.entity.JellyBallEntity;

public class JellyItem extends Item {

	public JellyItem(Properties properties) {
		super(properties);
	}

	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		Random random = new Random();
		world.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		if (!world.isClientSide) {
			JellyBallEntity jellyball = new JellyBallEntity(world, player);
			jellyball.getItem();
			jellyball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
			world.addFreshEntity(jellyball);
		}
		player.awardStat(Stats.ITEM_USED.get(this));
		if (!player.getAbilities().instabuild) {
			stack.shrink(1);
		}
		return InteractionResultHolder.sidedSuccess(stack, world.isClientSide());
	}

	public JellyBallEntity createJellyBall(Level world, ItemStack stack, LivingEntity shooter) {
		JellyBallEntity jellyball = new JellyBallEntity(world, shooter);
		return jellyball;
	}

}
