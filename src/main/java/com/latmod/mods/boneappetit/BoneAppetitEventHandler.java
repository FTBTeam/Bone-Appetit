package com.latmod.mods.boneappetit;

import com.latmod.mods.boneappetit.item.ItemBoneMeal;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * @author LatvianModder
 */
@Mod.EventBusSubscriber(modid = BoneAppetit.MOD_ID)
public class BoneAppetitEventHandler
{
	private static Item withName(Item item, String name)
	{
		item.setCreativeTab(BoneAppetit.TAB);
		item.setRegistryName(name);
		item.setTranslationKey(BoneAppetit.MOD_ID + "." + name);
		return item;
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		IForgeRegistry<Item> r = event.getRegistry();
		r.register(withName(new ItemBoneMeal(SoilType.SAND), "wet_bone_meal"));
		r.register(withName(new ItemBoneMeal(SoilType.SOUL_SAND), "fiery_bone_meal"));
		r.register(withName(new ItemBoneMeal(SoilType.END_STONE), "ender_bone_meal"));
	}
}