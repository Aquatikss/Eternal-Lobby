package com.aquamobs.blugu;

import net.minestom.server.entity.Player;
import net.minestom.server.inventory.AbstractInventory;
import net.minestom.server.inventory.Inventory;

abstract class Gui {

    public void open(Player player, Inventory inventory) {
        player.openInventory(inventory);
    }

    public Inventory getInventory(Player player) {
        AbstractInventory abstractInventory = player.getOpenInventory();
        return abstractInventory instanceof Inventory ? (Inventory) abstractInventory : null;
    }

    public void close(Player player) {
        player.closeInventory();
    }

    public boolean addItem(Inventory inventory, int slot, GuiItem item) {
        if (inventory == null) return false;
        inventory.setItemStack(slot, item.asItemStack());
        return true;
    }

}