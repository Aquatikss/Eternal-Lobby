package com.aquamobs.blugu;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class GuiItem {

    private static Material material;
    private static int amount;
    private static Component name;

    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public GuiItem() {}

    public ItemStack asItemStack() {

        name = name != null ? name : Component.text("Default Item Name");

        return ItemStack.of(material, amount).withCustomName(name);
    }

    public GuiItem setMaterial(Material material) {
        GuiItem.material = material;
        return this;
    }

    public GuiItem setAmount(int amount) {
        GuiItem.amount = amount;
        return this;
    }

    public GuiItem setName(String name) {
        GuiItem.name = miniMessage.deserialize(name);
        return this;
    }

    public GuiItem setLore(int index, String... lore) {

        return this;
    }

    public String[] getLore() {
        // Placeholder for getting lore, actual implementation may vary
        return new String[0];
    }

    public Component getName() {

        return name != null ? name : Component.text(material.name());
    }

    public int getAmount() {
        return amount;
    }

    public Material getMaterial() {
        return material;
    }

}
