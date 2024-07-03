package dev.pedrohb.cowcannon.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import dev.pedrohb.cowcannon.utils.Keys;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class ItemPickupTask implements Runnable {

  @Getter
  private static final ItemPickupTask instance = new ItemPickupTask();

  @Override
  public void run() {
    for (Player player : Bukkit.getOnlinePlayers()) {
      ArmorStand armorStand = null;

      for (Entity nearby : player.getWorld().getNearbyEntities(player.getLocation(), 3, 3, 3,
          ArmorStand.class::isInstance)) {
        if (nearby.getLocation().subtract(0.5, -0.8, -0.5).distance(player.getLocation()) < 1.5) {
          armorStand = (ArmorStand) nearby;

          break;
        }
      }

      if (armorStand != null) {
        final Long itemDropTime = armorStand.getPersistentDataContainer().get(Keys.ITEM_DROP_TIME,
            PersistentDataType.LONG);

        if (itemDropTime != null && System.currentTimeMillis() - itemDropTime > 800) { // collect after 0.8 seconds
          final ItemStack item = armorStand.getEquipment().getItemInMainHand();

          if (item.getType() != Material.AIR && player.getInventory().firstEmpty() != -1) {
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1F, 1F);
            player.getInventory().addItem(item);

            armorStand.remove();
          }
        }
      }
    }
  }
}
