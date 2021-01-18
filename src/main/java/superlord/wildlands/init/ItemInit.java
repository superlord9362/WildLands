package superlord.wildlands.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import superlord.wildlands.WildLands;

public class ItemInit {
	
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, WildLands.MOD_ID);

    public static final RegistryObject<BlockItem> MUD = REGISTER.register("mud", () -> new BlockItem(BlockInit.MUD.get(), new Item.Properties().group(WildLands.BLOCK_GROUP)));
    public static final RegistryObject<BlockItem> DRIED_MUD = REGISTER.register("dried_mud", () -> new BlockItem(BlockInit.DRIED_MUD.get(), new Item.Properties().group(WildLands.BLOCK_GROUP)));
    public static final RegistryObject<BlockItem> MUD_BRICKS = REGISTER.register("dried_mud_bricks", () -> new BlockItem(BlockInit.MUD_BRICKS.get(), new Item.Properties().group(WildLands.BLOCK_GROUP)));
    public static final RegistryObject<BlockItem> MUD_SHINGLES = REGISTER.register("dried_mud_shingles", () -> new BlockItem(BlockInit.MUD_SHINGLES.get(), new Item.Properties().group(WildLands.BLOCK_GROUP)));
    public static final RegistryObject<BlockItem> CYPRESS_LOG = REGISTER.register("cypress_log", () -> new BlockItem(BlockInit.CYPRESS_LOG.get(), new Item.Properties().group(WildLands.BLOCK_GROUP)));
    public static final RegistryObject<BlockItem> STRIPPED_CYPRESS_LOG = REGISTER.register("stripped_cypress_log", () -> new BlockItem(BlockInit.STRIPPED_CYPRESS_LOG.get(), new Item.Properties().group(WildLands.BLOCK_GROUP)));
    public static final RegistryObject<BlockItem> CYPRESS_PLANKS = REGISTER.register("cypress_planks", () -> new BlockItem(BlockInit.CYPRESS_PLANKS.get(), new Item.Properties().group(WildLands.BLOCK_GROUP)));
    public static final RegistryObject<BlockItem> CYPRESS_WOOD = REGISTER.register("cypress_wood", () -> new BlockItem(BlockInit.CYPRESS_WOOD.get(), new Item.Properties().group(WildLands.BLOCK_GROUP)));
    public static final RegistryObject<BlockItem> STRIPPED_CYPRESS_WOOD = REGISTER.register("stripped_cypress_wood", () -> new BlockItem(BlockInit.STRIPPED_CYPRESS_WOOD.get(), new Item.Properties().group(WildLands.BLOCK_GROUP)));
    public static final RegistryObject<BlockItem> CYPRESS_DOOR = REGISTER.register("cypress_door", () -> new BlockItem(BlockInit.CYPRESS_DOOR.get(), new Item.Properties().group(WildLands.BLOCK_GROUP)));
    public static final RegistryObject<BlockItem> CYPRESS_LEAVES = REGISTER.register("cypress_leaves", () -> new BlockItem(BlockInit.CYPRESS_LEAVES.get(), new Item.Properties().group(WildLands.BLOCK_GROUP)));
    public static final RegistryObject<BlockItem> CYPRESS_STAIRS = REGISTER.register("cypress_stairs", () -> new BlockItem(BlockInit.CYPRESS_STAIRS.get(), new Item.Properties().group(WildLands.BLOCK_GROUP)));
    public static final RegistryObject<BlockItem> CYPRESS_PRESSURE_PLATE = REGISTER.register("cypress_pressure_plate", () -> new BlockItem(BlockInit.CYPRESS_PRESSURE_PLATE.get(), new Item.Properties().group(WildLands.BLOCK_GROUP)));
    public static final RegistryObject<BlockItem> CYPRESS_FENCE = REGISTER.register("cypress_fence", () -> new BlockItem(BlockInit.CYPRESS_FENCE.get(), new Item.Properties().group(WildLands.BLOCK_GROUP)));
    public static final RegistryObject<BlockItem> CYPRESS_TRAPDOOR = REGISTER.register("cypress_trapdoor", () -> new BlockItem(BlockInit.CYPRESS_TRAPDOOR.get(), new Item.Properties().group(WildLands.BLOCK_GROUP)));
    public static final RegistryObject<BlockItem> CYPRESS_FENCE_GATE = REGISTER.register("cypress_fence_gate", () -> new BlockItem(BlockInit.CYPRESS_FENCE_GATE.get(), new Item.Properties().group(WildLands.BLOCK_GROUP)));
    public static final RegistryObject<BlockItem> CYPRESS_SLAB = REGISTER.register("cypress_slab", () -> new BlockItem(BlockInit.CYPRESS_SLAB.get(), new Item.Properties().group(WildLands.BLOCK_GROUP)));
    public static final RegistryObject<BlockItem> WET_CYPRESS_LOG = REGISTER.register("wet_cypress_log", () -> new BlockItem(BlockInit.WET_CYPRESS_LOG.get(), new Item.Properties().group(WildLands.BLOCK_GROUP)));

}
