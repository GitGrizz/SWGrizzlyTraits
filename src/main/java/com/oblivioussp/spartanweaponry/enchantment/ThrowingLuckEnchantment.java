package com.oblivioussp.spartanweaponry.enchantment;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

import net.minecraft.enchantment.LootBonusEnchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class ThrowingLuckEnchantment extends LootBonusEnchantment
{
	public ThrowingLuckEnchantment(Rarity rarityIn, EquipmentSlotType... slots)
	{
		super(rarityIn, EnchantmentSW.TYPE_THROWING_WEAPON, slots);
		name = "lucky_throw";
		setRegistryName("lucky_throw");
	}
    
    /**
     * Return the name of key in translation table of this enchantment.
     */
    @Override
    public String getName()
    {
    	return String.format("enchantment.%s.%s", ModSpartanWeaponry.ID, name);
    }
}
