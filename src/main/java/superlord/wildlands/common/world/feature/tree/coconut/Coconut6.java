package superlord.wildlands.common.world.feature.tree.coconut;

import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import superlord.wildlands.common.world.feature.config.WLTreeConfig;
import superlord.wildlands.common.world.feature.util.WLAbstractTreeFeature;

public class Coconut6 extends WLAbstractTreeFeature<WLTreeConfig> {

    public Coconut6(Codec<WLTreeConfig> configIn) {
        super(configIn);
    }

    protected boolean generate(Set<BlockPos> changedBlocks, WorldGenLevel world, Random rand, BlockPos pos, BoundingBox boundsIn, boolean isSapling, WLTreeConfig config) {

        int randTreeHeight = config.getMinHeight() + rand.nextInt(config.getMaxPossibleHeight());
        BlockPos.MutableBlockPos mainmutable = new BlockPos.MutableBlockPos().set(pos);

        if (pos.getY() + randTreeHeight + 1 < world.getHeight()) {
            if (!isDesiredGroundwSandTag(world, pos.below(), config)) {
                return false;
            } else if (!this.isAnotherTreeNearby(world, pos, randTreeHeight, 0, isSapling)) {
                return false;
            } else if (!this.doesSaplingHaveSpaceToGrow(world, pos, randTreeHeight, 7, 5, 5, isSapling)) {
                return false;
            } else {
            	for (int buildTrunk = 0; buildTrunk <= randTreeHeight; buildTrunk++) {
					mainmutable.move(Direction.UP);
				}
				
            	mainmutable.set(pos);
				
				placeTrunk(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, 0, 0), boundsIn);
				placeTrunk(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, 1, 0), boundsIn);
				placeTrunk(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, 2, 0), boundsIn);
				placeTrunk(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, 3, 0), boundsIn);
				placeTrunk(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, 4, 0), boundsIn);
				placeTrunk(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, 5, 0), boundsIn);
				placeTrunk(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, 9, 0), boundsIn);
				placeTrunk(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, 10, 0), boundsIn);
				placeTrunk(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, 11, 0), boundsIn);
				placeTrunk(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, 12, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 7, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 6, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 5, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 4, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 3, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-4, randTreeHeight - 2, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight - 2, -4), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight - 2, 4), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(4, randTreeHeight - 2, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-4, randTreeHeight - 1, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-3, randTreeHeight - 1, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight - 1, -4), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight - 1, -3), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight - 1, 3), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight - 1, 4), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(3, randTreeHeight - 1, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(4, randTreeHeight - 1, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-3, randTreeHeight, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-2, randTreeHeight, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight, -3), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight, -2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight, 2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight, 3), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(3, randTreeHeight, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight + 1, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 1, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 1, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 1, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 1, 0), boundsIn);
				placeExtra(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, 10, -1), boundsIn);
				placeExtraWest(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, 9, 0), boundsIn);
				placeExtraSouth(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, 11, 1), boundsIn);
            }
        }
        return true;
    }
}