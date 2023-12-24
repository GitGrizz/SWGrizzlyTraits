package com.oblivioussp.spartanweaponry.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface IHudLoadState 
{
	public boolean isLoaded(ItemStack stack);
    
	public float getLoadProgress(ItemStack stack, LivingEntity entity);
}
