package quintinity.mods.mobcages;
import java.util.HashMap;
import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class FuelHandler implements IFuelHandler
{
	public static HashMap<String, Integer> mobBurnTime;
	public static int EMPTY_BURN_TIME = 1000; //burn time for an empty cage
	public static int DEFAULT_BURN_TIME = 1600; //burn time for an unidentified mob
	
	static 
	{
		mobBurnTime = new HashMap<String, Integer>();
		mobBurnTime.put("Chicken", 3200);
		mobBurnTime.put("Cow", 7200);
		mobBurnTime.put("Pig", 6400);
		mobBurnTime.put("Sheep", 7200);
		mobBurnTime.put("Ozelot", 4800); //Ocelot
		mobBurnTime.put("EntityHorse", 10000); //Horse
		mobBurnTime.put("MushroomCow", 12000); //Mooshroom
	}
	
	@Override
	public int getBurnTime(ItemStack fuel) 
	{
		if (fuel.getItem() == MobCages.cageItem) {
			if (fuel.hasTagCompound()) {
				String entityString = fuel.getTagCompound().getString("EntityString");
				if (mobBurnTime.containsKey(entityString)) {
					return mobBurnTime.get(entityString);
				}
				return DEFAULT_BURN_TIME;
				
			}
			return EMPTY_BURN_TIME;
		}
		return 0;
	}
}
