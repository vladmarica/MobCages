package quintinity.mods.mobcages.integration;

import forestry.core.items.ItemWrench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Optional;

public class IntegrationForestry extends Integration
{
	@Override
	@Optional.Method(modid = "Forestry")
	public boolean isWrench(ItemStack itemstack, EntityPlayer player, int x, int y, int z) 
	{
		return itemstack.getItem() instanceof ItemWrench;
	}
	
	@Override
	@Optional.Method(modid = "Forestry")
	public void doWrenchRightClick(ItemStack itemstack, EntityPlayer player, int x, int y, int z) 
	{
		((ItemWrench)itemstack.getItem()).wrenchUsed(player, x, y, z);
		player.swingItem();
	}
}
