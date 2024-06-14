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

public class EntityListener implements Listener {

  private static final Settings settings = Settings.getInstance();

//  private final Map<UUID, PermissionAttachment> permissions = new HashMap<>();

  @EventHandler
  public void onEntityRightClick(PlayerInteractEntityEvent event) {
    if (event.getHand() != EquipmentSlot.HAND) {
      return;
    }

    Player player = event.getPlayer();
    Entity entity = event.getRightClicked();

    /*if (permissions.containsKey(player.getUniqueId())) {
      PermissionAttachment permission = permissions.remove(player.getUniqueId());

      player.removeAttachment(permission);

      player.sendMessage("You no longer have the perm.");
    } else {
      PermissionAttachment permission = player.addAttachment(CowCannon.getInstance(), "cowcannon.cow", true);

      permissions.put(player.getUniqueId(), permission);

      player.sendMessage("You now have the perm.");
    }*/

    if (player.getItemInHand().getItemMeta() != null) {
      PersistentDataContainer entityContainer = entity.getPersistentDataContainer();
      PersistentDataContainer handItemContainer = player.getItemInHand().getItemMeta().getPersistentDataContainer();

      if (entity.getType() == settings.getEntityType() && entityContainer.has(Keys.CUSTOM_COW) && handItemContainer.has(Keys.CUSTOM_BUCKET)) {
        if (!player.hasPermission("cowcannon.cow.use")) {
          player.sendMessage("You don't have permission to milk cows.");

          return;
        }

        entity.getWorld().createExplosion(entity.getLocation(), 2.F);
      }
    }
  }
}
