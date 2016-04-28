package quintinity.mods.mobcages;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
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
    public static Item cageItem;
    public static Block cage;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	crowbar = new ItemCrowbar();
    	GameRegistry.registerItem(crowbar, "crowbar");
    	GameRegistry.addRecipe(new ItemStack(crowbar), "X X", "XXX", " X ", 'X', Items.iron_ingot);
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(crowbar), "X X", "XXX", " X ", 'X', "ingotIron"));
    	
    	cageItem = new ItemCage();
    	GameRegistry.registerItem(cageItem, "cageitem");
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(cageItem), "XXX", "YYY", "XXX", 'X', "plankWood", 'Y', Blocks.iron_bars));
    	
    	cage = new BlockCage();
    	GameRegistry.registerBlock(cage, "cageblock");
    	GameRegistry.registerTileEntity(TileEntityCage.class, "cagetileentity");
    	
    	proxy.preInit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init(event);
    }
}