package dev.pedrohb.cowcannon.listeners;

import dev.pedrohb.cowcannon.configs.Settings;
import dev.pedrohb.cowcannon.utils.Keys;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataContainer;

public final class EntityListener implements Listener {

  @EventHandler
  public void onEntityRightClick(PlayerInteractEntityEvent event) {
    try {
      if (event.getHand() != EquipmentSlot.HAND) {
        return;
      }
    } catch (final Throwable t) {
      // Ignore
    }

    final Player player = event.getPlayer();
    final Entity entity = event.getRightClicked();

    if (player.getItemInHand().getItemMeta() != null) {
      try {
        final PersistentDataContainer entityContainer = entity.getPersistentDataContainer();
        final PersistentDataContainer handItemContainer = player.getItemInHand().getItemMeta().getPersistentDataContainer();

        if (entity.getType() == Settings.getInstance().getExplodingType() && entityContainer.has(Keys.CUSTOM_COW) && handItemContainer.has(Keys.CUSTOM_BUCKET)) {
          if (!player.hasPermission("cowcannon.cow.use")) {
            player.sendMessage("You don't have permission to milk cows ;)");

            return;
          }

          entity.getWorld().createExplosion(entity.getLocation(), 2.5F);
        }
      } catch (final LinkageError err) {
        // Ignore
      }
    }
  }
}
