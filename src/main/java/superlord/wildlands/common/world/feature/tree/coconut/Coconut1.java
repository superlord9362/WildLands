package superlord.wildlands.common.world.feature.tree.coconut;

import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import superlord.wildlands.common.world.feature.config.WLTreeConfig;
import superlord.wildlands.common.world.feature.util.WLAbstractTreeFeature;

public class Coconut1 extends WLAbstractTreeFeature<WLTreeConfig> {

    public Coconut1(Codec<WLTreeConfig> configIn) {
        super(configIn);
    }

	protected boolean generate(Set<BlockPos> changedBlocks, ISeedReader world, Random rand, BlockPos pos, MutableBoundingBox boundsIn, boolean isSapling, WLTreeConfig config) {

        int randTreeHeight = config.getMinHeight() + rand.nextInt(config.getMaxPossibleHeight());
        BlockPos.Mutable mainmutable = new BlockPos.Mutable().setPos(pos);

        if (pos.getY() + randTreeHeight + 1 < world.getHeight()) {
            if (!isDesiredGroundwSandTag(world, pos.down(), config)) {
                return false;
            } else if (!this.isAnotherTreeNearby(world, pos, randTreeHeight, 0, isSapling)) {
                return false;
            } else if (!this.doesSaplingHaveSpaceToGrow(world, pos, randTreeHeight, 7, 5, 5, isSapling)) {
                return false;
            } else {
            	buildTrunkBase(pos, changedBlocks, world, config, rand, boundsIn, mainmutable.setPos(pos).move(0, 0, 0).toImmutable());
            	for (int buildTrunk = 0; buildTrunk <= randTreeHeight; buildTrunk++) {
					placeTrunk(config, rand, changedBlocks, world, mainmutable, boundsIn);
					mainmutable.move(Direction.UP);
				}
				
            	mainmutable.setPos(pos);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 1, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 2, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 3, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 4, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 4, 1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 5, 1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 6, 1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 7, 1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 8, 1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 9, 1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 10, 1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 10, 1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 11, 1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 12, 1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 13, 1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 14, 1), boundsIn);
				placeExtra(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight+14, 0), boundsIn);
				placeExtraEast(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight+13, 1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 15, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-4, randTreeHeight + 13, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 13, -3), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 13, 5), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(4, randTreeHeight + 13, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-4, randTreeHeight + 14, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-3, randTreeHeight + 14, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 14, -3), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 14, -2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 14, 4), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 14, 5), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(3, randTreeHeight + 14, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(4, randTreeHeight + 14, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-3, randTreeHeight + 15, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-2, randTreeHeight + 15, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 15, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 15, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 15, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 15, -2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 15, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 15, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 15, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 15, 3), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 15, 4), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 15, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 15, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 15, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 15, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(3, randTreeHeight + 15, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 16, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 16, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 16, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 16, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 16, 1), boundsIn);
            }
        }
        return true;
    }
}