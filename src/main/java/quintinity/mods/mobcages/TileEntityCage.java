package quintinity.mods.mobcages;

import java.util.Random;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityCage extends TileEntity 
{
	public String entityID = "";
	public float entityHealth = 0;
	public boolean hasEntity = false;
	public NBTTagCompound entityData;
	private Random random = new Random();
	
	public TileEntityCage() {}
	
	//called to release the entity from this cage
	public boolean releaseEntity(int x, int y, int z)
	{
		if (hasEntity) {
			EntityLiving entity = (EntityLiving) EntityList.createEntityByName(entityID, worldObj);
			entity.setHealth(entityHealth);
			entity.setPosition(x + 0.5, y + 1, z + 0.5);
			
			if (entityData != null) {
				entity.readEntityFromNBT(entityData);
			}
			
			worldObj.spawnEntityInWorld(entity);
			worldObj.setBlockToAir(x, y, z);
			
			//25% chance that you do not get the cage back when you open it
			if (random.nextInt(4) != 0) { 
				EntityItem item = new EntityItem(worldObj, x + 0.5, y + 0.5, z + 0.5, new ItemStack(MobCages.cageItem));
				worldObj.spawnEntityInWorld(item);
			}
			return true;
		}
		return false;
	}
	
	public void onPlaced(int x, int y, int z, EntityLivingBase entity)
	{
		entityID = EntityList.getEntityString(entity);
		hasEntity = true;
		entityHealth = entity.getHealth();
		entityData = new NBTTagCompound();
		entity.writeEntityToNBT(entityData);
		
		worldObj.markBlockForUpdate(x, y, z);
	}
	
	public void handleBreaking(int x, int y, int z)
	{
		ItemStack stack = new ItemStack(MobCages.cageItem);
		if (hasEntity) {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setString("EntityString", entityID);
	        tag.setBoolean("HasEntity", hasEntity);
	        tag.setFloat("EntityHealth", entityHealth);
	        tag.setTag("EntityData", entityData);
			stack.setTagCompound(tag);
		}
		EntityItem item = new EntityItem(getWorld(), x + 0.5, y + 1, z + 0.5, stack);
		worldObj.spawnEntityInWorld(item);
	}
	
	@Override
	public Packet getDescriptionPacket()
    {
		NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tag);
    }
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
		this.readFromNBT(pkt.func_148857_g());
    }
    
	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
    {
		super.readFromNBT(tagCompound);
        entityID = tagCompound.getString("EntityString");
        hasEntity = tagCompound.getBoolean("HasEntity");
        entityHealth = tagCompound.getFloat("EntityHealth");
        entityData = tagCompound.getCompoundTag("EntityData");
    }
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);
        tagCompound.setString("EntityString", entityID);
        tagCompound.setBoolean("HasEntity", hasEntity);
        tagCompound.setFloat("EntityHealth", entityHealth);
        tagCompound.setTag("EntityData", entityData);
    }
	
	public World getWorld()
	{
		return this.worldObj;
	}
	
	@Override
	public boolean canUpdate()
    {
        return false; //we do not need 'updateEntity' to be called
    }
}