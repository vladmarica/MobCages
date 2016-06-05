package quintinity.mods.mobcages.integration;

import ic2.core.item.tool.ItemToolWrench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Optional;

public class IntegrationIC2 extends Integration
{
	@Override
	@Optional.Method(modid = "IC2")
	public boolean isWrench(ItemStack itemstack, EntityPlayer player, int x, int y, int z) 
	{
		Item item = itemstack.getItem();
		if (item instanceof ItemToolWrench) {
			return ((ItemToolWrench)item).canTakeDamage(itemstack, 1);
		}
		return false;
	}
	
	@Override
	@Optional.Method(modid = "IC2")
	public void doWrenchRightClick(ItemStack item, EntityPlayer player, int x, int y, int z) 
	{
		((ItemToolWrench)item.getItem()).damage(item, 1, player);
		player.swingItem();
	}
}
