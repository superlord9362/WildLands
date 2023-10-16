package superlord.wildlands.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import superlord.wildlands.WildLands;

public class WLTabs {
	
	public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WildLands.MOD_ID);
	
	public static final RegistryObject<CreativeModeTab> BLOCK_GROUP = REGISTER.register("wildlands_block_item_group", () -> CreativeModeTab.builder().icon(() -> new ItemStack(WLBlocks.MUD.get()))
		.title(Component.translatable("itemGroup.wildlands_block_item_group"))
		.displayItems((pParameters, pOutput) -> {
			for (var block : WLItems.BLOCKS.getEntries()) {
				pOutput.accept(block.get());
			}
		}).build());
	
	public static final RegistryObject<CreativeModeTab> ITEM_GROUP = REGISTER.register("wildlands_item_item_group", () -> CreativeModeTab.builder().icon(() -> new ItemStack(WLItems.OLIVINE.get()))
			.title(Component.translatable("itemGroup.wildlands_item_item_group"))
			.displayItems((pParameters, pOutput) -> {
				for (var block : WLItems.REGISTER.getEntries()) {
					pOutput.accept(block.get());
				}
			}).build());
	
	public static final RegistryObject<CreativeModeTab> SPAWN_EGG_GROUP = REGISTER.register("wildlands_spawn_item_group", () -> CreativeModeTab.builder().icon(() -> new ItemStack(WLItems.CATFISH_SPAWN_EGG.get()))
			.title(Component.translatable("itemGroup.wildlands_spawn_item_group"))
			.displayItems((pParameters, pOutput) -> {
				for (var block : WLItems.SPAWN_EGGS.getEntries()) {
					pOutput.accept(block.get());
				}
			}).build());
	
}
