package com.oblivioussp.spartanweaponry.item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.api.trait.IRangedTraitCallback;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;
import com.oblivioussp.spartanweaponry.client.ClientHelper;
import com.oblivioussp.spartanweaponry.client.gui.HudElementCrosshair;
import com.oblivioussp.spartanweaponry.client.gui.HudElementCrosshairHeavyCrossbow;
import com.oblivioussp.spartanweaponry.entity.projectile.BoltEntity;
import com.oblivioussp.spartanweaponry.init.ModEnchantments;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.util.Defaults;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.loading.FMLEnvironment;

public class HeavyCrossbowItem extends CrossbowItem implements IHudLoadState, IHudCrosshair
{
	protected WeaponMaterial material;
	protected String modId = null;
	protected int loadTicks;
	protected int aimTicks;
	
	protected String customDisplayName = null;
	
	protected boolean doCraftCheck = true;
	protected boolean canBeCrafted = true;
	
//	public static final String NBT_LOADED = "loaded";
//	public static final String NBT_AMMO = "ammo";
	
	public static final String NBT_CHARGED = "Charged";
	public static final String NBT_PROJECTILE = "Projectile";
	public static final Predicate<ItemStack> BOLT = (stack) -> stack.getItem().isIn(ItemTags.getCollection().get(new ResourceLocation(ModSpartanWeaponry.ID, "bolts")));

	public HeavyCrossbowItem(String unlocName, Item.Properties prop, WeaponMaterial material, boolean usingDeferredRegister) 
	{
		super(prop.maxDamage((int)(material.getMaxUses() * 1.5f)));
		if(!usingDeferredRegister)
			this.setRegistryName(unlocName);
		this.material = material;
		loadTicks = Defaults.CrossbowTicksToLoad;
		aimTicks = Defaults.CrossbowInaccuracyTicks;
		
		// Modify load and aim ticks via traits
		if(material.hasAnyWeaponTrait())
		{
			for(WeaponTrait trait : material.getAllWeaponTraits())
			{
				IRangedTraitCallback callback = trait.getRangedCallback();
				if(trait.isRangedTrait() && callback != null)
				{
					loadTicks = callback.modifyHeavyCrossbowLoadTime(material, loadTicks);
					aimTicks = callback.modifyHeavyCrossbowAimTime(material, aimTicks);
				}
			}
		}
		
		// Add to list on client only
		if(FMLEnvironment.dist.isClient())
			ClientHelper.registerHeavyCrossbowPropertyOverrides(this);
		//DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> () -> );
		
		//itemList.add(this);
		/*
		
		/*this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn == null && !(entityIn instanceof EntityPlayer))
                {
                    return 0.0F;
                }
                
                ItemStack itemstack = entityIn.getActiveItemStack();
                //return itemstack != null && itemstack.getItem() instanceof ItemCrossbow  ? (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F : 0.0F;
            	if(NBTHelper.getBoolean(stack, nbtIsLoaded))
            		return 1.0f;
                if(itemstack != null && itemstack.isItemEqual(stack) && itemstack.getItem() instanceof ItemCrossbow)
                {
                	return (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / ConfigHandler.crossbowTicksToLoad;
                }
                return 0.0f;
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });*/
		
		
		/*this.addPropertyOverride(new ResourceLocation("pull"), (stack, world, living) ->
		{
			if(living != null && stack.getItem() == this)
				return isLoaded(stack) ? 0.0f : (float)(getLoadingTicks(stack, living)) / getFullLoadTicks(stack);
			return 0.0f;
		});
		this.addPropertyOverride(new ResourceLocation("pulling"), (stack, world, living) ->
		{
			return living != null && living.isHandActive() && living.getActiveItemStack() == stack ? 1.0f : 0.0f;
		});
		this.addPropertyOverride(new ResourceLocation("charged"), (stack, world, living) ->
		{
			return isLoaded(stack) ? 1.0f : 0.0f;
		});*/
		
	}

	public HeavyCrossbowItem(String regName, Item.Properties prop, WeaponMaterial material, String customDisplayName, boolean usingDeferredRegister)
	{
		this(regName, prop, material, usingDeferredRegister);
		if(material.useCustomDisplayName())
			this.customDisplayName = customDisplayName;
	}
	
