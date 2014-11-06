package tehnut.emt.modules.base.items.foci;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tehnut.emt.modules.base.blocks.BaseBlockRegistry;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemShieldFocus extends ItemFocusBase {

	private static final AspectList visCost = new AspectList().add(Aspect.ORDER, 10).add(Aspect.WATER, 10).add(Aspect.AIR, 10);

	public ItemShieldFocus() {
		super("shield", "focus_shield");
	}

	@Override
	public int getFocusColor() {
		return 555555555;
	}

	@Override
	public boolean isUseItem() {
		return true;
	}

	@Override
	public AspectList getVisCost() {
		return visCost;
	}

	@Override
	public String getSortingHelper(ItemStack itemstack) {
		return "SHIELD";
	}

	@Override
	public void onUsingFocusTick(ItemStack itemstack, EntityPlayer player, int time) {
		player.motionX = 0.0D;
		player.motionY = 0.0D;
		player.motionZ = 0.0D;

		ItemWandCasting wand = (ItemWandCasting) itemstack.getItem();
		if (wand.consumeAllVis(itemstack, player, getVisCost(), true, true)) {
			int x = MathHelper.floor_double(player.posX);
			int y = MathHelper.floor_double(player.posY);
			int z = MathHelper.floor_double(player.posZ);

			// Player Level
			if (player.worldObj.isAirBlock(x + 1, y, z) && player.worldObj.isAirBlock(x - 1, y, z) && player.worldObj.isAirBlock(x, y, z + 1) && player.worldObj.isAirBlock(x, y, z - 1)) {
				player.worldObj.setBlock(x + 1, y, z, BaseBlockRegistry.shieldBlock);
				player.worldObj.setBlock(x - 1, y, z, BaseBlockRegistry.shieldBlock);
				player.worldObj.setBlock(x, y, z + 1, BaseBlockRegistry.shieldBlock);
				player.worldObj.setBlock(x, y, z - 1, BaseBlockRegistry.shieldBlock);
			}

			// Above the player
			if (player.worldObj.isAirBlock(x + 1, y + 1, z) && player.worldObj.isAirBlock(x - 1, y + 1, z) && player.worldObj.isAirBlock(x, y + 1, z + 1) && player.worldObj.isAirBlock(x, y + 1, z - 1)) {
				player.worldObj.setBlock(x + 1, y + 1, z, BaseBlockRegistry.shieldBlock);
				player.worldObj.setBlock(x - 1, y + 1, z, BaseBlockRegistry.shieldBlock);
				player.worldObj.setBlock(x, y + 1, z + 1, BaseBlockRegistry.shieldBlock);
				player.worldObj.setBlock(x, y + 1, z - 1, BaseBlockRegistry.shieldBlock);
			}
		}
	}

	@Override
	public void onPlayerStoppedUsingFocus(ItemStack itemstack, World world, EntityPlayer player, int count) {
		int x = MathHelper.floor_double(player.posX);
		int y = MathHelper.floor_double(player.posY);
		int z = MathHelper.floor_double(player.posZ);

		// Player Level
		if ((player.worldObj.getBlock(x + 1, y, z) == BaseBlockRegistry.shieldBlock) && (player.worldObj.getBlock(x - 1, y, z) == BaseBlockRegistry.shieldBlock) && (player.worldObj.getBlock(x, y, z + 1) == BaseBlockRegistry.shieldBlock) && (player.worldObj.getBlock(x, y, z - 1) == BaseBlockRegistry.shieldBlock)) {
			player.worldObj.setBlockToAir(x + 1, y, z);
			player.worldObj.setBlockToAir(x - 1, y, z);
			player.worldObj.setBlockToAir(x, y, z + 1);
			player.worldObj.setBlockToAir(x, y, z - 1);
		}

		// Above the player
		if ((player.worldObj.getBlock(x + 1, y + 1, z) == BaseBlockRegistry.shieldBlock) && (player.worldObj.getBlock(x - 1, y + 1, z) == BaseBlockRegistry.shieldBlock) && (player.worldObj.getBlock(x, y + 1, z + 1) == BaseBlockRegistry.shieldBlock) && (player.worldObj.getBlock(x, y + 1, z - 1) == BaseBlockRegistry.shieldBlock)) {
			player.worldObj.setBlockToAir(x + 1, y + 1, z);
			player.worldObj.setBlockToAir(x - 1, y + 1, z);
			player.worldObj.setBlockToAir(x, y + 1, z + 1);
			player.worldObj.setBlockToAir(x, y + 1, z - 1);
		}

		ItemStack milk = (new ItemStack(Items.milk_bucket));
		player.curePotionEffects(milk);
	}
}