package superlord.wildlands;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import superlord.wildlands.init.BlockInit;
import superlord.wildlands.init.ItemInit;

@Mod(WildLands.MOD_ID)
public class WildLands {
	
    public static final String MOD_ID = "wildlands";
    
    public WildLands() {
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    	BlockInit.REGISTER.register(bus);
    	ItemInit.REGISTER.register(bus);
    }
    
    public final static ItemGroup BLOCK_GROUP = new ItemGroup("wildlands_block_item_group") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockInit.MUD.get().asItem());
        }
    };

}
