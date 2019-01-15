package com.latmod.mods.boneappetit.client;

import com.latmod.mods.boneappetit.BoneAppetit;
import com.latmod.mods.boneappetit.item.BoneAppetitItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @author LatvianModder
 */
@Mod.EventBusSubscriber(modid = BoneAppetit.MOD_ID, value = Side.CLIENT)
public class BoneAppetitClientEventHandler
{
	private static void addModel(Item item, String variant)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), variant));
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event)
	{
		addModel(BoneAppetitItems.WET_BONE_MEAL, "inventory");
		addModel(BoneAppetitItems.FIERY_BONE_MEAL, "inventory");
		addModel(BoneAppetitItems.ENDER_BONE_MEAL, "inventory");
	}
}