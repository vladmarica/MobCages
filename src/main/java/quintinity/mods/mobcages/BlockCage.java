package quintinity.mods.mobcages;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BlockCage extends BlockContainer
{
	public int renderID;
		
	public BlockCage() 
	{
		super(Material.rock);
		this.setHardness(0.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeWood);
		this.setBlockName("cage");
		this.setResistance(30F);
	}
	
    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest)
    {
    	TileEntityCage tile = (TileEntityCage) world.getTileEntity(x, y, z);
		if (tile != null && world instanceof WorldServer) {
			tile.handleBreaking(x, y, z);
		}
		return super.removedByPlayer(world, player, x, y, z, willHarvest);
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
    
    @Override
	public TileEntity createNewTileEntity(World world, int metadata) 
	{
		return new TileEntityCage();
	}
    
    @Override
    public boolean isOpaqueCube()
    {
    	return false;
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderType()
    {
        return -1;
    }

}
