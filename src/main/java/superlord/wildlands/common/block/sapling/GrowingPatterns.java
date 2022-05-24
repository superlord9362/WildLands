package superlord.wildlands.common.block.sapling;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import java.util.*;


import static superlord.wildlands.WildLands.createLocation;

public record GrowingPatterns(boolean logGrowth, Map<ResourceLocation, List<GrowingPatternEntry>> patternsForBlock) {
    public static final int MAX_PATTERN_SIZE = 5;
    public static final GrowingPatterns DEFAULT = new GrowingPatterns(false, Util.make(new TreeMap<>(), map -> {
    	map.put(createLocation("coconut_sapling"), List.of(
    			new GrowingPatternEntry(List.of("x"), SimpleWeightedRandomList.<FeatureSpawner>builder()
    					.add(new FeatureSpawner(createLocation("coconut_tree_1")), 1)
    					.add(new FeatureSpawner(createLocation("coconut_tree_2")), 1)
    					.add(new FeatureSpawner(createLocation("coconut_tree_3")), 1)
    					.add(new FeatureSpawner(createLocation("coconut_tree_4")), 1)
    					.add(new FeatureSpawner(createLocation("coconut_tree_5")), 1)
    					.add(new FeatureSpawner(createLocation("coconut_tree_6")), 1)
    					.add(new FeatureSpawner(createLocation("coconut_tree_7")), 1)
    					.add(new FeatureSpawner(createLocation("coconut_tree_8")), 1)
    					.add(new FeatureSpawner(createLocation("coconut_tree_9")), 1)
    					.add(new FeatureSpawner(createLocation("coconut_tree_10")), 1)
    					.add(new FeatureSpawner(createLocation("coconut_tree_11")), 1)
    					.add(new FeatureSpawner(createLocation("coconut_tree_12")), 1)
    					.add(new FeatureSpawner(createLocation("coconut_tree_13")), 1)
    					.build()
    					)));
    	map.put(createLocation("cypress_sapling"), List.of(
    			new GrowingPatternEntry(List.of("XX","XX"), SimpleWeightedRandomList.<FeatureSpawner>builder()
    					.add(new FeatureSpawner(createLocation("cypress_tree_1")), 1)
    					.add(new FeatureSpawner(createLocation("cypress_tree_2")), 1)
    					.add(new FeatureSpawner(createLocation("cypress_tree_3")), 1)
    					.build()
    					)));
    }));
    
    public static GrowingPatterns INSTANCE = null;


    public static void setInstance(GrowingPatterns instance) {
        GrowingPatterns.INSTANCE = instance;
    }
    
    public static GrowingPatterns getConfig() {
        return getConfig(false, false);
    }

    public static GrowingPatterns getConfig(boolean serialize, boolean recreate) {
        if (INSTANCE == null || serialize || recreate) {
        }
        return INSTANCE;
    }
    

    public record GrowingPatternEntry(List<String> pattern, SimpleWeightedRandomList<FeatureSpawner> spawners) {
        
    }

    public static final class FeatureSpawner {
        

        private final ResourceLocation spawnerID;
        private final BlockPos spawnOffset;

        public FeatureSpawner(ResourceLocation spawnerID) {
            this(spawnerID, BlockPos.ZERO);
        }

        public FeatureSpawner(Holder<?> spawnerID) {
            this(spawnerID, BlockPos.ZERO);
        }

        public FeatureSpawner(Holder<?> spawnerID, BlockPos spawnOffset) {
            this(spawnerID.unwrapKey().orElseThrow(), spawnOffset);
        }

        public FeatureSpawner(ResourceKey<?> spawnerID) {
            this(spawnerID, BlockPos.ZERO);
        }

        public FeatureSpawner(ResourceKey<?> spawnerID, BlockPos spawnOffset) {
            this(spawnerID.location(), spawnOffset);
        }


        public FeatureSpawner(ResourceLocation spawnerID, BlockPos spawnOffset) {
            this.spawnerID = spawnerID;
            this.spawnOffset = spawnOffset;
        }

        public ResourceLocation spawnerID() {
            return spawnerID;
        }

        public BlockPos spawnOffset() {
            return spawnOffset;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (FeatureSpawner) obj;
            return Objects.equals(this.spawnerID, that.spawnerID) &&
                    Objects.equals(this.spawnOffset, that.spawnOffset);
        }

        @Override
        public int hashCode() {
            return Objects.hash(spawnerID, spawnOffset);
        }

        @Override
        public String toString() {
            return "Spawner[" +
                    "spawnerID=" + spawnerID + ", " +
                    "spawnOffset=" + spawnOffset + ']';
        }


    }

}
