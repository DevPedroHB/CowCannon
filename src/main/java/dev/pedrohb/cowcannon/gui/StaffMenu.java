package dev.pedrohb.cowcannon.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;

public class StaffMenu extends Menu {

  public StaffMenu() {
    this.setTitle("Staff Menu");

    this.addButton(new Button(9 + 4) {
      @Override
      public ItemStack getItem() {
        return ItemCreator.of(CompMaterial.ARROW,
            "List Online Player",
            "",
            "Click me to list",
            "online players").make();
      }

      @Override
      public void onClick(Player player) {
        new ListPlayersMenu().diplayTo(player);
      }
    });
  }

  private class ListPlayersMenu extends Menu {

    public ListPlayersMenu() {
      super(StaffMenu.this);

      this.setSize(9 * 6);
      this.setTitle("Listing Players");

      int startingSlot = 0;

      for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
        this.addButton(new Button(startingSlot++) {
          @Override
          public ItemStack getItem() {
            return ItemCreator.of(CompMaterial.PLAYER_HEAD).skullOwner(onlinePlayer.getName()).make();
          }

          @Override
          public void onClick(Player player) {
            player.sendMessage("You clicked player " + onlinePlayer.getName());
          }
        });
      }
    }
  }
}
