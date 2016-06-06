package quintinity.mods.mobcages.integration;

import cofh.thermalexpansion.item.tool.ItemWrench;
import cofh.thermalexpansion.item.tool.ItemWrenchBattle;
import cpw.mods.fml.common.Optional;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class IntegrationTE extends Integration
{
	@Override
	@Optional.Method(modid = "ThermalExpansion")
	public boolean isWrench(ItemStack itemstack, EntityPlayer player, int x, int y, int z) 
	{
		Item item = itemstack.getItem();
		return item instanceof ItemWrench || item instanceof ItemWrenchBattle;
	}
	
	@Override
	@Optional.Method(modid = "ThermalExpansion")
	public void doWrenchRightClick(ItemStack itemstack, EntityPlayer player, int x, int y, int z) 
	{
	}
}
