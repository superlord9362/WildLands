package superlord.wildlands.world.feature.tree.cypress;

import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import superlord.wildlands.world.feature.config.WLTreeConfig;
import superlord.wildlands.world.feature.util.WLAbstractTreeFeature;

public class Cypress1 extends WLAbstractTreeFeature<WLTreeConfig> {

	public Cypress1(Codec<WLTreeConfig> configIn) {
		super(configIn);
	}

	protected boolean generate(Set<BlockPos> changedBlocks, ISeedReader world, Random rand, BlockPos pos, MutableBoundingBox boundsIn, boolean isSapling, WLTreeConfig config) {

		int randTreeHeight = 8 /*config.getMinHeight() + rand.nextInt(config.getMaxPossibleHeight())*/;
		BlockPos.Mutable mainmutable = new BlockPos.Mutable().setPos(pos);

		if (pos.getY() + randTreeHeight + 1 < world.getHeight()) {
			if (!isDesiredGroundwDirtTag(world, pos.down(), config)) {
				return false;
			} else if (!this.isAnotherTreeNearby(world, pos, randTreeHeight, 0, isSapling)) {
				return false;
			} else if (!this.doesSaplingHaveSpaceToGrow(world, pos, randTreeHeight, 7, 5, 5, isSapling)) {
				return false;
			} else {
				buildTrunkBase(pos, changedBlocks, world, config, rand, boundsIn, mainmutable.setPos(pos).move(1, 0, -1).toImmutable(), mainmutable.setPos(pos).move(1, 0, 0).toImmutable(), mainmutable.setPos(pos).move(2, 0, -1).toImmutable(), mainmutable.setPos(pos).move(2, 0, 0).toImmutable());

				mainmutable.setPos(pos);

				placeTrunk(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, 7, 0), boundsIn);
				placeTrunk(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, 11, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight - 8, -1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight - 8, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight - 8, -1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight - 8, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight - 7, -1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight - 7, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight - 7, -1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight - 7, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight - 6, -1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight - 6, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight - 6, -1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight - 6, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight - 5, -1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight - 5, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight - 5, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight - 4, -1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight - 4, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight - 4, 1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight - 4, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight - 3, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight - 2, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight - 1, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 1, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 2, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 3, -1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 3, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 3, 1), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 3, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 4, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 4, -2), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 4, 2), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(3, randTreeHeight + 4, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-2, randTreeHeight + 5, 0), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 5, -3), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 5, 3), boundsIn);
				placeBranch(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(4, randTreeHeight + 5, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight - 4, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight - 4, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight - 4, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight - 3, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight - 1, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight - 1, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight - 1, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-3, randTreeHeight + 5, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-3, randTreeHeight + 5, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-3, randTreeHeight + 5, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-2, randTreeHeight + 5, -2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-2, randTreeHeight + 5, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-2, randTreeHeight + 5, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-2, randTreeHeight + 5, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 5, -3), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 5, -2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 5, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 5, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 5, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 5, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 5, 3), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 5, -4), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 5, -3), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 5, -2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 5, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 5, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 5, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 5, 3), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 5, 4), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 5, -4), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 5, -2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 5, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 5, 4), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 5, -4), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 5, -3), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 5, -2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 5, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 5, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 5, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 5, 3), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 5, 4), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(3, randTreeHeight + 5, -3), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(3, randTreeHeight + 5, -2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(3, randTreeHeight + 5, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(3, randTreeHeight + 5, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(3, randTreeHeight + 5, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(3, randTreeHeight + 5, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(3, randTreeHeight + 5, 3), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(4, randTreeHeight + 5, -2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(4, randTreeHeight + 5, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(4, randTreeHeight + 5, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(4, randTreeHeight + 5, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(5, randTreeHeight + 5, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(5, randTreeHeight + 5, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(5, randTreeHeight + 5, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-2, randTreeHeight + 6, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-2, randTreeHeight + 6, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-2, randTreeHeight + 6, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 6, -2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 6, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 6, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 6, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 6, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 6, -3), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 6, -2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 6, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 6, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 6, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 6, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 6, 3), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 6, -3), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 6, -2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 6, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 6, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 6, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 6, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 6, 3), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 6, -3), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 6, -2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 6, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 6, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 6, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 6, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 6, 3), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(3, randTreeHeight + 6, -2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(3, randTreeHeight + 6, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(3, randTreeHeight + 6, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(3, randTreeHeight + 6, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(3, randTreeHeight + 6, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(4, randTreeHeight + 6, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(4, randTreeHeight + 6, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(4, randTreeHeight + 6, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 7, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 7, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(-1, randTreeHeight + 7, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 7, -2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 7, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 7, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 7, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(0, randTreeHeight + 7, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 7, -2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 7, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 7, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 7, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(1, randTreeHeight + 7, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 7, -2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 7, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 7, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 7, 1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(2, randTreeHeight + 7, 2), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(3, randTreeHeight + 7, -1), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(3, randTreeHeight + 7, 0), boundsIn);
				placeLeaves(config, rand, changedBlocks, world, mainmutable.setPos(pos).move(3, randTreeHeight + 7, 1), boundsIn);

			}
		}
		return true;
	}
}