package quintinity.mods.mobcages.integration;
import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public abstract class Integration 
{
	private static ArrayList<Integration> integrationList = new ArrayList<Integration>();
	
	public static void register(Integration in) 
	{
		integrationList.add(in);
	}
	
	public static boolean useWrench(ItemStack item, EntityPlayer player, int x, int y, int z) 
	{
		for (Integration in : integrationList) {
			if (in.isWrench(item, player, x, y, z)) {
				in.doWrenchRightClick(item, player, x, y, z);
				return true;
			}
		}
		return false;
	}
	
	
	public boolean isWrench(ItemStack itemstack, EntityPlayer player, int x, int y, int z) 
	{
		return false;
	}
	
	public void doWrenchRightClick(ItemStack itemstack, EntityPlayer player, int x, int y, int z) 
	{
	}
}
