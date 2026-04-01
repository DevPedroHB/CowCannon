package dev.pedrohb.cowcannon.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.event.SimpleListener;

@AutoRegister
public class PlayerInteractEntityListener extends SimpleListener<PlayerInteractEntityEvent> {

  public PlayerInteractEntityListener(Class<PlayerInteractEntityEvent> event) {
    super(event);
  }

  @Override
  protected void execute(PlayerInteractEntityEvent event) {
    final Entity entity = event.getRightClicked();

    if (entity.getType() != EntityType.COW) {
      return;
    }

    entity.getWorld().createExplosion(entity.getLocation(), 5);
  }
}
