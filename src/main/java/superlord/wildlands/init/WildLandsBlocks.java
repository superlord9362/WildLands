package superlord.wildlands.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.block.BeardMossBlock;
import superlord.wildlands.common.block.BeardMossTopBlock;
import superlord.wildlands.common.block.CharredGrassBlock;
import superlord.wildlands.common.block.CharredTallGrassBlock;
import superlord.wildlands.common.block.CoconutBlock;
import superlord.wildlands.common.block.DoubleWaterPlantBlock;
import superlord.wildlands.common.block.DriedMudBlock;
import superlord.wildlands.common.block.DuckWeedBlock;
import superlord.wildlands.common.block.JellyBlock;
import superlord.wildlands.common.block.MudBlock;
import superlord.wildlands.common.block.OlivineLampBlock;
import superlord.wildlands.common.block.OlivinePressurePlate;
import superlord.wildlands.common.block.PalmettoBlock;
import superlord.wildlands.common.block.SmolderingLogBlock;
import superlord.wildlands.common.block.StarfishBlock;
import superlord.wildlands.common.block.UrchinBlock;
import superlord.wildlands.common.block.WLSapling;
import superlord.wildlands.common.block.WLStandingSignBlock;
import superlord.wildlands.common.block.WLWallSignBlock;
import superlord.wildlands.common.block.WallCoconutBlock;
import superlord.wildlands.common.util.WLTreeSpawners;

@Mod.EventBusSubscriber(modid = WildLands.MOD_ID, bus = Bus.MOD)
public class WildLandsBlocks {

