package quintinity.mods.mobcages;

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
import cpw.mods.fml.relauncher.Side;

public class ItemCage extends Item
{
	public ItemCage() 
	{
		super();
		this.setUnlocalizedName("cageitem");
		this.setTextureName(MobCages.MODID + ":empty");
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
    public boolean itemInteractionForEntity(ItemStack item, EntityPlayer player, EntityLivingBase entity)
    {
    	NBTTagCompound entityData = new NBTTagCompound();
    	if (entity.worldObj instanceof WorldServer && entity instanceof EntityAnimal) {
    		entity.writeToNBT(entityData);
    		entity.setDead();
    		int x = (int)entity.posX;
    		int y = (int)entity.posY;
    		int z = (int)entity.posZ;
    		entity.worldObj.setBlock(x, y, z, MobCages.cage);
    		TileEntityCage tile = (TileEntityCage)entity.worldObj.getTileEntity(x, y, z);
    		tile.onPlaced(x, y, z, entity);
        	item.stackSize--;
    	}
    	
        return true;
    }
    
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
    	int placeX = x;
    	int placeY = y;
    	int placeZ = z;
    		
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
    			list.add("\u00a79" + tag.getString("EntityString"));
    			list.add("Health: \u00a74" + tag.getFloat("EntityHealth"));
    		}
    	}
    	else {
    		list.add("Empty");
    	}
    }
}