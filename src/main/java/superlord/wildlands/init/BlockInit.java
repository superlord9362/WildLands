package superlord.wildlands.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import superlord.wildlands.WildLands;
import superlord.wildlands.block.CypressLogBlock;
import superlord.wildlands.block.MudBlock;

public class BlockInit {

	public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, WildLands.MOD_ID);

	public static final RegistryObject<Block> MUD = REGISTER.register("mud", () -> new MudBlock(AbstractBlock.Properties.create(Material.EARTH).sound(SoundType.GROUND).hardnessAndResistance(0.5f).speedFactor(0.2F)));
	public static final RegistryObject<Block> DRIED_MUD = REGISTER.register("dried_mud", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.GROUND).hardnessAndResistance(0.8f)));
	public static final RegistryObject<Block> MUD_BRICKS = REGISTER.register("dried_mud_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.GROUND).hardnessAndResistance(0.8f)));
	public static final RegistryObject<Block> MUD_SHINGLES = REGISTER.register("dried_mud_shingles", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.GROUND).hardnessAndResistance(0.8f)));

	public static final RegistryObject<Block> CYPRESS_LOG = REGISTER.register("cypress_log", () -> new CypressLogBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> STRIPPED_CYPRESS_LOG = REGISTER.register("stripped_cypress_log", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.CLAY));
	public static final RegistryObject<Block> CYPRESS_PLANKS = REGISTER.register("cypress_planks", () -> new Block(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)));
	public static final RegistryObject<Block> CYPRESS_WOOD = REGISTER.register("cypress_wood", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.CLAY));
	public static final RegistryObject<Block> STRIPPED_CYPRESS_WOOD = REGISTER.register("stripped_cypress_wood", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.CLAY));
	public static final RegistryObject<Block> CYPRESS_DOOR = REGISTER.register("cypress_door", () -> new DoorBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(3.0F).notSolid()));
	public static final RegistryObject<Block> CYPRESS_LEAVES = REGISTER.register("cypress_leaves", () -> createLeavesBlock());
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> CYPRESS_STAIRS = REGISTER.register("cypress_stairs", () -> new StairsBlock(BlockInit.CYPRESS_PLANKS.get().getDefaultState(), AbstractBlock.Properties.from(CYPRESS_PLANKS.get())));
	public static final RegistryObject<Block> CYPRESS_PRESSURE_PLATE = REGISTER.register("cypress_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.create(Material.WOOD, CYPRESS_PLANKS.get().getMaterialColor()).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> CYPRESS_FENCE = REGISTER.register("cypress_fence", () -> new FenceBlock(AbstractBlock.Properties.create(Material.WOOD, CYPRESS_PLANKS.get().getMaterialColor()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));   
	public static final RegistryObject<Block> CYPRESS_TRAPDOOR = REGISTER.register("cypress_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid().setAllowsSpawn(BlockInit::neverAllowSpawn)));
	public static final RegistryObject<Block> CYPRESS_FENCE_GATE = REGISTER.register("cypress_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, CYPRESS_PLANKS.get().getMaterialColor()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> CYPRESS_BUTTON = REGISTER.register("cypress_button", () -> new WoodButtonBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> CYPRESS_SLAB = REGISTER.register("cypress_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> WET_CYPRESS_LOG = REGISTER.register("wet_cypress_log", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.CLAY));
	
	private static RotatedPillarBlock createLogBlock(MaterialColor topColor, MaterialColor barkColor) {
		return new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, (state) -> {
			return state.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topColor : barkColor;
		}).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
	}

	private static LeavesBlock createLeavesBlock() {
		return new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid().setAllowsSpawn(BlockInit::allowsSpawnOnLeaves).setSuffocates(BlockInit::isntSolid).setBlocksVision(BlockInit::isntSolid));
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



