package quintinity.mods.mobcages;

import java.util.HashMap;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ItemCage extends Item
{
	public static HashMap<String, String> nameMap = new HashMap<String, String>();
	static 
	{
		nameMap.put("EntityHorse", "Horse");
		nameMap.put("Ozelot", "Ocelot");
		nameMap.put("MushroomCow", "Mooshroom");
	}
	
	public ItemCage() 
	{
		super();
		this.setUnlocalizedName("cageitem");
		this.setTextureName(MobCages.MODID + ":empty");
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
	/*	Called when a player right-clicks on an entity with a cage. */
    public boolean itemInteractionForEntity(ItemStack item, EntityPlayer player, EntityLivingBase entity)
    {
    	//We only want to do anything if the cage is empty, the entity is an alive animal, and we are on the server
    	if (!item.hasTagCompound() && entity.worldObj instanceof WorldServer && entity.isEntityAlive() && entity instanceof EntityAnimal) {
    		entity.setDead();
    		int x = (int)Math.round(entity.posX);
    		int y = (int)Math.round(entity.posY);
    		int z = (int)Math.round(entity.posZ);
    		entity.worldObj.setBlock(x, y, z, MobCages.cage);
    		TileEntityCage tile = (TileEntityCage)entity.worldObj.getTileEntity(x, y, z);
    		tile.onPlaced(x, y, z, entity);
        	item.stackSize--;
    	}
    	
        return true;
    }
    
    /* 
     * Called when a player right-clicks with this item on the ground
     * Returns weather or not placing a cage at the given coordinates was successful
     */
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
    	int placeX = x;
    	int placeY = y;
    	int placeZ = z;
    		
    	//figure out where the cage should be placed based on the side that was right-clicked
    	if(side == 0) {
    		placeY--;
    	}
    	else if(side == 1) {
    		placeY++;
    	}
    	else if(side == 2) {
    		placeZ--;
    	}
    	else if(side == 3) {
    		placeZ++;
    	}
    	else if(side == 4) {
    		placeX--;
    	}
    	else if(side == 5) {
    		placeX++;
    	}
    	
    	if (world.getBlock(placeX, placeY, placeZ) == Blocks.air) {
	    	world.setBlock(placeX, placeY, placeZ, MobCages.cage);
	    	if (stack.hasTagCompound()) {
		    	TileEntityCage tile = (TileEntityCage) world.getTileEntity(placeX, placeY, placeZ);
		    	if (tile != null) {
		    		tile.entityID = stack.getTagCompound().getString("EntityString");
		    		tile.hasEntity = stack.getTagCompound().getBoolean("HasEntity");
		    		tile.entityHealth = stack.getTagCompound().getFloat("EntityHealth");
		    		tile.entityData = stack.getTagCompound().getCompoundTag("EntityData");
		    		world.markBlockForUpdate(placeX, placeY, placeZ);
		    	}
	    	}
	    	stack.stackSize--;
	    	return true;
    	}
    	
    	return false; 	
	}
    
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) 
    {
    	if (item.hasTagCompound()) {
    		NBTTagCompound tag = item.getTagCompound();
    		if (tag.getBoolean("HasEntity")) {
    			String entityString = tag.getString("EntityString");
    			if (nameMap.containsKey(entityString)) {
    				entityString = nameMap.get(entityString);
    			}
    			list.add("\u00a79" + entityString);
    			list.add("Health: \u00a74" + tag.getFloat("EntityHealth"));
    		}
    	}
    	else {
    		list.add("Empty");
    	}
    }
}