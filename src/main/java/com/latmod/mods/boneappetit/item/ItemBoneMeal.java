package com.latmod.mods.boneappetit.item;

import com.latmod.mods.boneappetit.BoneAppetitConfig;
import com.latmod.mods.boneappetit.SoilType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author LatvianModder
 */
public class ItemBoneMeal extends Item
{
	public final SoilType type;

	public ItemBoneMeal(SoilType t)
	{
		type = t;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		IBlockState state = world.getBlockState(pos);

		if (state == type.blockState)
		{
			if (!world.isRemote)
			{
				for (int z = -BoneAppetitConfig.general.radius; z <= BoneAppetitConfig.general.radius; z++)
				{
					for (int x = -BoneAppetitConfig.general.radius; x <= BoneAppetitConfig.general.radius; x++)
					{
						if (world.getBlockState(pos.add(x, 0, z)) == state)
						{
							BlockPos pos1 = pos.add(x, 1, z);
							IBlockState state1 = world.getBlockState(pos1);

							if (state1.getBlock().isReplaceable(world, pos1))
							{
								IBlockState state2 = BoneAppetitConfig.getState(type, world.rand);

								if (state2 != Blocks.AIR.getDefaultState())
								{
									world.setBlockState(pos1, state2);
									world.playEvent(2005, pos1, 0);
								}
							}
						}
					}
				}

				player.getHeldItem(hand).shrink(1);
			}

			return EnumActionResult.SUCCESS;
		}

		return EnumActionResult.FAIL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag)
	{
		tooltip.add(I18n.format(type.nameKey));
	}
}