package superlord.wildlands.common.item;

import java.util.Random;

import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import superlord.wildlands.common.entity.item.Coconut;

public class CoconutItem extends StandingAndWallBlockItem {

	public CoconutItem(Block floorBlockIn, Block wallBlockIn, Item.Properties builder, Direction attachmentDirection) {
		super(floorBlockIn, wallBlockIn, builder, attachmentDirection);
	}
	
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		Random random = new Random();
		BlockHitResult raytraceresult = rayTrace(world, player, ClipContext.Block.OUTLINE);
		if (raytraceresult.getType() == BlockHitResult.Type.MISS || raytraceresult.getType() == BlockHitResult.Type.ENTITY) {
			world.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
			if (!world.isClientSide) {
				Coconut coconut = new Coconut(world, player);
				coconut.setItem(stack);
				coconut.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
				world.addFreshEntity(coconut);
			}
			player.awardStat(Stats.ITEM_USED.get(this));
			if (!player.getAbilities().instabuild) {
				stack.shrink(1);
			}
		}
		return InteractionResultHolder.sidedSuccess(stack, world.isClientSide());
	}

	protected static BlockHitResult rayTrace(Level worldIn, Player player, ClipContext.Block blockMode) {
		float f = player.getXRot();
		float f1 = player.getYRot();
		Vec3 vector3d = player.getEyePosition(1.0F);
		float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
		float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
		float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
		float f5 = Mth.sin(-f * ((float)Math.PI / 180F));
		float f6 = f3 * f4;
		float f7 = f2 * f4;
		double d0 = player.getAttribute(net.minecraftforge.common.ForgeMod.BLOCK_REACH.get()).getValue();;
		Vec3 vector3d1 = vector3d.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
		return worldIn.clip(new ClipContext(vector3d, vector3d1, blockMode, ClipContext.Fluid.ANY, player));
	}

	public Coconut createSoulBullet(Level world, ItemStack stack, LivingEntity shooter) {
		Coconut coconut = new Coconut(world, shooter);
		return coconut;
	}

}
