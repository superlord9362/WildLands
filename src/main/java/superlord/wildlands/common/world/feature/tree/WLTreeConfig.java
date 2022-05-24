package superlord.wildlands.common.world.feature.tree;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

public class WLTreeConfig implements FeatureConfiguration {

	    public static final Codec<WLTreeConfig> CODEC = RecordCodecBuilder.create((codecRecorder) -> {
	        return codecRecorder.group(BlockStateProvider.CODEC.fieldOf("trunk_provider").orElse(SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState())).forGetter((config) -> {
	            return config.trunkProvider;
	        }), BlockStateProvider.CODEC.fieldOf("leaves_provider").orElse(SimpleStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState())).forGetter((config) -> {
	            return config.leavesProvider;
	        }), BlockStateProvider.CODEC.fieldOf("extra_provider").orElse(SimpleStateProvider.simple(Blocks.AIR.defaultBlockState())).forGetter((config) -> {
				return config.getExtraProvider();
			}), BlockStateProvider.CODEC.fieldOf("extra_provider_east").orElse(SimpleStateProvider.simple(Blocks.AIR.defaultBlockState())).forGetter((config) -> {
				return config.getExtraProviderEast();
			}), BlockStateProvider.CODEC.fieldOf("extra_provider_south").orElse(SimpleStateProvider.simple(Blocks.AIR.defaultBlockState())).forGetter((config) -> {
				return config.getExtraProviderSouth();
			}), BlockStateProvider.CODEC.fieldOf("extra_provider_west").orElse(SimpleStateProvider.simple(Blocks.AIR.defaultBlockState())).forGetter((config) -> {
				return config.getExtraProviderWest();
			}), TreeDecorator.CODEC.listOf().fieldOf("decorators").forGetter((p_236688_0_) -> {
				return p_236688_0_.decorators;
			}), BlockStateProvider.CODEC.fieldOf("disk_provider").orElse(SimpleStateProvider.simple(Blocks.PODZOL.defaultBlockState())).forGetter((config) -> {
	            return config.diskProvider;
	        }), Codec.INT.fieldOf("min_height").orElse(15).forGetter((config) -> {
	            return config.minHeight;
	        }), Codec.INT.fieldOf("max_height").orElse(15).forGetter((config) -> {
	            return config.maxHeight;
	        }), Codec.INT.fieldOf("disk_radius").orElse(0).forGetter((config) -> {
	            return config.diskRadius;
	        }), BlockState.CODEC.listOf().fieldOf("whitelist").forGetter((config) -> {
	            return config.whitelist.stream().map(Block::defaultBlockState).collect(Collectors.toList());
	        })).apply(codecRecorder, WLTreeConfig::new);
	    });


	    private final BlockStateProvider trunkProvider;
	    private final BlockStateProvider leavesProvider;
		private final BlockStateProvider extraProvider;
		private final BlockStateProvider extraProviderEast;
		private final BlockStateProvider extraProviderSouth;
		private final BlockStateProvider extraProviderWest;
		public final List<TreeDecorator> decorators;
	    private final BlockStateProvider diskProvider;
	    private final int minHeight;
	    private final int maxHeight;
	    private final int diskRadius;
	    private final Set<Block> whitelist;

		WLTreeConfig(BlockStateProvider trunkProvider, BlockStateProvider leavesProvider, BlockStateProvider extraProvider, BlockStateProvider extraProviderEast, BlockStateProvider extraProviderSouth, BlockStateProvider extraProviderWest, List<TreeDecorator> decorators, BlockStateProvider diskProvider, int minHeight, int maxHeight, int diskRadius, List<BlockState> whitelist) {
	        this.trunkProvider = trunkProvider;
	        this.leavesProvider = leavesProvider;
			this.extraProvider = extraProvider;
			this.extraProviderEast = extraProviderEast;
			this.extraProviderSouth = extraProviderSouth;
			this.extraProviderWest = extraProviderWest;
			this.decorators = decorators;
	        this.diskProvider = diskProvider;
	        this.minHeight = minHeight;
	        this.maxHeight = maxHeight;
	        this.diskRadius = diskRadius;
	        this.whitelist = whitelist.stream().map(BlockBehaviour.BlockStateBase::getBlock).collect(Collectors.toSet());
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

	    private Rotation rotation = Rotation.NONE;
	    private Mirror mirror = Mirror.NONE;

	    public void setRotationAndMirror(Rotation rotation, Mirror mirror) {
	        this.rotation = rotation;
	        this.mirror = mirror;
	    }

	    public Rotation getRotation() {
	        return rotation;
	    }

	    public Mirror getMirror() {
	        return mirror;
	    }


	    public static class Builder {
	        private BlockStateProvider trunkProvider = SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState());
	        private BlockStateProvider leavesProvider = SimpleStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState());
			private BlockStateProvider extraProvider = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());
			private BlockStateProvider extraProviderEast = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());
			private BlockStateProvider extraProviderSouth = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());
			private BlockStateProvider extraProviderWest = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());
	        @SuppressWarnings("unused")
			@Deprecated
	        private BlockStateProvider groundReplacementProvider = SimpleStateProvider.simple(Blocks.DIRT.defaultBlockState());
	        private BlockStateProvider diskProvider = SimpleStateProvider.simple(Blocks.PODZOL.defaultBlockState());
	        private List<Block> whitelist = ImmutableList.of(Blocks.GRASS_BLOCK);
			private List<TreeDecorator> decorators = ImmutableList.of();
	        private int minHeight = 15;
	        private int maxPossibleHeight = 1;
	        private int diskRadius = 0;

	        public Builder setTrunkBlock(Block block) {
	            if (block != null)
	                trunkProvider = SimpleStateProvider.simple(block.defaultBlockState());
	            else
	                trunkProvider = SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState());

	            return this;
	        }

	        public Builder setTrunkBlock(BlockState state) {
	            if (state != null)
	                trunkProvider = SimpleStateProvider.simple(state);
	            else
	                trunkProvider = SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState());

	            return this;
	        }

	        public Builder setTrunkBlock(BlockStateProvider stateProvider) {
	            if (stateProvider != null)
	                trunkProvider = stateProvider;
	            else
	                trunkProvider = SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState());

	            return this;
	        }

	        public Builder setLeavesBlock(Block block) {
	            if (block != null)
	                leavesProvider = SimpleStateProvider.simple(block.defaultBlockState());
	            else
	                leavesProvider = SimpleStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState());

	            return this;
	        }

	        public Builder setLeavesBlock(BlockState state) {
	            if (state != null)
	                leavesProvider = SimpleStateProvider.simple(state);
	            else
	                leavesProvider = SimpleStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState());

	            return this;
	        }

	        public Builder setLeavesBlock(BlockStateProvider stateProvider) {
	            if (stateProvider != null)
	                leavesProvider = stateProvider;
	            else
	                leavesProvider = SimpleStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState());

	            return this;
	        }
	        
	        public Builder setExtraBlock(Block block) {
				if (block != null)
					extraProvider = SimpleStateProvider.simple(block.defaultBlockState());
				else
					extraProvider = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());

				return this;
			}

			public Builder setExtraBlock(BlockState state) {
				if (state != null)
					extraProvider = SimpleStateProvider.simple(state);
				else
					extraProvider = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());

				return this;
			}

			public Builder setExtraBlock(BlockStateProvider stateProvider) {
				if (stateProvider != null)
					extraProvider = stateProvider;
				else
					extraProvider = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());

				return this;
			}

			public Builder setExtraBlockEast(Block block) {
				if (block != null)
					extraProviderEast = SimpleStateProvider.simple(block.defaultBlockState());
				else
					extraProviderEast = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());

				return this;
			}

			public Builder setExtraBlockEast(BlockState state) {
				if (state != null)
					extraProviderEast = SimpleStateProvider.simple(state);
				else
					extraProviderEast = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());

				return this;
			}

			public Builder setExtraBlockEast(BlockStateProvider stateProvider) {
				if (stateProvider != null)
					extraProviderEast = stateProvider;
				else
					extraProviderEast = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());

				return this;
			}

			public Builder setExtraBlockWest(Block block) {
				if (block != null)
					extraProviderWest = SimpleStateProvider.simple(block.defaultBlockState());
				else
					extraProviderWest = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());

				return this;
			}

			public Builder setExtraBlockWest(BlockState state) {
				if (state != null)
					extraProviderWest = SimpleStateProvider.simple(state);
				else
					extraProviderWest = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());

				return this;
			}

			public Builder setExtraBlocWest(BlockStateProvider stateProvider) {
				if (stateProvider != null)
					extraProviderWest = stateProvider;
				else
					extraProviderWest = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());

				return this;
			}

			public Builder setExtraBlockSouth(Block block) {
				if (block != null)
					extraProviderSouth = SimpleStateProvider.simple(block.defaultBlockState());
				else
					extraProviderSouth = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());

				return this;
			}

			public Builder setExtraBlockSouth(BlockState state) {
				if (state != null)
					extraProviderSouth = SimpleStateProvider.simple(state);
				else
					extraProviderSouth = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());

				return this;
			}

			public Builder setExtraBlockSouth(BlockStateProvider stateProvider) {
				if (stateProvider != null)
					extraProviderSouth = stateProvider;
				else
					extraProviderSouth = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());

				return this;
			}

	        public Builder setDiskBlock(Block block) {
	            if (block != null)
	                diskProvider = SimpleStateProvider.simple(block.defaultBlockState());
	            else
	                diskProvider = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());

	            return this;
	        }

	        public Builder setDiskBlock(BlockState state) {
	            if (state != null)
	                diskProvider = SimpleStateProvider.simple(state);
	            else
	                diskProvider = SimpleStateProvider.simple(Blocks.AIR.defaultBlockState());

	            return this;
	        }

	        public Builder setDiskBlock(BlockStateProvider stateProvider) {
	            if (stateProvider != null)
	                diskProvider = stateProvider;
	            else
	                diskProvider = SimpleStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState());

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
	            this.diskProvider = config.diskProvider;
	            this.maxPossibleHeight = config.maxHeight;
	            this.minHeight = config.minHeight;
	            this.diskRadius = config.diskRadius;
	            this.whitelist = ImmutableList.copyOf(config.whitelist);
	            return this;
	        }

	        public WLTreeConfig build() {
				return new WLTreeConfig(this.trunkProvider, this.leavesProvider, this.extraProvider, this.extraProviderEast, this.extraProviderSouth, this.extraProviderWest, this.decorators, this.diskProvider, this.minHeight, this.maxPossibleHeight, this.diskRadius, this.whitelist.stream().map(Block::defaultBlockState).collect(Collectors.toList()));
	        }
	    }
}