	// ---- ---- ---- ---- ---- ---- ---- ----
	// Overriding methods
	// ---- ---- ---- ---- ---- ---- ---- ----
	@Override
	public int getMaxDamage(ItemStack stack) 
	{
		return material.getMaxUses();
	}
	
	@Override
	public Predicate<ItemStack> getAmmoPredicate()
	{
		return BOLT;
	}
	
	@Override
	public Predicate<ItemStack> getInventoryAmmoPredicate() 
	{
		return BOLT;
	}
	
	@Override
	public int getUseDuration(ItemStack stack)
	{
		return /*stack.getTag() != null && !stack.getTag().getBoolean(NBT_CHARGED) ? Reference.DefaultCrossbowTicksToLoad :*/ 72000;
	}
	
/*    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
    {
        //if(oldStack.hasTagCompound() && newStack.hasTagCompound() && oldStack.getTagCompound() != newStack.getTagCompound())
            //return true;
    	if(!ItemStack.areItemStackTagsEqual(oldStack, newStack))
    		return true;
        return super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged);
    }
*/
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) 
	{
		if(entityLiving instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity)entityLiving;
			boolean isCreativeOrInfinite = player.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            
    		if(this.getLoadProgress(stack, entityLiving) == 1.0f)
    		{
    			// Load the Crossbow
    			stack.getOrCreateTag().putBoolean(NBT_CHARGED, true);
    			ItemStack bolt = ItemStack.EMPTY;
    			int count = EnchantmentHelper.getEnchantmentLevel(Enchantments.MULTISHOT, stack) > 0 ? 3 : 1;
    			
    			bolt = entityLiving.findAmmo(stack);
    			if(bolt.isEmpty() || !BOLT.test(bolt))			// Fix: When in Creative Mode, the player will return an Arrow item as ammo, which is invalid; replace it with a Bolt
    				bolt = new ItemStack(ModItems.bolt, count);

        		// Create a copy of the bolt, then save it to NBT.
    			ItemStack boltToStore = bolt.copy();
    			boltToStore.setCount(count);
    			CompoundNBT nbtBolt = new CompoundNBT();
    			boltToStore.write(nbtBolt);
    			stack.getTag().put(NBT_PROJECTILE, nbtBolt);
    			
    			if(!player.abilities.isCreativeMode)
    			{
    				bolt.shrink(1);
    				if(bolt.isEmpty())
    					player.inventory.deleteStack(bolt);
    			}
    			worldIn.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_CROSSBOW_LOADING_END, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.5F + 1.0F) + 0.2F);
    			//player.getCooldownTracker().setCooldown(this, Reference.DefaultCrossbowTicksCooldown);
    		}
    		else
    		{
    			// Fire the Crossbow
    			ItemStack itemstack = ItemStack.EMPTY;
                CompoundNBT tag = stack.getOrCreateTag().getCompound(NBT_PROJECTILE);
                
                if(tag != null && !tag.isEmpty())
                	itemstack = ItemStack.read(tag);
                
	            int i = this.getUseDuration(stack) - timeLeft;
	            
	            if (i < 0 || !stack.getTag().getBoolean(NBT_CHARGED)) return;
	
	            if (!itemstack.isEmpty() || isCreativeOrInfinite)
	            {
	                if (itemstack.isEmpty())
	                {
	                    itemstack = new ItemStack(ModItems.bolt);
	                }
	
	                if (!worldIn.isRemote)
	                {
	                	BoltItem itemBolt = ((BoltItem)(itemstack.getItem() instanceof BoltItem ? itemstack.getItem() : ModItems.bolt));
	                	
	                	boolean flag1 = player.abilities.isCreativeMode || (itemstack.getItem() instanceof BoltItem ? ((BoltItem)itemstack.getItem()).isInfinite(itemstack, stack, player) : false);
	                    
	                	// Account for lack of accuracy.
	                	int stackAimTicks = getAimTicks(stack);
	                    int inaccuracy = MathHelper.clamp(stackAimTicks - i, 0, stackAimTicks);
	                    float inaccuracyModifier = 0.0f;

	                    if(inaccuracy != 0)		// Apply inaccuracy if there is any.
	                    	inaccuracyModifier = 12.0f * ((float)inaccuracy / stackAimTicks);
	                	
	                	// Fire projectiles.
	                    this.spawnProjectile(stack, itemBolt, itemstack, worldIn, player, flag1, inaccuracyModifier, 0.0f);
	                    if(itemstack.getCount() > 1)
	                    {
		                    this.spawnProjectile(stack, itemBolt, itemstack, worldIn, player, flag1, inaccuracyModifier, -10.0f);
		                    this.spawnProjectile(stack, itemBolt, itemstack, worldIn, player, flag1, inaccuracyModifier, 10.0f);
	                    }
	                    int damage = itemstack.getCount() > 1 ? 3 : 1;
	                    stack.damageItem(damage, player, (playerEntity) -> playerEntity.sendBreakAnimation(player.getActiveHand()));
	                    
	                    stack.getTag().putBoolean(NBT_CHARGED, false);
	                    stack.getTag().put(NBT_PROJECTILE, new CompoundNBT());
	                    //NBTHelper.setBoolean(stack, nbtIsLoaded, false);
	                    //NBTHelper.setTagCompound(stack, nbtAmmoStack, new NBTTagCompound());
	                }
	
	                worldIn.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_CROSSBOW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (Item.random.nextFloat() * 0.4F + 1.2F) + getBoltVelocity() * 0.5F);
	
	                player.addStat(Stats.ITEM_USED.get(this));
	            }
    		}
		}
	}
	
    /**
     * Gets the velocity of the bolt entity from the crossbow's charge
     */
    public static float getBoltVelocity()
    {
    	return 1.5f;
    }
    
    protected void spawnProjectile(ItemStack crossbow, BoltItem boltItem, ItemStack boltStack, World worldIn, PlayerEntity player, boolean creativeOrInfinite, float inaccuracyModifier, float projectileAngle)
    {
    	BoltEntity entityBolt = boltItem.createBolt(worldIn, boltStack, player);
    	
    	entityBolt.setIsCritical(true);
    	entityBolt.setHitSound(SoundEvents.ITEM_CROSSBOW_HIT);
    	entityBolt.setShotFromCrossbow(true);
        int pierceLvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.PIERCING, crossbow);
    	if(pierceLvl > 0)
    		entityBolt.setPierceLevel((byte)pierceLvl);
        
    	Quaternion quat = new Quaternion(new Vector3f(player.getUpVector(1.0f)), projectileAngle, true);
    	Vector3f velocityVec = new Vector3f(player.getLook(1.0f));
    	velocityVec.transform(quat);/*.func_214905_a(quat);*/
    	//entityBolt.shoot(velocityVec.getX(), velocityVec.getY(), velocityVec.getZ(), getBoltVelocity() * 3.0f, inaccuracyModifier);
        entityBolt.setDirectionAndMovement(player, player.rotationPitch, player.rotationYaw, 0.0F, getBoltVelocity() * 3.0F, inaccuracyModifier);

        if(material.hasAnyWeaponTrait())
        {
        	for(WeaponTrait trait : material.getAllWeaponTraits())
        	{
        		if(trait.isRangedTrait() && trait.getRangedCallback() != null)
        		{
        			trait.getRangedCallback().onProjectileSpawn(material, entityBolt);
        		}
        	}
        }
        
        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, crossbow);

        if (j > 0)
        {
            entityBolt.setDamage(entityBolt.getDamage() + j * 0.5D + 0.5D);
        }

        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, crossbow);

        if (k > 0)
        {
            entityBolt.setKnockbackStrength(k);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, crossbow) > 0)
        {
            entityBolt.setFire(100);
        }

        if (creativeOrInfinite || projectileAngle != 0.0f)
        {
            entityBolt.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
        }

        worldIn.addEntity(entityBolt);
    }
    
    /*public int getArrowSlotToUseFirst(@Nonnull IItemHandler quiverHandler)
	{
		for(int i = 0; i < quiverHandler.getSlots(); i++)
		{
			ItemStack stack = quiverHandler.getStackInSlot(i);
			if(!stack.isEmpty())
				return i;
		}
		
		return -1;
	}*/

    /**
     * How long it takes to use or consume an item
     */
