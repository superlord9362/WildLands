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

package superlord.wildlands.common.world.feature.util;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;

public class FeatureUtil {

    public static boolean isPlant(LevelSimulatedReader  world, BlockPos pos) {
        return world.isStateAtPosition(pos, (state) -> state.is(BlockTags.LEAVES) || state.getMaterial() == Material.LEAVES || state.getMaterial() == Material.PLANT || state.getMaterial() == Material.REPLACEABLE_PLANT || state.getMaterial() == Material.WATER_PLANT || state.getMaterial() == Material.REPLACEABLE_FIREPROOF_PLANT);
    }

    public static boolean isTerrainOrRock(LevelSimulatedReader  world, BlockPos pos) {
        return world.isStateAtPosition(pos, (state) -> state.is(Tags.Blocks.STONE) || state.getMaterial() == Material.STONE || state.is(BlockTags.BASE_STONE_OVERWORLD) || state.getMaterial() == Material.DIRT || state.is(BlockTags.SAND) || state.is(Tags.Blocks.SAND) || state.is(Tags.Blocks.SAND_COLORLESS) || state.is(Tags.Blocks.SAND_RED) || state.is(Tags.Blocks.SANDSTONE) || state.getMaterial() == Material.SAND || state.getBlock() == Blocks.GRASS_BLOCK);
    }

	public static boolean isAir(LevelSimulatedReader  reader, BlockPos pos) {
        return reader.isStateAtPosition(pos, BlockState::isAir);
    }

    public static boolean isAirInRange(LevelSimulatedReader  world, BlockPos pos, int xRange, int yRange, int zRange) {
        return isAirInRange(world, pos, xRange, yRange, zRange, xRange, yRange, zRange);
    }

    public static boolean isAirInRange(LevelSimulatedReader  world, BlockPos pos, int xNegRange, int yNegRange, int zNegRange, int xPosRange, int yPosRange, int zPosRange) {
        BlockPos.MutableBlockPos  mutable = new BlockPos.MutableBlockPos ();
        for (int x = -xNegRange; x <= xPosRange; x++) {
            for (int y = -yNegRange; y <= yPosRange; y++) {
                for (int z = -zNegRange; z <= zPosRange; z++) {
                    mutable.set(pos.offset(x, y, z));
                    if (!isAir(world, mutable))
                        return false;
                }
            }
        }
        return true;
    }




    @SuppressWarnings("incomplete-switch")
	public static void transformMutable(BlockPos.MutableBlockPos pos, Mirror mirrorIn, Rotation rotationIn)
    {
        switch (mirrorIn)
        {
            case LEFT_RIGHT:
                pos.setZ(-pos.getZ());
                break;
            case FRONT_BACK:
                pos.setX(-pos.getX());
                break;
        }
        switch (rotationIn)
        {
            case COUNTERCLOCKWISE_90:
                pos.set(pos.getZ(), pos.getY(), -pos.getX());
                break;
            case CLOCKWISE_90:
                pos.set(-pos.getZ(), pos.getY(), pos.getX());
                break;
            case CLOCKWISE_180:
                pos.set(-pos.getX(), pos.getY(), -pos.getZ());
                break;
        }
    }

    public static BlockPos transform(BlockPos pos, Mirror mirrorIn, Rotation rotationIn)
    {
        int posX = pos.getX();
        int posZ = pos.getZ();
        boolean mirror = true;
        switch (mirrorIn)
        {
            case LEFT_RIGHT:
                posZ = -posZ;
                break;
            case FRONT_BACK:
                posX = -posX;
                break;
            default:
                mirror = false;
        }
        switch (rotationIn)
        {
            case COUNTERCLOCKWISE_90:
                return new BlockPos(posZ, pos.getY(), -posX);
            case CLOCKWISE_90:
                return new BlockPos(-posZ, pos.getY(), posX);
            case CLOCKWISE_180:
                return new BlockPos(-posX, pos.getY(), -posZ);
            default:
                return mirror ? new BlockPos(posX, pos.getY(), posZ) : pos;
        }
    }
}
