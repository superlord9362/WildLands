package superlord.wildlands.common;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import superlord.wildlands.WildLands;
import superlord.wildlands.init.WildLandsBlocks;

@Mod.EventBusSubscriber(modid = WildLands.MOD_ID, bus = Bus.FORGE)
public class CommonEvents {
	
	public static Map<Block, Block> BLOCK_STRIPPING_MAP = new HashMap<>();
	public static Map<Block, Block> BLOCK_HOE_MAP = new HashMap<>();
	
	public static void registerFlammable(Block block, int encouragement, int flammability) {
		FireBlock fire = (FireBlock) Blocks.FIRE;
		fire.setFireInfo(block, encouragement, flammability);
	}
	
	public static void registerCompostable(float chance, IItemProvider item) {
		ComposterBlock.CHANCES.put(item.asItem(), chance);
	}
	
	public static void setup() {
		registerFlammable(WildLandsBlocks.CYPRESS_FENCE.get(), 5, 20);
		registerFlammable(WildLandsBlocks.CYPRESS_PLANKS.get(), 5, 20);
		registerFlammable(WildLandsBlocks.CYPRESS_SLAB.get(), 5, 20);
		registerFlammable(WildLandsBlocks.CYPRESS_FENCE_GATE.get(), 5, 20);
		registerFlammable(WildLandsBlocks.CYPRESS_STAIRS.get(), 5, 20);
		registerFlammable(WildLandsBlocks.COCONUT_FENCE.get(), 5, 20);
		registerFlammable(WildLandsBlocks.COCONUT_PLANKS.get(), 5, 20);
		registerFlammable(WildLandsBlocks.COCONUT_SLAB.get(), 5, 20);
		registerFlammable(WildLandsBlocks.COCONUT_FENCE_GATE.get(), 5, 20);
		registerFlammable(WildLandsBlocks.COCONUT_STAIRS.get(), 5, 20);
		registerFlammable(WildLandsBlocks.COCONUT_LOG, 5, 5);
		registerFlammable(WildLandsBlocks.CYPRESS_LOG, 5, 5);
		registerFlammable(WildLandsBlocks.STRIPPED_COCONUT_LOG, 5, 5);
		registerFlammable(WildLandsBlocks.STRIPPED_CYPRESS_LOG, 5, 5);
		registerFlammable(WildLandsBlocks.COCONUT_WOOD, 5, 5);
		registerFlammable(WildLandsBlocks.CYPRESS_WOOD, 5, 5);
		registerFlammable(WildLandsBlocks.STRIPPED_COCONUT_WOOD, 5, 5);
		registerFlammable(WildLandsBlocks.STRIPPED_CYPRESS_WOOD, 5, 5);
		registerFlammable(WildLandsBlocks.COCONUT_LEAVES.get(), 30, 60);
		registerFlammable(WildLandsBlocks.CYPRESS_LEAVES.get(), 30, 60);
		registerFlammable(WildLandsBlocks.PALMETTO.get(), 60, 100);
		registerCompostable(0.3F, WildLandsBlocks.COCONUT_LEAVES.get());
		registerCompostable(0.3F, WildLandsBlocks.CYPRESS_LEAVES.get());
		registerCompostable(0.3F, WildLandsBlocks.COCONUT_SAPLING.get());
		registerCompostable(0.3F, WildLandsBlocks.CYPRESS_SAPLING.get());
		registerCompostable(0.5F, WildLandsBlocks.PALMETTO.get());
	}
	
	static {
		BLOCK_STRIPPING_MAP.put(WildLandsBlocks.COCONUT_LOG, WildLandsBlocks.STRIPPED_COCONUT_LOG);
		BLOCK_STRIPPING_MAP.put(WildLandsBlocks.COCONUT_WOOD, WildLandsBlocks.STRIPPED_COCONUT_WOOD);
		BLOCK_STRIPPING_MAP.put(WildLandsBlocks.CYPRESS_LOG, WildLandsBlocks.STRIPPED_CYPRESS_LOG);
		BLOCK_STRIPPING_MAP.put(WildLandsBlocks.CYPRESS_WOOD, WildLandsBlocks.STRIPPED_CYPRESS_WOOD);
		BLOCK_STRIPPING_MAP.put(WildLandsBlocks.CHARRED_LOG, WildLandsBlocks.STRIPPED_CHARRED_LOG);
		BLOCK_STRIPPING_MAP.put(WildLandsBlocks.CHARRED_WOOD, WildLandsBlocks.STRIPPED_CHARRED_WOOD);
		BLOCK_HOE_MAP.put(WildLandsBlocks.CHARRED_GRASS, Blocks.FARMLAND);
	}
	
	@SubscribeEvent
	public static void onBlockClicked(PlayerInteractEvent.RightClickBlock event) {
		if(event.getItemStack().getItem() instanceof AxeItem) {
			World world = event.getWorld();
			BlockPos pos = event.getPos();
			BlockState state = world.getBlockState(pos);
			Block block = BLOCK_STRIPPING_MAP.get(state.getBlock());
			if(block != null) {
				PlayerEntity entity = event.getPlayer();
				world.playSound(entity, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
				if(!world.isRemote) {
					world.setBlockState(pos, block.getDefaultState().with(RotatedPillarBlock.AXIS, state.get(RotatedPillarBlock.AXIS)), 11);
					if(entity != null) {
						event.getItemStack().damageItem(1, entity, (p_220040_1_) -> {
							p_220040_1_.sendBreakAnimation(event.getHand());
						});
					}
				}
			}
		}
		if(event.getItemStack().getItem() instanceof HoeItem) {
			World world = event.getWorld();
			BlockPos pos = event.getPos();
			BlockState state = world.getBlockState(pos);
			Block block = BLOCK_HOE_MAP.get(state.getBlock());
			if(block != null) {
				PlayerEntity entity = event.getPlayer();
				world.playSound(entity, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
				if(!world.isRemote) {
					world.setBlockState(pos, block.getDefaultState(), 11);
					if(entity != null) {
						event.getItemStack().damageItem(1, entity, (p_220040_1_) -> {
							p_220040_1_.sendBreakAnimation(event.getHand());
						});
					}
				}
			}
		}
	}

}