/*    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
    	if(!NBTHelper.getBoolean(stack, nbtIsLoaded))
    		return ConfigHandler.crossbowTicksToLoad;
        return 72000;
    }
*/    
    /**
     * returns the action that specifies what animation to play when the items is being used
     */
/*    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
    	if(!NBTHelper.getBoolean(stack, nbtIsLoaded))
    		return EnumAction.NONE;
        return EnumAction.BOW;
    }
*/
    
    @Override
    public UseAction getUseAction(ItemStack stack) 
    {
    	if(!stack.getOrCreateTag().getBoolean(NBT_CHARGED))
    		return UseAction.CROSSBOW;
    	return UseAction.BOW;
    }
    
/*    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
    	ItemStack stack = playerIn.getHeldItem(handIn);
        boolean flag = false;
        	
        flag = findAmmo(playerIn) != null;

        if (!playerIn.capabilities.isCreativeMode && !flag && !NBTHelper.getBoolean(stack, nbtIsLoaded) && EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) == 0)
        {
            return !flag ? new ActionResult(EnumActionResult.FAIL, stack) : new ActionResult(EnumActionResult.PASS, stack);
        }
        playerIn.setActiveHand(handIn);
        return new ActionResult(EnumActionResult.SUCCESS, stack);
    }
*/	
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
    	ItemStack stack = playerIn.getHeldItem(handIn);
    	ItemStack ammoStack = playerIn.findAmmo(stack);
    	boolean hasAmmo = !ammoStack.isEmpty();
    	
    	if(!playerIn.abilities.isCreativeMode && !hasAmmo && !stack.getOrCreateTag().getBoolean(NBT_CHARGED) && EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) == 0)
    	{
    		return !hasAmmo ? ActionResult.resultFail(stack) : ActionResult.resultPass(stack);
    	}
    	playerIn.setActiveHand(handIn);
    	return ActionResult.resultConsume(stack);
    }
    
    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) 
    {
    	// Play loading sounds as necessary
    	if(!worldIn.isRemote && !stack.getOrCreateTag().getBoolean(NBT_CHARGED) && livingEntityIn instanceof PlayerEntity)
    	{
	    	float loadTicks = getLoadProgress(stack, livingEntityIn);
	    	SoundEvent loadingSound = null;
	    	if(loadTicks == 0.0f)
	    		loadingSound = SoundEvents.ITEM_CROSSBOW_LOADING_START;
	    	else if(loadTicks == 0.5f || loadTicks == 0.9f)
	    		loadingSound = SoundEvents.ITEM_CROSSBOW_LOADING_MIDDLE;
	    	if(loadingSound != null)
	    		worldIn.playSound((PlayerEntity)null, livingEntityIn.getPosX(), livingEntityIn.getPosY(), livingEntityIn.getPosZ(), loadingSound, SoundCategory.PLAYERS, 0.5f, 1.0f);
	    	
    	}
    }
    
    @Override
	public int getItemEnchantability(ItemStack stack)
	{
		return material != null ? material.getEnchantability() : 1;
	}
    
    /**
     * Return whether this item is repairable in an anvil.
     */
