package quintinity.mods.mobcages;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MobCages.MODID, name = MobCages.MODNAME, version = MobCages.VERSION)
public class MobCages
{
    public static final String MODNAME = "Mob Cages";
    public static final String MODID = "mobcages";
    public static final String VERSION = "1.0";

    @Instance
    public static MobCages instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
}