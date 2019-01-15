package com.latmod.mods.boneappetit;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

/**
 * @author LatvianModder
 */
public enum SoilType
{
	SAND(Blocks.SAND.getDefaultState(), "tile.sand.name"),
	SOUL_SAND(Blocks.SOUL_SAND.getDefaultState(), "tile.soulSand.name"),
	END_STONE(Blocks.END_STONE.getDefaultState(), "tile.endStone.name");

	public final IBlockState blockState;
	public final String nameKey;

	SoilType(IBlockState s, String l)
	{
		blockState = s;
		nameKey = l;
	}
}