package io.github.rysefoxx.content;

import io.github.rysefoxx.pagination.InventoryContents;
import io.github.rysefoxx.pagination.PreviousInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnegative;
import java.util.ArrayList;

public class BaseInventoryProvider implements InventoryProvider {

    @Override
    public void init(Player player, InventoryContents contents) {
        ArrayList<PreviousInventory> previousInventoryRecord = contents.getData("previousMenu");
        if (previousInventoryRecord != null && previousInventoryRecord.size() != 0) {
            contents.set(contents.size() - 1, IntelligentItem.of(
                getItem("§cRetour"),
                inventoryClickEvent -> {
                    if (previousInventoryRecord.size() == 0)
                        return;
                    previousInventoryRecord.get(previousInventoryRecord.size() - 1).previousInventory().open(player, previousInventoryRecord.get(previousInventoryRecord.size() - 1).previousData());
                    previousInventoryRecord.remove(previousInventoryRecord.size() - 1);
                }));
            return;
        }
        contents.set(contents.size() - 1, IntelligentItem.of(
            getItem("§cFermer"),
            inventoryClickEvent -> inventoryClickEvent.getWhoClicked().closeInventory()));
    }

    private ItemStack getItem(String title) {
        ItemStack res = new ItemStack(Material.BARRIER);
        ItemMeta meta = res.getItemMeta();
        meta.setDisplayName(title);
        res.setItemMeta(meta);
        return res;
    }

    public void updateItem(InventoryContents contents, @Nonnegative int slot, @NotNull IntelligentItem intelligentItem) {
        if (contents.get(slot).isPresent())
            contents.update(slot, intelligentItem);
        else
            contents.set(slot, intelligentItem);
    }
}
