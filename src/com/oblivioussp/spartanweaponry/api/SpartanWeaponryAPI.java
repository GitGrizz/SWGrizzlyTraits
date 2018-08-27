package com.oblivioussp.spartanweaponry.api;

import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SpartanWeaponryAPI 
{
	public static final String ModID = "spartanweaponry";
	
	//	public static ArrayList<ToolMaterialEx> materials = new ArrayList<ToolMaterialEx>();
	
	/**
	 * Use this to access internal methods for the mod. NOTE: DO NOT OVERRIDE THIS AS YOU WILL BREAK THE ENTIRE API! YOU HAVE BEEN WARNED!!!
	 */
	public static IInternalMethodHandler internalHandler = new DummyInternalMethodHandler();

	
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	// Weapon Creation functions
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	
	/**
	 * Creates a new dagger, using the specified material and adds new Weapon Properties to it. Gives the new item the registry name of "item.[modId].dagger_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param damage The damage of this weapon. Can be used to create a config file on the addon mod's end to customise damage
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createDagger(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties)
	{
		return internalHandler.addDagger(material, modId, damage, tab, properties);
	}
	
	/**
	 * Creates a new longsword, using the specified material and adds new Weapon Properties to it. Gives the new item the registry name of "item.[modId].longsword_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param damage The damage of this weapon. Can be used to create a config file on the addon mod's end to customise damage
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createLongsword(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties)
	{
		return internalHandler.addLongsword(material, modId, damage, tab, properties);
	}
	
	/**
	 * Creates a new katana, using the specified material and adds new Weapon Properties to it. Gives the new item the registry name of "item.[modId].katana_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param damage The damage of this weapon. Can be used to create a config file on the addon mod's end to customise damage
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createKatana(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties)
	{
		return internalHandler.addKatana(material, modId, damage, tab, properties);
	}
	
	/**
	 * Creates a new saber, using the specified material and adds new Weapon Properties to it. Gives the new item the registry name of "item.[modId].saber_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param damage The damage of this weapon. Can be used to create a config file on the addon mod's end to customise damage
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createSaber(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties)
	{
		return internalHandler.addSaber(material, modId, damage, tab, properties);
	}
	
	/**
	 * Creates a new rapier, using the specified material and adds new Weapon Properties to it. Gives the new item the registry name of "item.[modId].rapier_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param damage The damage of this weapon. Can be used to create a config file on the addon mod's end to customise damage
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createRapier(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties)
	{
		return internalHandler.addRapier(material, modId, damage, tab, properties);
	}
	
	/**
	 * Creates a new greatsword, using the specified material and adds new Weapon Properties to it. Gives the new item the registry name of "item.[modId].greatsword_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param damage The damage of this weapon. Can be used to create a config file on the addon mod's end to customise damage
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createGreatsword(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties)
	{
		return internalHandler.addGreatsword(material, modId, damage, tab, properties);
	}
	
	/**
	 * Creates a new hammer, using the specified material and adds new Weapon Properties to it. Gives the new item the registry name of "item.[modId].hammer_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param damage The damage of this weapon. Can be used to create a config file on the addon mod's end to customise damage
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createHammer(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties)
	{
		return internalHandler.addHammer(material, modId, damage, tab, properties);
	}
	
	/**
	 * Creates a new warhammer, using the specified material and adds new Weapon Properties to it. Gives the new item the registry name of "item.[modId].warhammer_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param damage The damage of this weapon. Can be used to create a config file on the addon mod's end to customise damage
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createWarhammer(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties)
	{
		return internalHandler.addWarhammer(material, modId, damage, tab, properties);
	}
	
	/**
	 * Creates a new spear, using the specified material and adds new Weapon Properties to it. Gives the new item the registry name of "item.[modId].spear_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param damage The damage of this weapon. Can be used to create a config file on the addon mod's end to customise damage
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createSpear(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties)
	{
		return internalHandler.addSpear(material, modId, damage, tab, properties);
	}
	
	/**
	 * Creates a new halberd, using the specified material and adds new Weapon Properties to it. Gives the new item the registry name of "item.[modId].halberd_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param damage The damage of this weapon. Can be used to create a config file on the addon mod's end to customise damage
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createHalberd(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties)
	{
		return internalHandler.addHalberd(material, modId, damage, tab, properties);
	}
	
	/**
	 * Creates a new pike, using the specified material and adds new Weapon Properties to it. Gives the new item the registry name of "item.[modId].pike_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param damage The damage of this weapon. Can be used to create a config file on the addon mod's end to customise damage
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createPike(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties)
	{
		return internalHandler.addPike(material, modId, damage, tab, properties);
	}
	
	/**
	 * Creates a new lance, using the specified material and adds new Weapon Properties to it. Gives the new item the registry name of "item.[modId].lance_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param damage The damage of this weapon. Can be used to create a config file on the addon mod's end to customise damage
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createLance(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties)
	{
		return internalHandler.addLance(material, modId, damage, tab, properties);
	}
	
	/**
	 * Creates a new longbow, using the specified material. Give the new item the registry name of "item.[modId].longbow_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param callback A callback method to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createLongbow(ToolMaterialEx material, String modId, CreativeTabs tab, IWeaponCallback callback)
	{
		return internalHandler.addLongbow(material, modId, tab, callback);
	}
	
	/**
	 * Creates a new crossbow, using the specified material. Give the new item the registry name of "item.[modId].longbow_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param callback A callback method to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createCrossbow(ToolMaterialEx material, String modId, CreativeTabs tab, IWeaponCallback callback)
	{
		return internalHandler.addCrossbow(material, modId, tab, callback);
	}
	
	/**
	 * Creates a new throwing knife, using the specified material and adds new Weapon Properties to it. Gives the new item the registry name of "item.[modId].throwing_knife_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param damage The damage of this weapon. Can be used to create a config file on the addon mod's end to customise damage
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createThrowingKnife(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties)
	{
		return internalHandler.addThrowingKnife(material, modId, damage, tab, properties);
	}
	
	/**
	 * Creates a new throwing axe, using the specified material and adds new Weapon Properties to it. Gives the new item the registry name of "item.[modId].throwing_axe_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param damage The damage of this weapon. Can be used to create a config file on the addon mod's end to customise damage
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createThrowingAxe(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties)
	{
		return internalHandler.addThrowingAxe(material, modId, damage, tab, properties);
	}
	
	/**
	 * Creates a new javelin, using the specified material and adds new Weapon Properties to it. Gives the new item the registry name of "item.[modId].javelin_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param damage The damage of this weapon. Can be used to create a config file on the addon mod's end to customise damage
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createJavelin(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties)
	{
		return internalHandler.addJavelin(material, modId, damage, tab, properties);
	}
	
	/**
	 * Creates a new boomerang, using the specified material and adds new Weapon Properties to it. Gives the new item the registry name of "item.[modId].boomerang_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param damage The damage of this weapon. Can be used to create a config file on the addon mod's end to customise damage
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createBoomerang(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties)
	{
		return internalHandler.addBoomerang(material, modId, damage, tab, properties);
	}
	
	/**
	 * Creates a new battleaxe, using the specified material and adds new Weapon Properties to it. Gives the new item the registry name of "item.[modId].battleaxe_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param damage The damage of this weapon. Can be used to create a config file on the addon mod's end to customise damage
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createBattleaxe(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties)
	{
		return internalHandler.addBattleaxe(material, modId, damage, tab, properties);
	}
	
	/**
	 * Creates a new mace, using the specified material and adds new Weapon Properties to it. Gives the new item the registry name of "item.[modId].mace_[material.unlocName]". The caller is responsible for registering the weapon item, model and recipe
	 * @param material The Material that the weapon is made of
	 * @param modId The mod ID. Used to correctly localize the weapon's material
	 * @param damage The damage of this weapon. Can be used to create a config file on the addon mod's end to customise damage
	 * @param tab The Creative Tab that this weapon will appear in
	 * @param properties Additional Weapon Properties to add to the weapon
	 * @return The newly created weapon. Will return 'null' if the config option for this weapon has been disabled. Remember to null-check this before registering!
	 */
	public static Item createMace(ToolMaterialEx material, String modId, float damage, CreativeTabs tab, WeaponProperty... properties)
	{
		return internalHandler.addMace(material, modId, damage, tab, properties);
	}
	
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	// Registration functions
	//---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
	
	/**
	 * Registers the Item to use the Spartan Weaponry colour handler. This means that the second and third layers of the item model will use the tint primary/secondary respective colours provided by the ToolMaterialEx.
	 * Remember to call this method during the Item Registry or the FMLInitialization Event!
	 * @param material The tool material to use for this. Contains the colours for the materials within.
	 * @param items All the items to register
	 */
	public static void registerColourHandler(ToolMaterialEx material, Item... items)
	{
		for(Item item : items)
			internalHandler.registerColourHandler(item, material);
	}
	
	/**
	 * Registers the Item to use SpartanWeaponry's registration of models.
	 * @param item The Item to register
	 * @param modId The mod ID for the mod calling this
	 * @param modelName The model name, e.g. "daggerEnderium" for the item model in "assets/[ModID]/models/item/daggerEnderium.json"
	 */
	public static void registerItemModelRender(Item item, String modId, String modelName)
	{
		internalHandler.registerItemModelRender(item, modId, modelName);
	}
	
	
	public static void registerItemModelRender(Item item)
	{
		internalHandler.registerItemModelRender(item);
	}
}