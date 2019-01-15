package com.latmod.mods.boneappetit;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.WeightedRandom;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author LatvianModder
 */
@Mod.EventBusSubscriber(modid = BoneAppetit.MOD_ID)
@Config(modid = BoneAppetit.MOD_ID, category = "")
public class BoneAppetitConfig
{
	public static final General general = new General();
	public static final Soils soils = new Soils();

	private static final Map<SoilType, List<PlantEntry>> CACHE = new HashMap<>();

	public static class General
	{
		@Config.RangeInt(min = 0, max = 15)
		public int radius = 2;
	}

	public static class Soils
	{
		public final String[] sand = new String[] {
				"100x minecraft:air",
				"3x minecraft:cactus",
				"3x minecraft:reeds",
				"1x thaumcraft:cinderpearl",
		};

		public final String[] soul_sand = new String[] {
				"30x minecraft:air",
				"1x minecraft:nether_wart",
		};

		public final String[] end_stone = new String[] {
				"60x minecraft:air",
				"4x minecraft:chorus_flower",
				"1x extrautils2:enderlilly",
		};
	}

	private static class PlantEntry extends WeightedRandom.Item
	{
		private final IBlockState state;

		public PlantEntry(IBlockState s, int w)
		{
			super(w);
			state = s;
		}
	}

	public static IBlockState getState(SoilType type, Random random)
	{
		List<PlantEntry> list = CACHE.get(type);

		if (list == null)
		{
			list = new ArrayList<>();
			CACHE.put(type, list);

			String[] s;

			if (type == SoilType.SOUL_SAND)
			{
				s = soils.soul_sand;
			}
			else if (type == SoilType.END_STONE)
			{
				s = soils.end_stone;
			}
			else
			{
				s = soils.sand;
			}

			for (String s1 : s)
			{
				String[] s2 = s1.split("x ", 2);

				if (s2.length == 2)
				{
					try
					{
						int w = Integer.parseInt(s2[0]);

						if (w > 0)
						{
							Block block = Block.getBlockFromName(s2[1]);
							list.add(new PlantEntry((block == null ? Blocks.AIR : block).getDefaultState(), w));
						}
					}
					catch (Exception ex)
					{
					}
				}
			}
		}

		if (list.isEmpty())
		{
			return Blocks.AIR.getDefaultState();
		}

		return WeightedRandom.getRandomItem(random, list).state;
	}

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.getModID().equals(BoneAppetit.MOD_ID))
		{
			ConfigManager.sync(BoneAppetit.MOD_ID, Config.Type.INSTANCE);
			CACHE.clear();
		}
	}
}