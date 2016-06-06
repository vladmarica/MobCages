package quintinity.mods.mobcages.integration;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import net.minecraft.item.ItemStack;
import quintinity.mods.mobcages.MobCages;

public class NEIMobCagesConfig implements IConfigureNEI
{
	@Override
	public void loadConfig() 
	{
		API.hideItem(new ItemStack(MobCages.cage));
	}

	@Override
	public String getName() 
	{
		return MobCages.MODNAME;
	}

	@Override
	public String getVersion() 
	{
		return MobCages.VERSION;
	}
}
