package superlord.wildlands.init;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.MudBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.block.BeardMossBlock;
import superlord.wildlands.common.block.BeardMossTopBlock;
import superlord.wildlands.common.block.CharredGrassBlock;
import superlord.wildlands.common.block.CharredTallGrassBlock;
import superlord.wildlands.common.block.CoconutBlock;
import superlord.wildlands.common.block.CoconutSaplingBlock;
import superlord.wildlands.common.block.CypressSaplingBlock;
import superlord.wildlands.common.block.DoubleWaterPlantBlock;
import superlord.wildlands.common.block.DuckWeedBlock;
import superlord.wildlands.common.block.GabbroOlivineBlock;
import superlord.wildlands.common.block.JellyBlock;
import superlord.wildlands.common.block.OlivineLampBlock;
import superlord.wildlands.common.block.OlivinePressurePlateBlock;
import superlord.wildlands.common.block.PalmettoBlock;
import superlord.wildlands.common.block.SmolderingLogBlock;
import superlord.wildlands.common.block.StarfishBlock;
import superlord.wildlands.common.block.UrchinBlock;
import superlord.wildlands.common.block.WLStandingSignBlock;
import superlord.wildlands.common.block.WLWallSignBlock;
import superlord.wildlands.common.block.WallCoconutBlock;

