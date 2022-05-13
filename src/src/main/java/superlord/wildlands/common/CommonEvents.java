package superlord.wildlands.common;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
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
	
	public static void registerCompostable(float chance, ItemLike item) {
		ComposterBlock.COMPOSTABLES.put(item.asItem(), chance);
	}
	
	public static void setup() {
//		registerFlammable(WildLandsBlocks.CYPRESS_FENCE.get(), 5, 20);
//		registerFlammable(WildLandsBlocks.CYPRESS_PLANKS.get(), 5, 20);
//		registerFlammable(WildLandsBlocks.CYPRESS_SLAB.get(), 5, 20);
//		registerFlammable(WildLandsBlocks.CYPRESS_FENCE_GATE.get(), 5, 20);
//		registerFlammable(WildLandsBlocks.CYPRESS_STAIRS.get(), 5, 20);
//		registerFlammable(WildLandsBlocks.COCONUT_FENCE.get(), 5, 20);
//		registerFlammable(WildLandsBlocks.COCONUT_PLANKS.get(), 5, 20);
//		registerFlammable(WildLandsBlocks.COCONUT_SLAB.get(), 5, 20);
//		registerFlammable(WildLandsBlocks.COCONUT_FENCE_GATE.get(), 5, 20);
//		registerFlammable(WildLandsBlocks.COCONUT_STAIRS.get(), 5, 20);
//		registerFlammable(WildLandsBlocks.COCONUT_LOG.get(), 5, 5);
//		registerFlammable(WildLandsBlocks.CYPRESS_LOG.get(), 5, 5);
//		registerFlammable(WildLandsBlocks.STRIPPED_COCONUT_LOG.get(), 5, 5);
//		registerFlammable(WildLandsBlocks.STRIPPED_CYPRESS_LOG.get(), 5, 5);
//		registerFlammable(WildLandsBlocks.COCONUT_WOOD.get(), 5, 5);
//		registerFlammable(WildLandsBlocks.CYPRESS_WOOD.get(), 5, 5);
//		registerFlammable(WildLandsBlocks.STRIPPED_COCONUT_WOOD.get(), 5, 5);
//		registerFlammable(WildLandsBlocks.STRIPPED_CYPRESS_WOOD.get(), 5, 5);
//		registerFlammable(WildLandsBlocks.COCONUT_LEAVES.get(), 30, 60);
//		registerFlammable(WildLandsBlocks.CYPRESS_LEAVES.get(), 30, 60);
//		registerFlammable(WildLandsBlocks.PALMETTO.get(), 60, 100);
		registerCompostable(0.3F, WildLandsBlocks.COCONUT_LEAVES.get());
		registerCompostable(0.3F, WildLandsBlocks.CYPRESS_LEAVES.get());
		registerCompostable(0.3F, WildLandsBlocks.COCONUT_SAPLING.get());
		registerCompostable(0.3F, WildLandsBlocks.CYPRESS_SAPLING.get());
		registerCompostable(0.5F, WildLandsBlocks.PALMETTO.get());
	}
	
	static {
//		BLOCK_STRIPPING_MAP.put(WildLandsBlocks.COCONUT_LOG.get(), WildLandsBlocks.STRIPPED_COCONUT_LOG.get());
//		BLOCK_STRIPPING_MAP.put(WildLandsBlocks.COCONUT_WOOD.get(), WildLandsBlocks.STRIPPED_COCONUT_WOOD.get());
//		BLOCK_STRIPPING_MAP.put(WildLandsBlocks.CYPRESS_LOG.get(), WildLandsBlocks.STRIPPED_CYPRESS_LOG.get());
//		BLOCK_STRIPPING_MAP.put(WildLandsBlocks.CYPRESS_WOOD.get(), WildLandsBlocks.STRIPPED_CYPRESS_WOOD.get());
//		BLOCK_STRIPPING_MAP.put(WildLandsBlocks.CHARRED_LOG.get(), WildLandsBlocks.STRIPPED_CHARRED_LOG.get());
//		BLOCK_STRIPPING_MAP.put(WildLandsBlocks.CHARRED_WOOD.get(), WildLandsBlocks.STRIPPED_CHARRED_WOOD.get());
//		BLOCK_HOE_MAP.put(WildLandsBlocks.CHARRED_GRASS.get(), Blocks.FARMLAND);
	}	
	
	@SubscribeEvent
	public static void onBlockClicked(PlayerInteractEvent.RightClickBlock event) {
		if(event.getItemStack().getItem() instanceof AxeItem) {
			Level world = event.getWorld();
			BlockPos pos = event.getPos();
			BlockState state = world.getBlockState(pos);
			Block block = BLOCK_STRIPPING_MAP.get(state.getBlock());
			if(block != null) {
				Player entity = event.getPlayer();
				world.playSound(entity, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
				if(!world.isClientSide) {
					world.setBlock(pos, block.defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS)), 11);
					if(entity != null) {
						event.getItemStack().hurtAndBreak(1, entity, (p_220040_1_) -> {
							p_220040_1_.broadcastBreakEvent(event.getHand());
						});
					}
				}
			}
		}
		if(event.getItemStack().getItem() instanceof HoeItem) {
			Level world = event.getWorld();
			BlockPos pos = event.getPos();
			BlockState state = world.getBlockState(pos);
			Block block = BLOCK_HOE_MAP.get(state.getBlock());
			if(block != null) {
				Player entity = event.getPlayer();
				world.playSound(entity, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
				if(!world.isClientSide) {
					world.setBlock(pos, block.defaultBlockState(), 11);
					if(entity != null) {
						event.getItemStack().hurtAndBreak(1, entity, (p_220040_1_) -> {
							p_220040_1_.broadcastBreakEvent(event.getHand());
						});
					}
				}
			}
		}
	}

}