	public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, WildLands.MOD_ID);

	public static final RegistryObject<Block> MUD = REGISTER.register("mud", () -> new MudBlock(AbstractBlock.Properties.create(Material.EARTH).sound(SoundType.GROUND).hardnessAndResistance(0.5f).speedFactor(0.2F)));
	public static final RegistryObject<Block> DRIED_MUD = REGISTER.register("dried_mud", () -> new DriedMudBlock(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.GROUND).hardnessAndResistance(0.8f)));
	public static final RegistryObject<Block> MUD_BRICKS = REGISTER.register("dried_mud_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.GROUND).hardnessAndResistance(0.8f)));
	public static final RegistryObject<Block> MUD_BRICK_SLAB = REGISTER.register("dried_mud_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.GROUND).hardnessAndResistance(0.8F)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> MUD_BRICK_STAIRS = REGISTER.register("dried_mud_brick_stairs", () -> new StairsBlock(WildLandsBlocks.MUD_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(MUD_BRICKS.get())));
	
	public static final Block CYPRESS_LOG = createLogBlock(MaterialColor.WOOD, MaterialColor.CLAY).setRegistryName("cypress_log");//Loot Table done
	public static final Block STRIPPED_CYPRESS_LOG = createLogBlock(MaterialColor.WOOD, MaterialColor.CLAY).setRegistryName("stripped_cypress_log");//Loot Table done
	public static final Block CYPRESS_WOOD = createLogBlock(MaterialColor.WOOD, MaterialColor.CLAY).setRegistryName("cypress_wood");//Loot Table done
	public static final Block STRIPPED_CYPRESS_WOOD = createLogBlock(MaterialColor.WOOD, MaterialColor.CLAY).setRegistryName("stripped_cypress_wood");//Loot Table done
	public static final Block COCONUT_LOG = createLogBlock(MaterialColor.WOOD, MaterialColor.CLAY).setRegistryName("coconut_log");//Loot Table done
	public static final Block STRIPPED_COCONUT_LOG = createLogBlock(MaterialColor.WOOD, MaterialColor.CLAY).setRegistryName("stripped_coconut_log");//Loot Table done
	public static final Block COCONUT_WOOD = createLogBlock(MaterialColor.WOOD, MaterialColor.CLAY).setRegistryName("coconut_wood");//Loot Table done
	public static final Block STRIPPED_COCONUT_WOOD = createLogBlock(MaterialColor.WOOD, MaterialColor.CLAY).setRegistryName("stripped_coconut_wood");//Loot Table done
	public static final Block CHARRED_LOG = createLogBlock(MaterialColor.WOOD, MaterialColor.BLACK).setRegistryName("charred_log");
	public static final Block STRIPPED_CHARRED_LOG = createLogBlock(MaterialColor.WOOD, MaterialColor.BLACK).setRegistryName("stripped_charred_log");
	public static final Block CHARRED_WOOD = createLogBlock(MaterialColor.WOOD, MaterialColor.BLACK).setRegistryName("charred_wood");
	public static final Block STRIPPED_CHARRED_WOOD = createLogBlock(MaterialColor.WOOD, MaterialColor.BLACK).setRegistryName("stripped_charred_wood");

	public static final RegistryObject<Block> SMOLDERING_LOG = REGISTER.register("smoldering_log", () -> new SmolderingLogBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BLACK).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD).setLightLevel((state) -> {
		return 3;
	}), ParticleTypes.FLAME));

	public static final RegistryObject<Block> CYPRESS_PLANKS = REGISTER.register("cypress_planks", () -> new Block(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)));
	public static final RegistryObject<Block> CYPRESS_DOOR = REGISTER.register("cypress_door", () -> new DoorBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(3.0F).notSolid()));
	public static final RegistryObject<Block> CYPRESS_LEAVES = REGISTER.register("cypress_leaves", () -> createLeavesBlock());
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> CYPRESS_STAIRS = REGISTER.register("cypress_stairs", () -> new StairsBlock(WildLandsBlocks.CYPRESS_PLANKS.get().getDefaultState(), AbstractBlock.Properties.from(CYPRESS_PLANKS.get())));
	public static final RegistryObject<Block> CYPRESS_PRESSURE_PLATE = REGISTER.register("cypress_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.create(Material.WOOD, CYPRESS_PLANKS.get().getMaterialColor()).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> CYPRESS_FENCE = REGISTER.register("cypress_fence", () -> new FenceBlock(AbstractBlock.Properties.create(Material.WOOD, CYPRESS_PLANKS.get().getMaterialColor()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));   
	public static final RegistryObject<Block> CYPRESS_TRAPDOOR = REGISTER.register("cypress_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid().setAllowsSpawn(WildLandsBlocks::neverAllowSpawn)));
	public static final RegistryObject<Block> CYPRESS_FENCE_GATE = REGISTER.register("cypress_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, CYPRESS_PLANKS.get().getMaterialColor()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> CYPRESS_BUTTON = REGISTER.register("cypress_button", () -> new WoodButtonBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> CYPRESS_SLAB = REGISTER.register("cypress_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> CYPRESS_SAPLING = REGISTER.register("cypress_sapling", () -> new WLSapling(new WLTreeSpawners.Cypress(), AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0F).sound(SoundType.PLANT)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> POTTED_CYPRESS_SAPLING = REGISTER.register("potted_cypress_sapling", () -> new FlowerPotBlock(CYPRESS_SAPLING.get(), AbstractBlock.Properties.create(Material.MISCELLANEOUS).notSolid().zeroHardnessAndResistance()));
	public static final RegistryObject<Block> PALMETTO = REGISTER.register("palmetto", () -> new PalmettoBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().notSolid().sound(SoundType.PLANT)));
	public static final RegistryObject<Block> CATTAIL = REGISTER.register("cattail", () -> new DoubleWaterPlantBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
	public static final RegistryObject<Block> DUCKWEED = REGISTER.register("duckweed", () -> new DuckWeedBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT).notSolid()));
	public static final RegistryObject<Block> BEARD_MOSS = REGISTER.register("beard_moss", () -> new BeardMossBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
	public static final RegistryObject<Block> BEARD_MOSS_TOP = REGISTER.register("beard_moss_top", () -> new BeardMossTopBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
	public static final RegistryObject<Block> COCONUT_PLANKS = REGISTER.register("coconut_planks", () -> new Block(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)));
	public static final RegistryObject<Block> COCONUT_DOOR = REGISTER.register("coconut_door", () -> new DoorBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(3.0F).notSolid()));
	public static final RegistryObject<Block> COCONUT_LEAVES = REGISTER.register("coconut_leaves", () -> createLeavesBlock());
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> COCONUT_STAIRS = REGISTER.register("coconut_stairs", () -> new StairsBlock(WildLandsBlocks.COCONUT_PLANKS.get().getDefaultState(), AbstractBlock.Properties.from(COCONUT_PLANKS.get())));
	public static final RegistryObject<Block> COCONUT_PRESSURE_PLATE = REGISTER.register("coconut_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.create(Material.WOOD, COCONUT_PLANKS.get().getMaterialColor()).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> COCONUT_FENCE = REGISTER.register("coconut_fence", () -> new FenceBlock(AbstractBlock.Properties.create(Material.WOOD, COCONUT_PLANKS.get().getMaterialColor()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> COCONUT_TRAPDOOR = REGISTER.register("coconut_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid().setAllowsSpawn(WildLandsBlocks::neverAllowSpawn)));
	public static final RegistryObject<Block> COCONUT_FENCE_GATE = REGISTER.register("coconut_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, COCONUT_PLANKS.get().getMaterialColor()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> COCONUT_BUTTON = REGISTER.register("coconut_button", () -> new WoodButtonBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> COCONUT_SLAB = REGISTER.register("coconut_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> COCONUT_SAPLING = REGISTER.register("coconut_sapling", () -> new WLSapling(new WLTreeSpawners.Coconut(), AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0F).sound(SoundType.PLANT)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> POTTED_COCONUT_SAPLING = REGISTER.register("potted_coconut_sapling", () -> new FlowerPotBlock(COCONUT_SAPLING.get(), AbstractBlock.Properties.create(Material.MISCELLANEOUS).notSolid().zeroHardnessAndResistance()));

	public static final RegistryObject<Block> CHARRED_PLANKS = REGISTER.register("charred_planks", () -> new Block(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)));
	public static final RegistryObject<Block> CHARRED_DOOR = REGISTER.register("charred_door", () -> new DoorBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(3.0F).notSolid()));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> CHARRED_STAIRS = REGISTER.register("charred_stairs", () -> new StairsBlock(WildLandsBlocks.CHARRED_PLANKS.get().getDefaultState(), AbstractBlock.Properties.from(CHARRED_PLANKS.get())));
	public static final RegistryObject<Block> CHARRED_PRESSURE_PLATE = REGISTER.register("charred_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.create(Material.WOOD, CHARRED_PLANKS.get().getMaterialColor()).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> CHARRED_FENCE = REGISTER.register("charred_fence", () -> new FenceBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BLACK).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> CHARRED_TRAPDOOR = REGISTER.register("charred_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BLACK).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid().setAllowsSpawn(WildLandsBlocks::neverAllowSpawn)));
	public static final RegistryObject<Block> CHARRED_FENCE_GATE = REGISTER.register("charred_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BLACK).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> CHARRED_BUTTON = REGISTER.register("charred_button", () -> new WoodButtonBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> CHARRED_SLAB = REGISTER.register("charred_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BLACK).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));

	public static final RegistryObject<Block> CONGLOMERATE = REGISTER.register("conglomerate", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> CONGLOMERATE_STAIRS = REGISTER.register("conglomerate_stairs", () -> new StairsBlock(WildLandsBlocks.CONGLOMERATE.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> CONGLOMERATE_SLAB = REGISTER.register("conglomerate_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> CONGLOMERATE_BRICKS = REGISTER.register("conglomerate_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> CONGLOMERATE_BRICK_STAIRS = REGISTER.register("conglomerate_brick_stairs", () -> new StairsBlock(WildLandsBlocks.CONGLOMERATE_BRICKS.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> CONGLOMERATE_BRICK_SLAB = REGISTER.register("conglomerate_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> CONGLOMERATE_BRICK_WALL = REGISTER.register("conglomerate_brick_wall", () -> new WallBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));

	public static final RegistryObject<Block> GABBRO = REGISTER.register("gabbro", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> OLIVINE_GABBRO = REGISTER.register("olivine_gabbro", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> GABBRO_STAIRS = REGISTER.register("gabbro_stairs", () -> new StairsBlock(WildLandsBlocks.GABBRO.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> GABBRO_SLAB = REGISTER.register("gabbro_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> GABBRO_WALL = REGISTER.register("gabbro_wall", () -> new WallBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> POLISHED_GABBRO = REGISTER.register("polished_gabbro", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> POLISHED_GABBRO_STAIRS = REGISTER.register("polished_gabbro_stairs", () -> new StairsBlock(WildLandsBlocks.POLISHED_GABBRO.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> POLISHED_GABBRO_SLAB = REGISTER.register("polished_gabbro_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));

	public static final RegistryObject<Block> DOLERITE = REGISTER.register("dolerite", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> DOLERITE_STAIRS = REGISTER.register("dolerite_stairs", () -> new StairsBlock(WildLandsBlocks.DOLERITE.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> DOLERITE_SLAB = REGISTER.register("dolerite_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> DOLERITE_WALL = REGISTER.register("dolerite_wall", () -> new WallBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> POLISHED_DOLERITE = REGISTER.register("polished_dolerite", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> POLISHED_DOLERITE_STAIRS = REGISTER.register("polished_dolerite_stairs", () -> new StairsBlock(WildLandsBlocks.POLISHED_DOLERITE.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> POLISHED_DOLERITE_SLAB = REGISTER.register("polished_dolerite_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));

	public static final RegistryObject<Block> OLIVINE = REGISTER.register("olivine_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> CUT_OLIVINE = REGISTER.register("cut_olivine_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> OLIVINE_PRESSURE_PLATE = REGISTER.register("olivine_pressure_plate", () -> new OlivinePressurePlate(OlivinePressurePlate.Sensitivity.MOBS, AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> PEARL = REGISTER.register("pearl_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> PEARL_TILES = REGISTER.register("pearl_tile_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<CoconutBlock> COCONUT = REGISTER.register("coconut", () -> new CoconutBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).notSolid().sound(SoundType.WOOD)));
	public static final RegistryObject<WallCoconutBlock> WALL_COCONUT = REGISTER.register("wall_coconut", () -> new WallCoconutBlock(AbstractBlock.Properties.from(COCONUT.get())));
	public static final RegistryObject<Block> FINE_SAND = REGISTER.register("fine_sand", () -> new SandBlock(0xE6E2B6, AbstractBlock.Properties.create(Material.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
	public static final RegistryObject<Block> FINE_SANDSTONE = REGISTER.register("fine_sandstone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> CUT_FINE_SANDSTONE = REGISTER.register("cut_fine_sandstone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> SMOOTH_FINE_SANDSTONE = REGISTER.register("smooth_fine_sandstone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> FINE_SANDSTONE_SLAB = REGISTER.register("fine_sandstone_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> CUT_FINE_SANDSTONE_SLAB = REGISTER.register("cut_fine_sandstone_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> SMOOTH_FINE_SANDSTONE_SLAB = REGISTER.register("smooth_fine_sandstone_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F).sound(SoundType.STONE)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> FINE_SANDSTONE_STAIRS = REGISTER.register("fine_sandstone_stairs", () -> new StairsBlock(WildLandsBlocks.FINE_SANDSTONE.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F).sound(SoundType.STONE)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> SMOOTH_FINE_SANDSTONE_STAIRS = REGISTER.register("smooth_fine_sandstone_stairs", () -> new StairsBlock(WildLandsBlocks.SMOOTH_FINE_SANDSTONE.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> FINE_SANDSTONE_WALL = REGISTER.register("fine_sandstone_wall", () -> new WallBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F).sound(SoundType.STONE)));

	public static final RegistryObject<Block> SEAWEED_BLOCK = REGISTER.register("seaweed_block", () -> new Block(AbstractBlock.Properties.create(Material.ORGANIC).hardnessAndResistance(0.5F).sound(SoundType.WET_GRASS)));

	public static final RegistryObject<StarfishBlock> BLUE_STARFISH = REGISTER.register("blue_starfish", () -> new StarfishBlock(AbstractBlock.Properties.create(Material.ORGANIC).zeroHardnessAndResistance().doesNotBlockMovement().notSolid().sound(SoundType.WET_GRASS)));
	public static final RegistryObject<StarfishBlock> MAGENTA_STARFISH = REGISTER.register("magenta_starfish", () -> new StarfishBlock(AbstractBlock.Properties.create(Material.ORGANIC).zeroHardnessAndResistance().doesNotBlockMovement().notSolid().sound(SoundType.WET_GRASS)));
	public static final RegistryObject<StarfishBlock> ORANGE_STARFISH = REGISTER.register("orange_starfish", () -> new StarfishBlock(AbstractBlock.Properties.create(Material.ORGANIC).zeroHardnessAndResistance().doesNotBlockMovement().notSolid().sound(SoundType.WET_GRASS)));
	public static final RegistryObject<StarfishBlock> PINK_STARFISH = REGISTER.register("pink_starfish", () -> new StarfishBlock(AbstractBlock.Properties.create(Material.ORGANIC).zeroHardnessAndResistance().doesNotBlockMovement().notSolid().sound(SoundType.WET_GRASS)));
	public static final RegistryObject<StarfishBlock> RED_STARFISH = REGISTER.register("red_starfish", () -> new StarfishBlock(AbstractBlock.Properties.create(Material.ORGANIC).zeroHardnessAndResistance().doesNotBlockMovement().notSolid().sound(SoundType.WET_GRASS)));
	public static final RegistryObject<UrchinBlock> URCHIN = REGISTER.register("urchin", () -> new UrchinBlock(AbstractBlock.Properties.create(Material.ORGANIC).zeroHardnessAndResistance().doesNotBlockMovement().notSolid().sound(SoundType.WET_GRASS)));
	
	public static final RegistryObject<Block> CYPRESS_SIGN = REGISTER.register("cypress_sign", () -> new WLStandingSignBlock(AbstractBlock.Properties.create(Material.WOOD, WildLandsBlocks.CYPRESS_LOG.getMaterialColor()).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD), WLWoodTypes.CYPRESS));
	public static final RegistryObject<Block> CYPRESS_WALL_SIGN = REGISTER.register("cypress_wall_sign", () -> new WLWallSignBlock(AbstractBlock.Properties.create(Material.WOOD, WildLandsBlocks.CYPRESS_LOG.getMaterialColor()).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD), WLWoodTypes.CYPRESS));
	public static final RegistryObject<Block> COCONUT_SIGN = REGISTER.register("coconut_sign", () -> new WLStandingSignBlock(AbstractBlock.Properties.create(Material.WOOD, WildLandsBlocks.COCONUT_LOG.getMaterialColor()).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD), WLWoodTypes.COCONUT));
	public static final RegistryObject<Block> COCONUT_WALL_SIGN = REGISTER.register("coconut_wall_sign", () -> new WLWallSignBlock(AbstractBlock.Properties.create(Material.WOOD, WildLandsBlocks.COCONUT_LOG.getMaterialColor()).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD), WLWoodTypes.COCONUT));
	public static final RegistryObject<Block> CHARRED_SIGN = REGISTER.register("charred_sign", () -> new WLStandingSignBlock(AbstractBlock.Properties.create(Material.WOOD, WildLandsBlocks.CHARRED_LOG.getMaterialColor()).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD), WLWoodTypes.CHARRED));
	public static final RegistryObject<Block> CHARRED_WALL_SIGN = REGISTER.register("charred_wall_sign", () -> new WLWallSignBlock(AbstractBlock.Properties.create(Material.WOOD, WildLandsBlocks.CHARRED_LOG.getMaterialColor()).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD), WLWoodTypes.CHARRED));
	
	public static final Block CHARRED_GRASS = new CharredGrassBlock(AbstractBlock.Properties.create(Material.EARTH).hardnessAndResistance(0.5F).sound(SoundType.GROUND)).setRegistryName("charred_grass");
	public static final Block CHARRED_TALL_GRASS = new CharredTallGrassBlock(AbstractBlock.Properties.create(Material.PLANTS).zeroHardnessAndResistance().doesNotBlockMovement().notSolid().sound(SoundType.PLANT)).setRegistryName("charred_tall_grass");
	public static final Block CHARRED_BUSH = new CharredTallGrassBlock(AbstractBlock.Properties.create(Material.PLANTS).zeroHardnessAndResistance().doesNotBlockMovement().notSolid().sound(SoundType.PLANT)).setRegistryName("charred_bush");

	public static final RegistryObject<Block> OLIVINE_LAMP = REGISTER.register("olivine_lamp", () -> new OlivineLampBlock(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(1.0F).sound(SoundType.LANTERN).setLightLevel((state) -> {
		return 1 * state.get(OlivineLampBlock.XP_0_10);
	})));
	
	public static final RegistryObject<JellyBlock> JELLY_BLOCK = REGISTER.register("jelly_block", () -> new JellyBlock(AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.PINK).sound(SoundType.SLIME).slipperiness(0.8F).notSolid()));

	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry().register(COCONUT_LOG);
		event.getRegistry().register(COCONUT_WOOD);
		event.getRegistry().register(CYPRESS_LOG);
		event.getRegistry().register(CYPRESS_WOOD);
		event.getRegistry().register(STRIPPED_COCONUT_LOG);
		event.getRegistry().register(STRIPPED_COCONUT_WOOD);
		event.getRegistry().register(STRIPPED_CYPRESS_LOG);
		event.getRegistry().register(STRIPPED_CYPRESS_WOOD);
		event.getRegistry().register(CHARRED_LOG);
		event.getRegistry().register(CHARRED_WOOD);
		event.getRegistry().register(STRIPPED_CHARRED_LOG);
		event.getRegistry().register(STRIPPED_CHARRED_WOOD);
		event.getRegistry().register(CHARRED_GRASS);
		event.getRegistry().register(CHARRED_TALL_GRASS);
		event.getRegistry().register(CHARRED_BUSH);
		if (FMLEnvironment.dist == Dist.CLIENT) {
			RenderType cutoutRenderType = RenderType.getCutout();
			RenderType cutoutMippedRenderType = RenderType.getCutoutMipped();
			RenderType translucentRenderType = RenderType.getTranslucent();
			RenderTypeLookup.setRenderLayer(CYPRESS_SAPLING.get(), cutoutRenderType);
			RenderTypeLookup.setRenderLayer(POTTED_CYPRESS_SAPLING.get(), cutoutRenderType);
			RenderTypeLookup.setRenderLayer(COCONUT_DOOR.get(), cutoutRenderType);
			RenderTypeLookup.setRenderLayer(BLUE_STARFISH.get(), cutoutRenderType);
			RenderTypeLookup.setRenderLayer(MAGENTA_STARFISH.get(), cutoutRenderType);
			RenderTypeLookup.setRenderLayer(ORANGE_STARFISH.get(), cutoutRenderType);
			RenderTypeLookup.setRenderLayer(PINK_STARFISH.get(), cutoutRenderType);
			RenderTypeLookup.setRenderLayer(RED_STARFISH.get(), cutoutRenderType);
			RenderTypeLookup.setRenderLayer(PALMETTO.get(), cutoutMippedRenderType);
			RenderTypeLookup.setRenderLayer(COCONUT_SAPLING.get(), cutoutRenderType);
			RenderTypeLookup.setRenderLayer(POTTED_COCONUT_SAPLING.get(), cutoutRenderType);
			RenderTypeLookup.setRenderLayer(CATTAIL.get(), cutoutRenderType);
			RenderTypeLookup.setRenderLayer(DUCKWEED.get(), cutoutRenderType);
			RenderTypeLookup.setRenderLayer(BEARD_MOSS.get(), cutoutRenderType);
			RenderTypeLookup.setRenderLayer(BEARD_MOSS_TOP.get(), cutoutRenderType);
			RenderTypeLookup.setRenderLayer(CHARRED_TALL_GRASS, cutoutRenderType);
			RenderTypeLookup.setRenderLayer(CHARRED_BUSH, cutoutRenderType);
			RenderTypeLookup.setRenderLayer(URCHIN.get(), cutoutRenderType);
			RenderTypeLookup.setRenderLayer(JELLY_BLOCK.get(), translucentRenderType);
		}
	}

	private static RotatedPillarBlock createLogBlock(MaterialColor topColor, MaterialColor barkColor) {
		return new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, (state) -> {
			return state.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topColor : barkColor;
		}).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
	}

	private static LeavesBlock createLeavesBlock() {
		return new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid().setAllowsSpawn(WildLandsBlocks::allowsSpawnOnLeaves).setSuffocates(WildLandsBlocks::isntSolid).setBlocksVision(WildLandsBlocks::isntSolid));
	}

	private static Boolean allowsSpawnOnLeaves(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
		return entity == EntityType.OCELOT || entity == EntityType.PARROT;
	}

	private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
		return false;
	}

	private static Boolean neverAllowSpawn(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}


}
