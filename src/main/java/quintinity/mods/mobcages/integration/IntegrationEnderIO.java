package quintinity.mods.mobcages.integration;
import cpw.mods.fml.common.Optional;
import crazypants.enderio.item.ItemYetaWrench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class IntegrationEnderIO extends Integration
{
	@Override
	@Optional.Method(modid = "EnderIO")
	public boolean isWrench(ItemStack itemstack, EntityPlayer player, int x, int y, int z) 
	{
		return itemstack.getItem() instanceof ItemYetaWrench;
	}
	
	@Override
	@Optional.Method(modid = "EnderIO")
	public void doWrenchRightClick(ItemStack itemstack, EntityPlayer player, int x, int y, int z) 
	{
		player.swingItem();
	}
}
