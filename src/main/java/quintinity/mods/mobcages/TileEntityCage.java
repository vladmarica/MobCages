package quintinity.mods.mobcages;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;

public class TileEntityCage extends TileEntity 
{
	public String entityID = "";
	public float entityHealth = 0;
	public boolean hasEntity = false;
	
	public TileEntityCage()
	{
	}
	
	@Override
	public void updateEntity() 
	{
	}
	
	public boolean releaseEntity(int x, int y, int z)
	{
		if (hasEntity) {
			EntityLiving entity = (EntityLiving) EntityList.createEntityByName(entityID, getWorld());
			entity.setHealth(entityHealth);
			entity.setPosition(x + 0.5, y + 1, z + 0.5);
			worldObj.spawnEntityInWorld(entity);
			worldObj.setBlockToAir(x, y, z);
			return true;
		}
		return false;
	}
	
	public void onPlaced(int x, int y, int z, EntityLiving entity)
	{
		entityID = EntityList.getEntityString(entity);
		hasEntity = true;
		entityHealth = entity.getHealth();
		getWorld().markBlockForUpdate(x, y, z);
	}
	
	public void handleBreaking(int x, int y, int z)
	{
		/*
		ItemStack stack = new ItemStack(MobCages.mobCageItem);
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("EntityString", entityID);
        tag.setBoolean("HasEntity", hasEntity);
        tag.setFloat("EntityHealth", entityHealth);
		stack.setTagCompound(tag);
		EntityItem item = new EntityItem(getWorld(), x + 0.5, y + 1, z + 0.5, stack);
		worldObj.spawnEntityInWorld(item);
		*/
	}
	
	/*
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, -1, tag);
	}
	
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
    	this.readFromNBT(pkt.customParam1);
    }
    */
    
	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
    {
		super.readFromNBT(tagCompound);
        entityID = tagCompound.getString("EntityString");
        hasEntity = tagCompound.getBoolean("HasEntity");
        entityHealth = tagCompound.getInteger("EntityHealth");
    }
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);
        tagCompound.setString("EntityString", entityID);
        tagCompound.setBoolean("HasEntity", hasEntity);
        tagCompound.setFloat("EntityHealth", entityHealth);
    }
	
	public World getWorld()
	{
		return this.worldObj;
	}
}