/*	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
		if(toRepair.isEmpty() || repair.isEmpty())
    		return false;
    	if( material.doesOreDictMatch(repair))
    		return true;
    	return super.getIsRepairable(toRepair, repair);
    }
*/    
    
    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) 
    {
    	return this.material.getRepairMaterial().test(repair) /*|| super.getIsRepairable(toRepair, repair)*/;
    	//return super.getIsRepairable(toRepair, repair);
    }
    
/*    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
		if(displayName != null)
		{
			String name = I18n.translateToLocalFormatted(String.format("item.%s:%s.name", Reference.ModID, displayName), I18n.translateToLocal(material.getFullUnlocName()));
			return name;
		}
		return super.getItemStackDisplayName(stack);
		
        //return ("" + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }
*/	
    
    /**
     * Called to get the Mod ID of the mod that *created* the ItemStack,
     * instead of the real Mod ID that *registered* it.
     *
     * For example the Forge Universal Bucket creates a subitem for each modded fluid,
     * and it returns the modded fluid's Mod ID here.
     *
     * Mods that register subitems for other mods can override this.
     * Informational mods can call it to show the mod that created the item.
     *
     * @param itemStack the ItemStack to check
     * @return the Mod ID for the ItemStack, or
     *         null when there is no specially associated mod and {@link #getRegistryName()} would return null.
     */
