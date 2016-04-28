package quintinity.mods.mobcages.client;

import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class MobCageItemRenderer implements IItemRenderer 
{
	RenderCage cage = new RenderCage();
	
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		return type != ItemRenderType.FIRST_PERSON_MAP;
	}

	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) 
	{
		return true;
	}

	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		if (type == ItemRenderType.INVENTORY) {
			GL11.glTranslatef(0F, -0.1F, 0F);
			cage.renderFrame(0, 0, 0, 0, true);
		}
		else if (type == ItemRenderType.ENTITY) {
			
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glTranslatef(-0.5F, -0.4F, -0.5F);
			cage.renderFrame(0, 0, 0, 0, false);
		}
		else {
			cage.renderFrame(0, 0, 0, 0, false);
		}
	}
}