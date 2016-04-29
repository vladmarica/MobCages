package quintinity.mods.mobcages.client;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class MobCageItemRenderer implements IItemRenderer 
{
	RenderCage cage = new RenderCage();
	
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		return type != ItemRenderType.FIRST_PERSON_MAP; //handle all render types except for maps
	}

	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) 
	{
		return true;
	}

	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		if (type == ItemRenderType.INVENTORY) { //render in inventory
			GL11.glTranslatef(0F, -0.1F, 0F);
			GL11.glEnable(GL11.GL_LIGHTING);
			cage.renderFrame(0, 0, 0, 0, true);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glTranslatef(0F, 0.1F, 0F);
		}
		else if (type == ItemRenderType.ENTITY) { //render in the world as a dropped item
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glTranslatef(-0.5F, -0.4F, -0.5F);
			cage.renderFrame(0, 0, 0, 0, false);
		}
		else { //render in a player's hand
			GL11.glTranslatef(1F, 0.2F, 1F);
			GL11.glRotatef(180F, 0F, 1F, 0F);
			cage.renderFrame(0, 0, 0, 0, false);
			if (item.hasTagCompound()) {
				NBTTagCompound tag = item.getTagCompound();
				EntityLiving entity = cage.getEntity(tag.getString("EntityString"));
				cage.renderEntity(entity, 0, 0, 0);
			}
		}
	}
}