/*    @Nullable
    public String getCreatorModId(ItemStack itemStack)
    {
    	if(modId != null)
            return modId;
    	return super.getCreatorModId(itemStack);
    }
*/	

	@Override
	public ITextComponent getDisplayName(ItemStack stack) 
	{
		if(customDisplayName == null)
			return super.getDisplayName(stack);
		return new TranslationTextComponent(customDisplayName, material.translateName());
	}
	
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
    {
    	if(doCraftCheck && worldIn != null)
    	{
    		if(material.getModId() == ModSpartanWeaponry.ID && material.getRepairMaterial() == Ingredient.EMPTY)
	    			canBeCrafted = false;
    		
	    	doCraftCheck = false;
    	}

    	if(!canBeCrafted)
    		tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.uncraftable.missing_material", ModSpartanWeaponry.ID), material.getTagName()).mergeStyle(TextFormatting.RED));
    	
    	if(stack.getOrCreateTag().contains(NBT_CHARGED))
    	{
    		ItemStack bolt = ItemStack.read(stack.getTag().getCompound(NBT_PROJECTILE));
    		if(!bolt.isEmpty())
    		{
	    		tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.heavy_crossbow.loaded_bolt", ModSpartanWeaponry.ID), String.format("[%s x%d]", TextFormatting.AQUA.toString() + bolt.getDisplayName().getString() + TextFormatting.WHITE.toString(), bolt.getCount())));
	        	tooltip.add(new StringTextComponent(""));
    		}
    	}

		boolean isShiftPressed = Screen.hasShiftDown();
    	if(material.hasAnyWeaponTrait())
    	{
			if(isShiftPressed)
				tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.traits", ModSpartanWeaponry.ID), new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".showing_details").mergeStyle(TextFormatting.DARK_GRAY)).mergeStyle(TextFormatting.GOLD));
			else
				tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.traits", ModSpartanWeaponry.ID), new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".show_details", TextFormatting.DARK_AQUA.toString() + "SHIFT").mergeStyle(TextFormatting.DARK_GRAY)).mergeStyle(TextFormatting.GOLD));
			List<WeaponTrait> rangedTraits = material.getAllWeaponTraits().stream().filter((trait) -> trait.isRangedTrait()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			if(!rangedTraits.isEmpty())
				tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.traits.material_bonus", ModSpartanWeaponry.ID)).mergeStyle(TextFormatting.AQUA));
    		for(WeaponTrait matTrait : rangedTraits)
    		{
    			if(matTrait.isRangedTrait())
    				matTrait.addTooltip(stack, tooltip, isShiftPressed);
    		}
        	tooltip.add(new StringTextComponent(""));
    	}
    	
    	if(isShiftPressed)
    	{
			tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.description", ModSpartanWeaponry.ID), new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".showing_details").mergeStyle(TextFormatting.DARK_GRAY)).mergeStyle(TextFormatting.GOLD));
			tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.heavy_crossbow.desc", ModSpartanWeaponry.ID)).mergeStyle(TextFormatting.GRAY, TextFormatting.ITALIC));
    		tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.heavy_crossbow.desc_2", ModSpartanWeaponry.ID)).mergeStyle(TextFormatting.GRAY, TextFormatting.ITALIC));
    		tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.heavy_crossbow.desc_3", ModSpartanWeaponry.ID)).mergeStyle(TextFormatting.GRAY, TextFormatting.ITALIC));
    	}
    	else
			tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.description", ModSpartanWeaponry.ID), new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".show_details", TextFormatting.AQUA.toString() + "SHIFT").mergeStyle(TextFormatting.DARK_GRAY)).mergeStyle(TextFormatting.GOLD));
    	
    	tooltip.add(new StringTextComponent(""));
    	tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.modifiers.ammo.type", ModSpartanWeaponry.ID), new TranslationTextComponent(String.format("tooltip.%s.modifiers.ammo.bolt", ModSpartanWeaponry.ID)).mergeStyle(TextFormatting.GRAY)).mergeStyle(TextFormatting.DARK_AQUA));
    	tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.modifiers.heavy_crossbow.load_time", ModSpartanWeaponry.ID), new TranslationTextComponent(String.format("tooltip.%s.modifiers.heavy_crossbow.load_time.value", ModSpartanWeaponry.ID), (float)getFullLoadTicks(stack) / 20.0f).mergeStyle(TextFormatting.GRAY)).mergeStyle(TextFormatting.DARK_AQUA));
    	tooltip.add(new TranslationTextComponent(String.format("tooltip.%s.modifiers.heavy_crossbow.aim_time", ModSpartanWeaponry.ID), new TranslationTextComponent(String.format("tooltip.%s.modifiers.heavy_crossbow.aim_time.value", ModSpartanWeaponry.ID), (float)getAimTicks(stack) / 20.0f).mergeStyle(TextFormatting.GRAY)).mergeStyle(TextFormatting.DARK_AQUA));
    	tooltip.add(new StringTextComponent(""));
    }
    
/*    @Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
    	if(callback != null)
    		callback.onCreateItem(material, stack);
		super.onCreated(stack, worldIn, playerIn);
	}
*/
/*	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) 
	{
		if(this.isInCreativeTab(tab))
		{
			ItemStack stack = new ItemStack(this);
			if(callback != null)
				callback.onCreateItem(material, stack);
			items.add(stack);
		}
	}
*/	
/*	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) 
	{
		if(callback != null && entityIn instanceof EntityLivingBase)
			callback.onItemUpdate(material, stack, worldIn, (EntityLivingBase)entityIn, itemSlot, isSelected);
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
	}
*/
    
    @Override
    public ITextComponent getHighlightTip(ItemStack item, ITextComponent displayName) 
    {
    	if(item.hasTag())
		{
    		ItemStack bolt = ItemStack.read(item.getTag().getCompound(NBT_PROJECTILE));
    		if(!bolt.isEmpty())
    		{
    			return new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".highlight_heavy_crossbow", displayName, bolt.getDisplayName(), bolt.getCount());
    		}
//    			return String.format("%s [%s x%d]", displayName, bolt.getDisplayName(), bolt.getCount());
		}

    	return super.getHighlightTip(item, displayName);
    }
	
	// ---- ---- ---- ---- ---- ---- ---- ----
	// IHudLoadState
	// ---- ---- ---- ---- ---- ---- ---- ----
	@Override
	public boolean isLoaded(ItemStack stack)
	{
		return stack.getTag() != null && stack.getTag().getBoolean(NBT_CHARGED);
	}

	@Override
	public float getLoadProgress(ItemStack stack, LivingEntity living)
	{
		return !isLoaded(stack) ? MathHelper.clamp((float)getLoadingTicks(stack, living) / (float)getFullLoadTicks(stack), 0.0f, 1.0f) : 0.0f;
	}
	
	// ---- ---- ---- ---- ---- ---- ---- ----
	// Internal methods
	// ---- ---- ---- ---- ---- ---- ---- ----
	
	public int getFullLoadTicks(ItemStack stack)
	{
		int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.QUICK_CHARGE, stack);
		return MathHelper.clamp(loadTicks - 5 * i, 0, loadTicks);
	}
	
	public int getLoadingTicks(ItemStack stack, LivingEntity living)
	{
		return stack.getUseDuration() - living.getItemInUseCount();
	}
	
	public int getAimTicks(ItemStack stack)
	{
		int i = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.HEAVY_CROSSBOW_SHARPSHOOTER, stack);
		return MathHelper.clamp(aimTicks - 2 * i, 0, aimTicks);
	}

    @OnlyIn(Dist.CLIENT)
	@Override
	public HudElementCrosshair createHudElement() 
	{
		return new HudElementCrosshairHeavyCrossbow();
	}

    @OnlyIn(Dist.CLIENT)
	@Override
	public ResourceLocation getType()
	{
		return HudElementCrosshairHeavyCrossbow.TYPE;
	}

/*	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) 
	{
		//return super.canApplyAtEnchantingTable(stack, enchantment);
		return enchantment == Enchantments.POWER || enchantment == Enchantments.PUNCH || enchantment == Enchantments.FLAME || 
				enchantment == Enchantments.INFINITY || super.canApplyAtEnchantingTable(stack, enchantment);
	}
*/
}
