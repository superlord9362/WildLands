/*
Copyright (C) 2020 Corgi Taco

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 3 of the License.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with this program; if not, write to the Free Software Foundation,
Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/

package superlord.wildlands.common.util;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import superlord.wildlands.common.world.feature.config.WLTreeConfig;

public abstract class TreeSpawner {
    @Nullable
    protected abstract ConfiguredFeature<WLTreeConfig, ?> getTreeFeature(Random random);

    public boolean spawn(ServerLevel worldIn, ChunkGenerator chunkGenerator, BlockPos pos, BlockState blockUnder, Random random) {
        ConfiguredFeature<WLTreeConfig, ?> configuredTreeFeature = this.getTreeFeature(random);
        if (configuredTreeFeature == null) {
            return false;
        } else {
            worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 4);
            if (configuredTreeFeature.place(worldIn, chunkGenerator, random, pos)) {
                return true;
            } else {
                worldIn.setBlock(pos, blockUnder, 4);
                return false;
            }
        }
    }
}