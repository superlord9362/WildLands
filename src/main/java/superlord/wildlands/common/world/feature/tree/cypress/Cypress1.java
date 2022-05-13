package superlord.wildlands.common.world.feature.tree.cypress;

import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import superlord.wildlands.common.world.feature.config.WLTreeConfig;
import superlord.wildlands.common.world.feature.util.WLAbstractTreeFeature;

public class Cypress1 extends WLAbstractTreeFeature<WLTreeConfig> {

	public Cypress1(Codec<WLTreeConfig> configIn) {
		super(configIn);
	}

	protected boolean generate(Set<BlockPos> changedBlocks, WorldGenLevel  world, Random rand, BlockPos pos, BoundingBox  boundsIn, boolean isSapling, WLTreeConfig config) {

		int randTreeHeight = 8 /*pos, config.getMinHeight() + rand.nextInt(pos, config.getMaxPossibleHeight())*/;
		BlockPos.MutableBlockPos  mainmutable = new BlockPos.MutableBlockPos ().set(pos);

		if (pos.getY() + randTreeHeight + 1 < world.getHeight()) {
			if (!isDesiredGroundwDirtTag(world, pos.below(), config)) {
				return false;
			} else if (!this.isAnotherTreeNearby(world, pos, randTreeHeight, 0, isSapling)) {
				return false;
			} else if (!this.doesSaplingHaveSpaceToGrow(world, pos, randTreeHeight, 5, 5, 5, isSapling)) {
				return false;
			} else {
				mainmutable.set(pos);

				placeTrunk(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, 7, 0), boundsIn);
				placeTrunk(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, 11, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 8, -1), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 8, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight - 8, -1), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight - 8, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 7, -1), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 7, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight - 7, -1), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight - 7, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 6, -1), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 6, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight - 6, -1), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight - 6, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 5, -1), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 5, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight - 5, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 4, -1), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 4, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 4, 1), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight - 4, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 3, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 2, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 1, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 1, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 2, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 3, -1), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 3, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 3, 1), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 3, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight + 4, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 4, -2), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 4, 2), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(3, randTreeHeight + 4, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-2, randTreeHeight + 5, 0), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 5, -3), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 5, 3), boundsIn);
				placeBranch(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(4, randTreeHeight + 5, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight - 4, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 4, 2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight - 4, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight - 3, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight - 1, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight - 1, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight - 1, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-3, randTreeHeight + 5, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-3, randTreeHeight + 5, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-3, randTreeHeight + 5, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-2, randTreeHeight + 5, -2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-2, randTreeHeight + 5, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-2, randTreeHeight + 5, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-2, randTreeHeight + 5, 2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight + 5, -3), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight + 5, -2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight + 5, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight + 5, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight + 5, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight + 5, 2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight + 5, 3), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 5, -4), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 5, -3), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 5, -2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 5, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 5, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 5, 2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 5, 3), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 5, 4), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 5, -4), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 5, -2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 5, 2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 5, 4), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 5, -4), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 5, -3), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 5, -2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 5, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 5, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 5, 2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 5, 3), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 5, 4), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(3, randTreeHeight + 5, -3), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(3, randTreeHeight + 5, -2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(3, randTreeHeight + 5, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(3, randTreeHeight + 5, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(3, randTreeHeight + 5, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(3, randTreeHeight + 5, 2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(3, randTreeHeight + 5, 3), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(4, randTreeHeight + 5, -2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(4, randTreeHeight + 5, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(4, randTreeHeight + 5, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(4, randTreeHeight + 5, 2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(5, randTreeHeight + 5, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(5, randTreeHeight + 5, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(5, randTreeHeight + 5, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-2, randTreeHeight + 6, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-2, randTreeHeight + 6, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-2, randTreeHeight + 6, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight + 6, -2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight + 6, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight + 6, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight + 6, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight + 6, 2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 6, -3), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 6, -2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 6, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 6, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 6, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 6, 2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 6, 3), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 6, -3), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 6, -2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 6, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 6, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 6, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 6, 2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 6, 3), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 6, -3), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 6, -2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 6, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 6, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 6, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 6, 2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 6, 3), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(3, randTreeHeight + 6, -2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(3, randTreeHeight + 6, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(3, randTreeHeight + 6, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(3, randTreeHeight + 6, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(3, randTreeHeight + 6, 2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(4, randTreeHeight + 6, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(4, randTreeHeight + 6, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(4, randTreeHeight + 6, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight + 7, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight + 7, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(-1, randTreeHeight + 7, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 7, -2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 7, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 7, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 7, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(0, randTreeHeight + 7, 2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 7, -2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 7, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 7, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 7, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(1, randTreeHeight + 7, 2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 7, -2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 7, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 7, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 7, 1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(2, randTreeHeight + 7, 2), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(3, randTreeHeight + 7, -1), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(3, randTreeHeight + 7, 0), boundsIn);
				placeLeaves(pos, config, rand, changedBlocks, world, mainmutable.set(pos).move(3, randTreeHeight + 7, 1), boundsIn);

			}
		}
		return true;
	}
}