package com.latmod.mods.boneappetit;

import com.latmod.mods.boneappetit.item.BoneAppetitItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

@Mod(
		modid = BoneAppetit.MOD_ID,
		name = BoneAppetit.MOD_NAME,
		version = BoneAppetit.VERSION
)
public class BoneAppetit
{
	public static final String MOD_ID = "boneappetit";
	public static final String MOD_NAME = "Bone Appetit";
	public static final String VERSION = "0.0.0.boneappetit";

	public static final CreativeTabs TAB = new CreativeTabs(MOD_ID)
	{
		@Override
		public ItemStack createIcon()
		{
			return new ItemStack(BoneAppetitItems.WET_BONE_MEAL);
		}
	};
}