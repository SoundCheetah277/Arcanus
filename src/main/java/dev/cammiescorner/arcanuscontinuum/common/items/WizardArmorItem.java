package dev.cammiescorner.arcanuscontinuum.common.items;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import dev.cammiescorner.arcanuscontinuum.Arcanus;
import dev.cammiescorner.arcanuscontinuum.api.entities.ArcanusEntityAttributes;
import dev.cammiescorner.arcanuscontinuum.common.registry.ArcanusComponents;
import net.minecraft.core.Holder;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;

import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

public class WizardArmorItem extends ArmorItem {
	private final Supplier<ItemAttributeModifiers> defaultModifiers;

	public WizardArmorItem(Holder<ArmorMaterial> armorMaterial, Type equipmentSlot) {
		super(armorMaterial, equipmentSlot, new Item.Properties().stacksTo(1));

		this.defaultModifiers = Suppliers.memoize(() -> {
			ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
			EquipmentSlotGroup equipmentSlotGroup = EquipmentSlotGroup.bySlot(type.getSlot());
			ResourceLocation resourceLocation = Arcanus.id("armor." + type.getName());
			float knockbackResist = material.value().knockbackResistance();

			builder.add(Attributes.ARMOR, new AttributeModifier(resourceLocation, material.value().getDefense(type), AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);
			builder.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(resourceLocation, material.value().toughness(), AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);

			if(knockbackResist > 0f)
				builder.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(resourceLocation, knockbackResist, AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);

			return builder.build();
		});

		CauldronInteraction.WATER.map().put(this, CauldronInteraction.DYED_ITEM);
	}

	@Override
	public ItemAttributeModifiers getDefaultAttributeModifiers() {
		return defaultModifiers.get();
	}
}
