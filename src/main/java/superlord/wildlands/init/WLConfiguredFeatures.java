package superlord.wildlands.init;

import java.util.List;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import superlord.wildlands.WildLands;

public class WLConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURES = DeferredRegister.create(Registries.CONFIGURED_FEATURE, WildLands.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_STARFISH = CONFIGURED_FEATURES.register("starfish", () -> new ConfiguredFeature<>(WLFeatures.STARFISH.get(), new CountConfiguration(4)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_URCHIN = CONFIGURED_FEATURES.register("urchin", () -> new ConfiguredFeature<>(WLFeatures.URCHIN.get(), new CountConfiguration(4)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_DISK_MUD = CONFIGURED_FEATURES.register("disk_mud", () -> new ConfiguredFeature<>(Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(WLBlocks.MUD.get()), BlockPredicate.matchesBlocks(List.of(Blocks.SAND, Blocks.DIRT, Blocks.GRAVEL)), UniformInt.of(2, 3), 1)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_DISK_FINE_SAND = CONFIGURED_FEATURES.register("disk_fine_sand", () -> new ConfiguredFeature<>(Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(WLBlocks.FINE_SAND.get()), BlockPredicate.matchesBlocks(List.of(Blocks.SAND, Blocks.GRAVEL, Blocks.DIRT)), UniformInt.of(2, 3), 1)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_DISK_CONGLOMERATE = CONFIGURED_FEATURES.register("disk_conglomerate", () -> new ConfiguredFeature<>(Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(WLBlocks.CONGLOMERATE.get()), BlockPredicate.matchesBlocks(List.of(Blocks.SAND, Blocks.GRAVEL, Blocks.DIRT)), UniformInt.of(2, 3), 1)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_DISK_GABBRO = CONFIGURED_FEATURES.register("disk_gabbro", () -> new ConfiguredFeature<>(Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(WLBlocks.GABBRO.get()), BlockPredicate.matchesBlocks(List.of(Blocks.SAND, Blocks.GRAVEL, Blocks.DIRT)), UniformInt.of(2, 3), 1)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_DISK_DOLERITE = CONFIGURED_FEATURES.register("disk_dolerite", () -> new ConfiguredFeature<>(Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(WLBlocks.DOLERITE.get()), BlockPredicate.matchesBlocks(List.of(Blocks.SAND, Blocks.GRAVEL, Blocks.DIRT)), UniformInt.of(2, 3), 1)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_CONGLOMERATE_ROCK = CONFIGURED_FEATURES.register("conglomerate_rock", () -> new ConfiguredFeature<>(WLFeatures.LARGE_ROCK.get(), new BlockStateConfiguration(WLBlocks.CONGLOMERATE.get().defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_GABBRO_ROCK = CONFIGURED_FEATURES.register("gabbro_rock", () -> new ConfiguredFeature<>(WLFeatures.LARGE_ROCK.get(), new BlockStateConfiguration(WLBlocks.GABBRO.get().defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_DOLERITE_ROCK = CONFIGURED_FEATURES.register("dolerite_rock", () -> new ConfiguredFeature<>(WLFeatures.LARGE_ROCK.get(), new BlockStateConfiguration(WLBlocks.DOLERITE.get().defaultBlockState())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_OLIVINE_GABBRO_ROCK = CONFIGURED_FEATURES.register("olivine_gabbro_rock", () -> new ConfiguredFeature<>(Feature.FOREST_ROCK, new BlockStateConfiguration(WLBlocks.OLIVINE_GABBRO.get().defaultBlockState())));
    
    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_CATTAIL = CONFIGURED_FEATURES.register("cattail", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WLBlocks.CATTAIL.get())))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_PALMETTO = CONFIGURED_FEATURES.register("palmetto", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WLBlocks.PALMETTO.get())))));
   
    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_BURNT_GRASS = CONFIGURED_FEATURES.register("burnt_grass", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WLBlocks.CHARRED_TALL_GRASS.get())))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_BURNT_BUSH = CONFIGURED_FEATURES.register("burnt_bush", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WLBlocks.CHARRED_BUSH.get()))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_DUCKWEED = CONFIGURED_FEATURES.register("duckweed", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(10, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WLBlocks.DUCKWEED.get()))))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> BEARD_MOSS = CONFIGURED_FEATURES.register("beard_moss", () -> new ConfiguredFeature<>(WLFeatures.BEARD_MOSS.get(), new NoneFeatureConfiguration()));
   
    public static final RegistryObject<ConfiguredFeature<?, ?>> CYPRESS_TREE = CONFIGURED_FEATURES.register("configured_cypress_tree", () -> new ConfiguredFeature<>(WLFeatures.CYPRESS_TREE.get(), new NoneFeatureConfiguration()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> COCONUT_TREE = CONFIGURED_FEATURES.register("configured_coconut_tree", () -> new ConfiguredFeature<>(WLFeatures.COCONUT_TREE.get(), new NoneFeatureConfiguration()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CHARRED_TREE = CONFIGURED_FEATURES.register("configured_charred_tree", () -> new ConfiguredFeature<>(WLFeatures.CHARRED_TREE.get(), new NoneFeatureConfiguration()));
    
}
