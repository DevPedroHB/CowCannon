package dev.pedrohb.cowcannon.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.event.SimpleListener;
import org.mineacademy.fo.remain.CompEntityType;
import org.mineacademy.fo.remain.CompMaterial;
import org.mineacademy.fo.remain.CompMetadata;
import org.mineacademy.fo.remain.Remain;

import dev.pedrohb.cowcannon.model.MetadataKey;
import lombok.Getter;

@AutoRegister
public final class ExplodingCowInteractListener extends SimpleListener<PlayerInteractEntityEvent> {

  @Getter
  private static final ExplodingCowInteractListener instance = new ExplodingCowInteractListener();

  private ExplodingCowInteractListener() {
    super(PlayerInteractEntityEvent.class);
  }

  @Override
  protected void execute(PlayerInteractEntityEvent event) {
    if (!Remain.isInteractEventPrimaryHand(event)) {
      return;
    }

    final Entity entity = event.getRightClicked();

    if (!this.isValidExplodingCowInteraction(entity, event.getPlayer())) {
      return;
    }

    event.setCancelled(true);

    entity.getWorld().createExplosion(entity.getLocation(), 8.0F);
  }

  private boolean isValidExplodingCowInteraction(Entity entity, Player player) {
    if (entity.getType() != CompEntityType.COW) {
      return false;
    }

    if (!CompMetadata.hasMetadata(entity, MetadataKey.EXPLODING_COW)) {
      return false;
    }

    return player.getInventory().getItemInMainHand().getType() == CompMaterial.BUCKET.getMaterial();
  }
}
