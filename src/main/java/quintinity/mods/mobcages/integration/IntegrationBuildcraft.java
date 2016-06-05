package quintinity.mods.mobcages.integration;
import buildcraft.api.tools.IToolWrench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Optional;

public class IntegrationBuildcraft extends Integration 
{
	@Override
	@Optional.Method(modid = "BuildCraft|Core")
	public boolean isWrench(ItemStack itemstack, EntityPlayer player, int x, int y, int z) 
	{
		if (itemstack.getItem() instanceof IToolWrench) {
			IToolWrench wrench = (IToolWrench)itemstack.getItem();
			return wrench.canWrench(player, x, y, z);
		}
		return false;
	}
	
	@Override
	@Optional.Method(modid = "BuildCraft|Core")
	public void doWrenchRightClick(ItemStack item, EntityPlayer player, int x, int y, int z) 
	{
		((IToolWrench)item.getItem()).wrenchUsed(player, x, y, z);
	}
}
