package superlord.wildlands.common.world.feature.config;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.treedecorator.TreeDecorator;

public class WLTreeConfig implements IFeatureConfig {

	public static final Codec<WLTreeConfig> CODEC = RecordCodecBuilder.create((codecRecorder) -> {
		return codecRecorder.group(BlockStateProvider.CODEC.fieldOf("trunk_provider").orElse(new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState())).forGetter((config) -> {
			return config.trunkProvider;
		}), BlockStateProvider.CODEC.fieldOf("leaves_provider").orElse(new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState())).forGetter((config) -> {
			return config.leavesProvider;
		}), BlockStateProvider.CODEC.fieldOf("extra_provider").orElse(new SimpleBlockStateProvider(Blocks.AIR.getDefaultState())).forGetter((config) -> {
			return config.getExtraProvider();
		}), BlockStateProvider.CODEC.fieldOf("extra_provider_east").orElse(new SimpleBlockStateProvider(Blocks.AIR.getDefaultState())).forGetter((config) -> {
			return config.getExtraProviderEast();
		}), BlockStateProvider.CODEC.fieldOf("extra_provider_south").orElse(new SimpleBlockStateProvider(Blocks.AIR.getDefaultState())).forGetter((config) -> {
			return config.getExtraProviderSouth();
		}), BlockStateProvider.CODEC.fieldOf("extra_provider_west").orElse(new SimpleBlockStateProvider(Blocks.AIR.getDefaultState())).forGetter((config) -> {
			return config.getExtraProviderWest();
		}), TreeDecorator.field_236874_c_.listOf().fieldOf("decorators").forGetter((p_236688_0_) -> {
			return p_236688_0_.decorators;
		}), BlockStateProvider.CODEC.fieldOf("ground_replacement_provider").orElse(new SimpleBlockStateProvider(Blocks.DIRT.getDefaultState())).forGetter((config) -> {
			return config.groundReplacementProvider;//TODO: Remove Ground Replacement Provider
		}), BlockStateProvider.CODEC.fieldOf("disk_provider").orElse(new SimpleBlockStateProvider(Blocks.PODZOL.getDefaultState())).forGetter((config) -> {
			return config.diskProvider;
		}), Codec.INT.fieldOf("min_height").orElse(15).forGetter((config) -> {
			return config.minHeight;
		}), Codec.INT.fieldOf("max_height").orElse(15).forGetter((config) -> {
			return config.maxHeight;
		}), Codec.INT.fieldOf("disk_radius").orElse(0).forGetter((config) -> {
			return config.diskRadius;
		}), BlockState.CODEC.listOf().fieldOf("whitelist").forGetter((config) -> {
			return config.whitelist.stream().map(Block::getDefaultState).collect(Collectors.toList());
		})).apply(codecRecorder, WLTreeConfig::new);
	});


	private final BlockStateProvider trunkProvider;
	private final BlockStateProvider leavesProvider;
	private final BlockStateProvider extraProvider;
	private final BlockStateProvider extraProviderEast;
	private final BlockStateProvider extraProviderSouth;
	private final BlockStateProvider extraProviderWest;
	public final List<TreeDecorator> decorators;
	@Deprecated
	private final BlockStateProvider groundReplacementProvider;
	private final BlockStateProvider diskProvider;
	private final int minHeight;
	private final int maxHeight;
	private final int diskRadius;
	private final Set<Block> whitelist;
	private boolean forcedPlacement = false;

	WLTreeConfig(BlockStateProvider trunkProvider, BlockStateProvider leavesProvider, BlockStateProvider extraProvider, BlockStateProvider extraProviderEast, BlockStateProvider extraProviderSouth, BlockStateProvider extraProviderWest, List<TreeDecorator> decorators,  BlockStateProvider groundReplacementProvider, BlockStateProvider diskProvider, int minHeight, int maxHeight, int diskRadius, List<BlockState> whitelist) {
		this.trunkProvider = trunkProvider;
		this.leavesProvider = leavesProvider;
		this.extraProvider = extraProvider;
		this.extraProviderEast = extraProviderEast;
		this.extraProviderSouth = extraProviderSouth;
		this.extraProviderWest = extraProviderWest;
		this.decorators = decorators;
		this.groundReplacementProvider = groundReplacementProvider;
		this.diskProvider = diskProvider;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
		this.diskRadius = diskRadius;
		this.whitelist = whitelist.stream().map(AbstractBlock.AbstractBlockState::getBlock).collect(Collectors.toSet());
	}

	/**
	 * Used to generate trees from saplings
	 */
	public void forcePlacement() {
		forcedPlacement = true;
	}


	public BlockStateProvider getTrunkProvider() {
		return this.trunkProvider;
	}

	public BlockStateProvider getLeavesProvider() {
		return this.leavesProvider;
	}

	public BlockStateProvider getExtraProvider() {
		return this.extraProvider;
	}

	public BlockStateProvider getExtraProviderEast() {
		return this.extraProviderEast;
	}

	public BlockStateProvider getExtraProviderSouth() {
		return this.extraProviderSouth;
	}

	public BlockStateProvider getExtraProviderWest() {
		return this.extraProviderWest;
	}
	
	public List<TreeDecorator> getDecorators() {
		return this.decorators;
	}

	@Deprecated
	public BlockStateProvider getGroundReplacementProvider() {
		return groundReplacementProvider;
	}

	public BlockStateProvider getDiskProvider() {
		return this.diskProvider;
	}

	public int getMinHeight() {
		return minHeight;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public int getDiskRadius() {
		return diskRadius;
	}

	public Set<Block> getWhitelist() {
		return whitelist;
	}

	public int getMaxPossibleHeight() {
		int returnValue = this.maxHeight - minHeight;
		if (returnValue <= 0)
			returnValue = 1;

		return returnValue;
	}

	public boolean isPlacementForced() {
		return forcedPlacement;
	}

	public static class Builder {
		private BlockStateProvider trunkProvider = new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState());
		private BlockStateProvider leavesProvider = new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState());
		private BlockStateProvider extraProvider = new SimpleBlockStateProvider(Blocks.AIR.getDefaultState());
		private BlockStateProvider extraProviderEast = new SimpleBlockStateProvider(Blocks.AIR.getDefaultState());
		private BlockStateProvider extraProviderSouth = new SimpleBlockStateProvider(Blocks.AIR.getDefaultState());
		private BlockStateProvider extraProviderWest = new SimpleBlockStateProvider(Blocks.AIR.getDefaultState());
		@Deprecated private BlockStateProvider groundReplacementProvider = new SimpleBlockStateProvider(Blocks.DIRT.getDefaultState());
		private BlockStateProvider diskProvider = new SimpleBlockStateProvider(Blocks.PODZOL.getDefaultState());
		private List<Block> whitelist = ImmutableList.of(Blocks.GRASS_BLOCK);
		private List<TreeDecorator> decorators = ImmutableList.of();
		private int minHeight = 15;
		private int maxPossibleHeight = 1;
		private int diskRadius = 0;

		public Builder setTrunkBlock(Block block) {
			if (block != null)
				trunkProvider = new SimpleBlockStateProvider(block.getDefaultState());
			else
				trunkProvider = new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState());

			return this;
		}

		public WLTreeConfig.Builder setDecorators(List<TreeDecorator> decorators) {
			this.decorators = decorators;
			return this;
		}

		public Builder setTrunkBlock(BlockState state) {
			if (state != null)
				trunkProvider = new SimpleBlockStateProvider(state);
			else
				trunkProvider = new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState());

			return this;
		}

		public Builder setTrunkBlock(BlockStateProvider stateProvider) {
			if (stateProvider != null)
				trunkProvider = stateProvider;
			else
				trunkProvider = new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState());

			return this;
		}

		public Builder setLeavesBlock(Block block) {
			if (block != null)
				leavesProvider = new SimpleBlockStateProvider(block.getDefaultState());
			else
				leavesProvider = new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState());

			return this;
		}

		public Builder setLeavesBlock(BlockState state) {
			if (state != null)
				leavesProvider = new SimpleBlockStateProvider(state);
			else
				leavesProvider = new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState());

			return this;
		}

		public Builder setLeavesBlock(BlockStateProvider stateProvider) {
			if (stateProvider != null)
				leavesProvider = stateProvider;
			else
				leavesProvider = new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState());

			return this;
		}

		public Builder setExtraBlock(Block block) {
			if (block != null)
				extraProvider = new SimpleBlockStateProvider(block.getDefaultState());
			else
				extraProvider = new SimpleBlockStateProvider(Blocks.AIR.getDefaultState());

			return this;
		}

		public Builder setExtraBlock(BlockState state) {
			if (state != null)
				extraProvider = new SimpleBlockStateProvider(state);
			else
				extraProvider = new SimpleBlockStateProvider(Blocks.AIR.getDefaultState());

			return this;
		}

		public Builder setExtraBlock(BlockStateProvider stateProvider) {
			if (stateProvider != null)
				extraProvider = stateProvider;
			else
				extraProvider = new SimpleBlockStateProvider(Blocks.AIR.getDefaultState());

			return this;
		}

		public Builder setExtraBlockEast(Block block) {
			if (block != null)
				extraProviderEast = new SimpleBlockStateProvider(block.getDefaultState());
			else
				extraProviderEast = new SimpleBlockStateProvider(Blocks.AIR.getDefaultState());

			return this;
		}

		public Builder setExtraBlockEast(BlockState state) {
			if (state != null)
				extraProviderEast = new SimpleBlockStateProvider(state);
			else
				extraProviderEast = new SimpleBlockStateProvider(Blocks.AIR.getDefaultState());

			return this;
		}

		public Builder setExtraBlockEast(BlockStateProvider stateProvider) {
			if (stateProvider != null)
				extraProviderEast = stateProvider;
			else
				extraProviderEast = new SimpleBlockStateProvider(Blocks.AIR.getDefaultState());

			return this;
		}

		public Builder setExtraBlockWest(Block block) {
			if (block != null)
				extraProviderWest = new SimpleBlockStateProvider(block.getDefaultState());
			else
				extraProviderWest = new SimpleBlockStateProvider(Blocks.AIR.getDefaultState());

			return this;
		}

		public Builder setExtraBlockWest(BlockState state) {
			if (state != null)
				extraProviderWest = new SimpleBlockStateProvider(state);
			else
				extraProviderWest = new SimpleBlockStateProvider(Blocks.AIR.getDefaultState());

			return this;
		}

		public Builder setExtraBlocWest(BlockStateProvider stateProvider) {
			if (stateProvider != null)
				extraProviderWest = stateProvider;
			else
				extraProviderWest = new SimpleBlockStateProvider(Blocks.AIR.getDefaultState());

			return this;
		}

		public Builder setExtraBlockSouth(Block block) {
			if (block != null)
				extraProviderSouth = new SimpleBlockStateProvider(block.getDefaultState());
			else
				extraProviderSouth = new SimpleBlockStateProvider(Blocks.AIR.getDefaultState());

			return this;
		}

		public Builder setExtraBlockSouth(BlockState state) {
			if (state != null)
				extraProviderSouth = new SimpleBlockStateProvider(state);
			else
				extraProviderSouth = new SimpleBlockStateProvider(Blocks.AIR.getDefaultState());

			return this;
		}

		public Builder setExtraBlockSouth(BlockStateProvider stateProvider) {
			if (stateProvider != null)
				extraProviderSouth = stateProvider;
			else
				extraProviderSouth = new SimpleBlockStateProvider(Blocks.AIR.getDefaultState());

			return this;
		}

		@Deprecated
		public Builder setGroundReplacementBlock(Block block) {
			if (block != null)
				groundReplacementProvider = new SimpleBlockStateProvider(block.getDefaultState());
			else
				groundReplacementProvider = new SimpleBlockStateProvider(Blocks.DIRT.getDefaultState());

			return this;
		}

		@Deprecated
		public Builder setGroundReplacementBlock(BlockState state) {
			if (state != null)
				groundReplacementProvider = new SimpleBlockStateProvider(state);
			else
				groundReplacementProvider = new SimpleBlockStateProvider(Blocks.AIR.getDefaultState());

			return this;
		}

		@Deprecated
		public Builder setGroundReplacementBlock(BlockStateProvider stateProvider) {
			if (stateProvider != null)
				groundReplacementProvider = stateProvider;
			else
				groundReplacementProvider = new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState());

			return this;
		}


		public Builder setDiskBlock(Block block) {
			if (block != null)
				diskProvider = new SimpleBlockStateProvider(block.getDefaultState());
			else
				diskProvider = new SimpleBlockStateProvider(Blocks.AIR.getDefaultState());

			return this;
		}

		public Builder setDiskBlock(BlockState state) {
			if (state != null)
				diskProvider = new SimpleBlockStateProvider(state);
			else
				diskProvider = new SimpleBlockStateProvider(Blocks.AIR.getDefaultState());

			return this;
		}

		public Builder setDiskBlock(BlockStateProvider stateProvider) {
			if (stateProvider != null)
				diskProvider = stateProvider;
			else
				diskProvider = new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState());

			return this;
		}

		public Builder setMinHeight(int minHeight) {
			this.minHeight = minHeight;
			return this;
		}

		public Builder setMaxHeight(int maxPossibleHeight) {
			if (maxPossibleHeight != 0)
				this.maxPossibleHeight = maxPossibleHeight + 1;
			else
				this.maxPossibleHeight = 1;
			return this;
		}

		public Builder setDiskRadius(int diskRadius) {
			this.diskRadius = Math.abs(diskRadius);
			return this;
		}

		public Builder setWhitelist(ImmutableList<Block> whitelist) {
			this.whitelist = whitelist;
			return this;
		}

		public Builder copy(WLTreeConfig config) {
			this.trunkProvider = config.trunkProvider;
			this.leavesProvider = config.leavesProvider;
			this.extraProvider = config.extraProvider;
			this.extraProviderEast = config.extraProviderEast;
			this.extraProviderSouth = config.extraProviderSouth;
			this.extraProviderWest = config.extraProviderWest;
			this.groundReplacementProvider = config.groundReplacementProvider;
			this.diskProvider = config.diskProvider;
			this.maxPossibleHeight = config.maxHeight;
			this.minHeight = config.minHeight;
			this.diskRadius = config.diskRadius;
			this.whitelist = ImmutableList.copyOf(config.whitelist);
			return this;
		}

		public WLTreeConfig build() {
			return new WLTreeConfig(this.trunkProvider, this.leavesProvider, this.extraProvider, this.extraProviderEast, this.extraProviderSouth, this.extraProviderWest, this.decorators, this.groundReplacementProvider, this.diskProvider, this.minHeight, this.maxPossibleHeight, this.diskRadius, this.whitelist.stream().map(Block::getDefaultState).collect(Collectors.toList()));
		}
	}
}
