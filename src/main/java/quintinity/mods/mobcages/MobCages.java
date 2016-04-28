package quintinity.mods.mobcages;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = MobCages.MODID, name = MobCages.MODNAME, version = MobCages.VERSION)
public class MobCages
{
    public static final String MODNAME = "Mob Cages";
    public static final String MODID = "mobcages";
    public static final String VERSION = "1.0";

    
    @Instance
    public static MobCages instance;
    
    @SidedProxy(clientSide = "quintinity.mods.mobcages.client.ClientProxy", serverSide = "quintinity.mods.mobcages.server.ServerProxy")
    public static CommonProxy proxy;
    
    public static Item crowbar;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	crowbar = new ItemCrowbar();
    	GameRegistry.registerItem(crowbar, "crowbar");
    	GameRegistry.addRecipe(new ItemStack(crowbar), "X X", "XXX", " X ", 'X', Items.iron_ingot);
    	
    	proxy.preInit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init(event);
    }
}