package dev.cammiescorner.arcanuscontinuum.common.registry;

import dev.cammiescorner.arcanuscontinuum.Arcanus;
import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.RegistrySupplier;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;

public class ArcanusArmourMaterials {
	public static final RegistryHandler<ArmorMaterial> MATERIALS = RegistryHandler.create(Registries.ARMOR_MATERIAL, Arcanus.MOD_ID);

	public static final RegistrySupplier<ArmorMaterial> WIZARD = MATERIALS.register("WIZARD", () -> new ArmorMaterial(
		Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
			map.put(ArmorItem.Type.BOOTS, 1);
			map.put(ArmorItem.Type.LEGGINGS, 4);
			map.put(ArmorItem.Type.CHESTPLATE, 5);
			map.put(ArmorItem.Type.HELMET, 2);
			map.put(ArmorItem.Type.BODY, 5);
		}),
		25,
		SoundEvents.ARMOR_EQUIP_LEATHER,
		() -> Ingredient.of(Items.LEATHER),
		List.of(),
		0f,
		0f
	));
	public static final RegistrySupplier<ArmorMaterial> BATTLE_MAGE = MATERIALS.register("battle_mage", () -> new ArmorMaterial(
		Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
			map.put(ArmorItem.Type.BOOTS, 2);
			map.put(ArmorItem.Type.LEGGINGS, 5);
			map.put(ArmorItem.Type.CHESTPLATE, 6);
			map.put(ArmorItem.Type.HELMET, 3);
			map.put(ArmorItem.Type.BODY, 6);
		}),
		25,
		SoundEvents.ARMOR_EQUIP_LEATHER,
		() -> Ingredient.of(Items.AMETHYST_SHARD),
		List.of(),
		0f,
		0f
	));
}
