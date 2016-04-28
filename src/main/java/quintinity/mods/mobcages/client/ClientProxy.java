package quintinity.mods.mobcages.client;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import quintinity.mods.mobcages.CommonProxy;
import quintinity.mods.mobcages.MobCages;
import quintinity.mods.mobcages.TileEntityCage;

public class ClientProxy extends CommonProxy
{
	public void preInit(FMLPreInitializationEvent event) 
	{
		MinecraftForgeClient.registerItemRenderer(MobCages.cageItem, new MobCageItemRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCage.class, new RenderCage());
	}
	
	public void init(FMLInitializationEvent event) 
	{
		
	}
}