@Mod.EventBusSubscriber(modid = WildLands.MOD_ID, bus = Bus.MOD)
public class WLBlocks {
	public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, WildLands.MOD_ID);

	public static final RegistryObject<Block> MUD = REGISTER.register("mud", () -> new MudBlock(Block.Properties.copy(Blocks.MUD)));
	
	public static final RegistryObject<Block> SMOLDERING_LOG = REGISTER.register("smoldering_log", () -> new SmolderingLogBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).lightLevel((state) -> {
		return 3;
	}), ParticleTypes.FLAME));

	public static final RegistryObject<Block> CYPRESS_LOG = REGISTER.register("cypress_log", () -> createLog());//Loot Table done
	public static final RegistryObject<Block> STRIPPED_CYPRESS_LOG = REGISTER.register("stripped_cypress_log", () -> createLog());//Loot Table done
	public static final RegistryObject<Block> CYPRESS_WOOD = REGISTER.register("cypress_wood", () -> createLog());//Loot Table done
	public static final RegistryObject<Block> STRIPPED_CYPRESS_WOOD =  REGISTER.register("stripped_cypress_wood", () -> createLog());;//Loot Table done
	public static final RegistryObject<Block> COCONUT_LOG = REGISTER.register("coconut_log", () -> createLog());;//Loot Table done
	public static final RegistryObject<Block> STRIPPED_COCONUT_LOG = REGISTER.register("stripped_coconut_log", () -> createLog());;//Loot Table done
	public static final RegistryObject<Block> COCONUT_WOOD = REGISTER.register("coconut_wood", () -> createLog());;//Loot Table done
	public static final RegistryObject<Block> STRIPPED_COCONUT_WOOD = REGISTER.register("stripped_coconut_wood", () -> createLog());;//Loot Table done
	public static final RegistryObject<Block> CHARRED_LOG = REGISTER.register("charred_log", () -> createLog());;
	public static final RegistryObject<Block> STRIPPED_CHARRED_LOG = REGISTER.register("stripped_charred_log", () -> createLog());;
	public static final RegistryObject<Block> CHARRED_WOOD = REGISTER.register("charred_wood", () -> createLog());;
	public static final RegistryObject<Block> STRIPPED_CHARRED_WOOD = REGISTER.register("stripped_charred_wood", () -> createLog());;

	public static final RegistryObject<Block> CYPRESS_PLANKS = REGISTER.register("cypress_planks", () -> new Block(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F)));
	public static final RegistryObject<Block> CYPRESS_DOOR = REGISTER.register("cypress_door", () -> new DoorBlock(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(3.0F), WLWoodTypes.CYPRESS_TYPE));
	public static final RegistryObject<Block> CYPRESS_LEAVES = REGISTER.register("cypress_leaves", () -> leaves(SoundType.GRASS));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> CYPRESS_STAIRS = REGISTER.register("cypress_stairs", () -> new StairBlock(WLBlocks.CYPRESS_PLANKS.get().defaultBlockState(), Block.Properties.copy(CYPRESS_PLANKS.get())));
	public static final RegistryObject<Block> CYPRESS_PRESSURE_PLATE = REGISTER.register("cypress_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.of().mapColor(CYPRESS_PLANKS.get().defaultMapColor()).forceSolidOn().noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), WLWoodTypes.CYPRESS_TYPE));
	public static final RegistryObject<Block> CYPRESS_FENCE = REGISTER.register("cypress_fence", () -> new FenceBlock(Block.Properties.of().mapColor(CYPRESS_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));   
	public static final RegistryObject<Block> CYPRESS_TRAPDOOR = REGISTER.register("cypress_trapdoor", () -> new TrapDoorBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(3.0F).sound(SoundType.WOOD).isValidSpawn(WLBlocks::neverAllowSpawn), WLWoodTypes.CYPRESS_TYPE));
	public static final RegistryObject<Block> CYPRESS_FENCE_GATE = REGISTER.register("cypress_fence_gate", () -> new FenceGateBlock(Block.Properties.of().mapColor(CYPRESS_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava(), WLWoodTypes.CYPRESS));
	public static final RegistryObject<Block> CYPRESS_BUTTON = REGISTER.register("cypress_button", () -> new ButtonBlock(Block.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY), WLWoodTypes.CYPRESS_TYPE, 30, true));
	public static final RegistryObject<Block> CYPRESS_SLAB = REGISTER.register("cypress_slab", () -> new SlabBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> CYPRESS_SAPLING = REGISTER.register("cypress_sapling", () -> new CypressSaplingBlock(Block.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().strength(0F).sound(SoundType.GRASS)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> POTTED_CYPRESS_SAPLING = REGISTER.register("potted_cypress_sapling", () -> new FlowerPotBlock(CYPRESS_SAPLING.get(), Block.Properties.of().instabreak()));
	public static final RegistryObject<Block> PALMETTO = REGISTER.register("palmetto", () -> new PalmettoBlock(Block.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> CATTAIL = REGISTER.register("cattail", () -> new DoubleWaterPlantBlock(Block.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> DUCKWEED = REGISTER.register("duckweed", () -> new DuckWeedBlock(Block.Properties.of().mapColor(MapColor.WATER).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> BEARD_MOSS = REGISTER.register("beard_moss", () -> new BeardMossBlock(Block.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> BEARD_MOSS_TOP = REGISTER.register("beard_moss_top", () -> new BeardMossTopBlock(Block.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> COCONUT_PLANKS = REGISTER.register("coconut_planks", () -> new Block(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F)));
	public static final RegistryObject<Block> COCONUT_DOOR = REGISTER.register("coconut_door", () -> new DoorBlock(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(3.0F), WLWoodTypes.COCONUT_TYPE));
	public static final RegistryObject<Block> COCONUT_LEAVES = REGISTER.register("coconut_leaves", () -> leaves(SoundType.GRASS));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> COCONUT_STAIRS = REGISTER.register("coconut_stairs", () -> new StairBlock(WLBlocks.COCONUT_PLANKS.get().defaultBlockState(), Block.Properties.copy(COCONUT_PLANKS.get())));
	public static final RegistryObject<Block> COCONUT_PRESSURE_PLATE = REGISTER.register("coconut_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.of().mapColor(COCONUT_PLANKS.get().defaultMapColor()).forceSolidOn().noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), WLWoodTypes.COCONUT_TYPE));
	public static final RegistryObject<Block> COCONUT_FENCE = REGISTER.register("coconut_fence", () -> new FenceBlock(Block.Properties.of().mapColor(COCONUT_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
	public static final RegistryObject<Block> COCONUT_TRAPDOOR = REGISTER.register("coconut_trapdoor", () -> new TrapDoorBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(3.0F).sound(SoundType.WOOD).isValidSpawn(WLBlocks::neverAllowSpawn), WLWoodTypes.COCONUT_TYPE));
	public static final RegistryObject<Block> COCONUT_FENCE_GATE = REGISTER.register("coconut_fence_gate", () -> new FenceGateBlock(Block.Properties.of().mapColor(COCONUT_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava(), WLWoodTypes.COCONUT));
	public static final RegistryObject<Block> COCONUT_BUTTON = REGISTER.register("coconut_button", () -> new ButtonBlock(Block.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY), WLWoodTypes.COCONUT_TYPE, 30, true));
	public static final RegistryObject<Block> COCONUT_SLAB = REGISTER.register("coconut_slab", () -> new SlabBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> COCONUT_SAPLING = REGISTER.register("coconut_sapling", () -> new CoconutSaplingBlock(Block.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().strength(0F).sound(SoundType.GRASS)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> POTTED_COCONUT_SAPLING = REGISTER.register("potted_coconut_sapling", () -> new FlowerPotBlock(COCONUT_SAPLING.get(), Block.Properties.of().instabreak()));

	public static final RegistryObject<Block> CHARRED_PLANKS = REGISTER.register("charred_planks", () -> new Block(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F)));
	public static final RegistryObject<Block> CHARRED_DOOR = REGISTER.register("charred_door", () -> new DoorBlock(Block.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(3.0F), WLWoodTypes.CHARRED_TYPE));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> CHARRED_STAIRS = REGISTER.register("charred_stairs", () -> new StairBlock(WLBlocks.CHARRED_PLANKS.get().defaultBlockState(), Block.Properties.copy(CHARRED_PLANKS.get())));
	public static final RegistryObject<Block> CHARRED_PRESSURE_PLATE = REGISTER.register("charred_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.of().mapColor(CHARRED_PLANKS.get().defaultMapColor()).forceSolidOn().noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), WLWoodTypes.CHARRED_TYPE));
	public static final RegistryObject<Block> CHARRED_FENCE = REGISTER.register("charred_fence", () -> new FenceBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> CHARRED_TRAPDOOR = REGISTER.register("charred_trapdoor", () -> new TrapDoorBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(3.0F).sound(SoundType.WOOD).isValidSpawn(WLBlocks::neverAllowSpawn), WLWoodTypes.CHARRED_TYPE));
	public static final RegistryObject<Block> CHARRED_FENCE_GATE = REGISTER.register("charred_fence_gate", () -> new FenceGateBlock(Block.Properties.of().mapColor(CHARRED_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava(), WLWoodTypes.CHARRED));
	public static final RegistryObject<Block> CHARRED_BUTTON = REGISTER.register("charred_button", () -> new ButtonBlock(Block.Properties.of().noCollission().strength(0.5F).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY), WLWoodTypes.CHARRED_TYPE, 30, true));
	public static final RegistryObject<Block> CHARRED_SLAB = REGISTER.register("charred_slab", () -> new SlabBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));

	public static final RegistryObject<Block> CONGLOMERATE = REGISTER.register("conglomerate", () -> new Block(Block.Properties.copy(Blocks.STONE)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> CONGLOMERATE_STAIRS = REGISTER.register("conglomerate_stairs", () -> new StairBlock(WLBlocks.CONGLOMERATE.get().defaultBlockState(), Block.Properties.copy(Blocks.STONE_STAIRS)));
	public static final RegistryObject<Block> CONGLOMERATE_SLAB = REGISTER.register("conglomerate_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.STONE_SLAB)));
	public static final RegistryObject<Block> CONGLOMERATE_BRICKS = REGISTER.register("conglomerate_bricks", () -> new Block(Block.Properties.copy(Blocks.STONE)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> CONGLOMERATE_BRICK_STAIRS = REGISTER.register("conglomerate_brick_stairs", () -> new StairBlock(WLBlocks.CONGLOMERATE_BRICKS.get().defaultBlockState(), Block.Properties.copy(Blocks.STONE_STAIRS)));
	public static final RegistryObject<Block> CONGLOMERATE_BRICK_SLAB = REGISTER.register("conglomerate_brick_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.STONE_SLAB)));
	public static final RegistryObject<Block> CONGLOMERATE_BRICK_WALL = REGISTER.register("conglomerate_brick_wall", () -> new WallBlock(Block.Properties.copy(Blocks.COBBLESTONE_WALL)));

	public static final RegistryObject<Block> GABBRO = REGISTER.register("gabbro", () -> new Block(Block.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<Block> OLIVINE_GABBRO = REGISTER.register("olivine_gabbro", () -> new GabbroOlivineBlock(Block.Properties.copy(Blocks.STONE)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> GABBRO_STAIRS = REGISTER.register("gabbro_stairs", () -> new StairBlock(WLBlocks.GABBRO.get().defaultBlockState(), Block.Properties.copy(Blocks.STONE_STAIRS)));
	public static final RegistryObject<Block> GABBRO_SLAB = REGISTER.register("gabbro_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.STONE_SLAB)));
	public static final RegistryObject<Block> GABBRO_WALL = REGISTER.register("gabbro_wall", () -> new WallBlock(Block.Properties.copy(Blocks.COBBLESTONE_WALL)));
	public static final RegistryObject<Block> POLISHED_GABBRO = REGISTER.register("polished_gabbro", () -> new Block(Block.Properties.copy(Blocks.STONE)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> POLISHED_GABBRO_STAIRS = REGISTER.register("polished_gabbro_stairs", () -> new StairBlock(WLBlocks.POLISHED_GABBRO.get().defaultBlockState(), Block.Properties.copy(Blocks.STONE_STAIRS)));
	public static final RegistryObject<Block> POLISHED_GABBRO_SLAB = REGISTER.register("polished_gabbro_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.STONE_SLAB)));

	public static final RegistryObject<Block> DOLERITE = REGISTER.register("dolerite", () -> new Block(Block.Properties.copy(Blocks.STONE)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> DOLERITE_STAIRS = REGISTER.register("dolerite_stairs", () -> new StairBlock(WLBlocks.DOLERITE.get().defaultBlockState(), Block.Properties.copy(Blocks.STONE_STAIRS)));
	public static final RegistryObject<Block> DOLERITE_SLAB = REGISTER.register("dolerite_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.STONE_SLAB)));
	public static final RegistryObject<Block> DOLERITE_WALL = REGISTER.register("dolerite_wall", () -> new WallBlock(Block.Properties.copy(Blocks.COBBLESTONE_WALL)));
	public static final RegistryObject<Block> POLISHED_DOLERITE = REGISTER.register("polished_dolerite", () -> new Block(Block.Properties.copy(Blocks.STONE)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> POLISHED_DOLERITE_STAIRS = REGISTER.register("polished_dolerite_stairs", () -> new StairBlock(WLBlocks.POLISHED_DOLERITE.get().defaultBlockState(), Block.Properties.copy(Blocks.STONE_STAIRS)));
	public static final RegistryObject<Block> POLISHED_DOLERITE_SLAB = REGISTER.register("polished_dolerite_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.STONE_SLAB)));

	public static final RegistryObject<Block> OLIVINE = REGISTER.register("olivine_block", () -> new Block(Block.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> OLIVINE_PRESSURE_PLATE = REGISTER.register("olivine_pressure_plate", () -> new OlivinePressurePlateBlock(OlivinePressurePlateBlock.Sensitivity.MOBS, Block.Properties.of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL), WLWoodTypes.OLIVINE_TYPE));
	public static final RegistryObject<CoconutBlock> COCONUT = REGISTER.register("coconut", () -> new CoconutBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.5F).sound(SoundType.WOOD)));
	public static final RegistryObject<WallCoconutBlock> WALL_COCONUT = REGISTER.register("wall_coconut", () -> new WallCoconutBlock(Block.Properties.copy(COCONUT.get())));
	public static final RegistryObject<Block> FINE_SAND = REGISTER.register("fine_sand", () -> new SandBlock(0xE6E2B6, Block.Properties.of().mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.SAND)));
	public static final RegistryObject<Block> FINE_SANDSTONE = REGISTER.register("fine_sandstone", () -> new Block(Block.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(0.8F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> CUT_FINE_SANDSTONE = REGISTER.register("cut_fine_sandstone", () -> new Block(Block.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(0.8F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> SMOOTH_FINE_SANDSTONE = REGISTER.register("smooth_fine_sandstone", () -> new Block(Block.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(0.8F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> FINE_SANDSTONE_SLAB = REGISTER.register("fine_sandstone_slab", () -> new SlabBlock(Block.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(0.8F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> CUT_FINE_SANDSTONE_SLAB = REGISTER.register("cut_fine_sandstone_slab", () -> new SlabBlock(Block.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(0.8F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> SMOOTH_FINE_SANDSTONE_SLAB = REGISTER.register("smooth_fine_sandstone_slab", () -> new SlabBlock(Block.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(0.8F).sound(SoundType.STONE)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> FINE_SANDSTONE_STAIRS = REGISTER.register("fine_sandstone_stairs", () -> new StairBlock(WLBlocks.FINE_SANDSTONE.get().defaultBlockState(), Block.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(0.8F).sound(SoundType.STONE)));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> SMOOTH_FINE_SANDSTONE_STAIRS = REGISTER.register("smooth_fine_sandstone_stairs", () -> new StairBlock(WLBlocks.SMOOTH_FINE_SANDSTONE.get().defaultBlockState(), Block.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(0.8F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> FINE_SANDSTONE_WALL = REGISTER.register("fine_sandstone_wall", () -> new WallBlock(Block.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(0.8F).sound(SoundType.STONE)));

	public static final RegistryObject<Block> SEAWEED_BLOCK = REGISTER.register("seaweed_block", () -> new Block(Block.Properties.of().mapColor(MapColor.WATER).strength(0.5F).sound(SoundType.WET_GRASS)));

	public static final RegistryObject<StarfishBlock> BLUE_STARFISH = REGISTER.register("blue_starfish", () -> new StarfishBlock(Block.Properties.of().mapColor(MapColor.WATER).instabreak().noCollission().sound(SoundType.WET_GRASS)));
	public static final RegistryObject<StarfishBlock> MAGENTA_STARFISH = REGISTER.register("magenta_starfish", () -> new StarfishBlock(Block.Properties.of().mapColor(MapColor.WATER).instabreak().noCollission().sound(SoundType.WET_GRASS)));
	public static final RegistryObject<StarfishBlock> ORANGE_STARFISH = REGISTER.register("orange_starfish", () -> new StarfishBlock(Block.Properties.of().mapColor(MapColor.WATER).instabreak().noCollission().sound(SoundType.WET_GRASS)));
	public static final RegistryObject<StarfishBlock> PINK_STARFISH = REGISTER.register("pink_starfish", () -> new StarfishBlock(Block.Properties.of().mapColor(MapColor.WATER).instabreak().noCollission().sound(SoundType.WET_GRASS)));
	public static final RegistryObject<StarfishBlock> RED_STARFISH = REGISTER.register("red_starfish", () -> new StarfishBlock(Block.Properties.of().mapColor(MapColor.WATER).instabreak().noCollission().sound(SoundType.WET_GRASS)));
	public static final RegistryObject<UrchinBlock> URCHIN = REGISTER.register("urchin", () -> new UrchinBlock(Block.Properties.of().mapColor(MapColor.WATER).instabreak().noCollission().sound(SoundType.WET_GRASS)));

	public static final RegistryObject<WLStandingSignBlock> CYPRESS_SIGN = REGISTER.register("cypress_sign", () -> new WLStandingSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), WLWoodTypes.CYPRESS));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<WLWallSignBlock> CYPRESS_WALL_SIGN = REGISTER.register("cypress_wall_sign", () -> new WLWallSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(CYPRESS_SIGN.get()), WLWoodTypes.CYPRESS));
	public static final RegistryObject<WLStandingSignBlock> COCONUT_SIGN = REGISTER.register("coconut_sign", () -> new WLStandingSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), WLWoodTypes.COCONUT));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<WLWallSignBlock> COCONUT_WALL_SIGN = REGISTER.register("coconut_wall_sign", () -> new WLWallSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(COCONUT_SIGN.get()), WLWoodTypes.COCONUT));
	public static final RegistryObject<WLStandingSignBlock> CHARRED_SIGN = REGISTER.register("charred_sign", () -> new WLStandingSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), WLWoodTypes.CHARRED));
	@SuppressWarnings("deprecation")
	public static final RegistryObject<WLWallSignBlock> CHARRED_WALL_SIGN = REGISTER.register("charred_wall_sign", () -> new WLWallSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(CHARRED_SIGN.get()), WLWoodTypes.CHARRED));

	public static final RegistryObject<Block> CHARRED_GRASS = REGISTER.register("charred_grass", () -> new CharredGrassBlock(Block.Properties.of().mapColor(MapColor.GRASS).strength(0.5F).sound(SoundType.GRASS)));
	public static final RegistryObject<Block> CHARRED_TALL_GRASS = REGISTER.register("charred_tall_grass", () -> new CharredTallGrassBlock(Block.Properties.of().mapColor(MapColor.GRASS).instabreak().noCollission().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> CHARRED_BUSH = REGISTER.register("charred_bush", () -> new CharredTallGrassBlock(Block.Properties.of().mapColor(MapColor.GRASS).instabreak().noCollission().sound(SoundType.GRASS)));

	public static final RegistryObject<Block> OLIVINE_LAMP = REGISTER.register("olivine_lamp", () -> new OlivineLampBlock(Block.Properties.of().strength(1.0F).sound(SoundType.LANTERN).lightLevel((state) -> {
		return 1 * state.getValue(OlivineLampBlock.XP_0_10);
	})));

	public static final RegistryObject<JellyBlock> JELLY_BLOCK = REGISTER.register("jelly_block", () -> new JellyBlock(Block.Properties.of().mapColor(MapColor.CLAY).sound(SoundType.SLIME_BLOCK).friction(0.8F).noOcclusion()));

	private static RotatedPillarBlock createLog() {
		return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD));
	}

	private static LeavesBlock leaves(SoundType p_152615_) {
		return new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(p_152615_).noOcclusion().isValidSpawn(WLBlocks::allowsSpawnOnLeaves).isSuffocating(WLBlocks::never).isViewBlocking(WLBlocks::never));
	}

	private static Boolean allowsSpawnOnLeaves(BlockState state, BlockGetter reader, BlockPos pos, EntityType<?> entity) {
		return entity == EntityType.OCELOT || entity == EntityType.PARROT;
	}

	private static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
		return false;
	}

	private static Boolean neverAllowSpawn(BlockState state, BlockGetter reader, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}

}
