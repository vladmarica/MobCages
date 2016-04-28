package quintinity.mods.mobcages.client;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import quintinity.mods.mobcages.MobCages;
import quintinity.mods.mobcages.TileEntityCage;
import cpw.mods.fml.client.FMLClientHandler;

public class RenderCage extends TileEntitySpecialRenderer
{
	public TextureManager engine;
	public float defaultThickness = 0.0625F;
	public EntityLiving clientEntity;
	public static HashMap<String, EntityLiving> entities = new HashMap<String, EntityLiving>();
	
	public RenderCage()
	{
		engine = Minecraft.getMinecraft().renderEngine;
	}

	public void renderFrame(double x, double y, double z, float var8, boolean forInv)
	{
		engine = Minecraft.getMinecraft().renderEngine;
		GL11.glPushMatrix();
	    GL11.glTranslated(x, y, z);

	    
	    ResourceLocation texture = new ResourceLocation(MobCages.MODID, "textures/items/textures.png");
	    System.out.println("Engine not null: " + (engine != null));
	    System.out.println("Texture not null: " + (texture != null));
	    engine.bindTexture(texture);
	     
	    GL11.glRotatef(90F, 1F, 0F, 0F);
	    render3DTexture(1);
	    GL11.glRotatef(-90F, 1F, 0F, 0F);
	    GL11.glTranslatef(0, 0, 0.0625F);
	    if (!forInv) {
	    	render3DTexture(0, 0.0625F / 2);
	    }
	    GL11.glTranslatef(0, 0, 1 - 0.0625F);
	    GL11.glTranslatef(0, 0, -0.0625F / 2);
	    render3DTexture(0, 0.0625F / 2);
	    GL11.glTranslatef(0, 0, (0.0625F / 2));
	    GL11.glRotatef(90F, 0F, 1F, 0F);
	    
	    GL11.glTranslatef(0, 0, 0.0625F);
	    if (!forInv) {
	    	render3DTexture(0, 0.0625F / 2);
	    }
	    render3DTexture(2);
	    GL11.glTranslatef(0, 0, 1 - 0.0625F);
	    render3DTexture(2);
	    GL11.glTranslatef(0, 0, 0.0625F / -2);
	    render3DTexture(0, 0.0625F / 2);
	    GL11.glTranslatef(0, 1 - 0.0625F, -1 - (-0.0625F / 2));
	    GL11.glRotatef(90F, 1F, 0F, 0F);
	    render3DTexture(1);
	    GL11.glRotatef(-90F, 1F, 0F, 0F);
	    GL11.glPopMatrix();
	}
	
	public void renderEntity(EntityLiving entity, double x, double y, double z)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glTranslatef(1F, 0F, 0.9F);
		RenderManager.instance.renderEntityWithPosYaw(entity, 0, 0, 0, 0, 0);
		GL11.glPopMatrix();
	}
	
	public void render3DTexture(int index)
	{
		render3DTexture(index, 0.0625F);
	}
	
	public void render3DTexture(int index, float thickness)
	{
		Tessellator tess = Tessellator.instance;
        float var9 = (float)(index % 16 * 16 + 0) / 256.0F;
        float var10 = (float)(index % 16 * 16 + 16) / 256.0F;
        float var11 = (float)(index / 16 * 16 + 0) / 256.0F;
        float var12 = (float)(index / 16 * 16 + 16) / 256.0F;
        ItemRenderer.renderItemIn2D(tess, var10, var11, var9, var12, 256, 256, thickness); 
	}
	
	public EntityLiving getEntity(String entityID, World world)
	{
		if (!entities.containsKey(entityID)) {
			EntityLiving entity = (EntityLiving)EntityList.createEntityByName(entityID, world);
			entities.put(entityID, entity);
		}
		return entities.get(entityID);
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float var8) 
	{
		TileEntityCage cage = (TileEntityCage)tile;
		renderFrame(x, y, z, var8, false);
		if (cage.hasEntity) {
			EntityLiving entity = getEntity(cage.entityID, cage.getWorldObj());
			renderEntity(entity, x, y, z);
		}
	}
}