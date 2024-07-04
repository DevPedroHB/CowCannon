package dev.pedrohb.cowcannon.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import dev.pedrohb.cowcannon.CowCannon;
import dev.pedrohb.cowcannon.gui.Button;
import dev.pedrohb.cowcannon.gui.Menu;

public class MenuListener implements Listener {

  @EventHandler
  public void onInventoryClick(InventoryClickEvent event) {
    Player dude = (Player) event.getWhoClicked();
    int slot = event.getSlot();

    if (dude.hasMetadata("CowMenuMetadata")) {
      Menu menu = (Menu) dude.getMetadata("CowMenuMetadata").get(0).value();

      for (Button button : menu.getButtons()) {
        if (button.getSlot() == slot) {
          button.onClick(dude);

          event.setCancelled(true);
        }
      }
    }
  }

  @EventHandler
  public void onInventoryClose(InventoryCloseEvent event) {
    Player dude = (Player) event.getPlayer();

    if (dude.hasMetadata("CowMenuMetadata")) {
      // Menu menu = (Menu) dude.getMetadata("CowMenuMetadata").get(0).value();

      // menu.onClose();

      dude.removeMetadata("CowMenuMetadata", CowCannon.getInstance());
    }
  }
}
