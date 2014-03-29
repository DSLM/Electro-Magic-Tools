/*******************************************************************************
 * Copyright (c) 2014 Tombenpotter.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at http://www.gnu.org/licenses/gpl.html
 * 
 * This class was made by Tombenpotter and is distributed as a part of the Electro-Magic Tools mod.
 * Electro-Magic Tools is a derivative work on Thaumcraft 4 (c) Azanor 2012.
 * http://www.minecraftforum.net/topic/1585216-
 ******************************************************************************/
package electricMagicTools.tombenpotter.electricmagictools.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CreativeTab
{

	public static void load() {
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabTombenpotter", "en_US", "Electro-Magic Tools");
	}

	public static CreativeTabs tabTombenpotter = new CreativeTabs("tabTombenpotter")
	{
		public ItemStack getIconItemStack() {
			return new ItemStack(ItemRegistry.thaumiumDrill);
		}
	};
}
