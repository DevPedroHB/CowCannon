package dev.pedrohb.cowcannon.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import dev.pedrohb.cowcannon.models.Region;
import dev.pedrohb.cowcannon.models.Regions;
import dev.pedrohb.cowcannon.utils.Common;

public final class RegionListener implements Listener {

  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    Player player = event.getPlayer();
    Regions regions = Regions.getInstance();
    Region standingIn = regions.findRegion(event.getBlock().getLocation());

    if (standingIn != null) {
      Common.tell(player, "Cannot break blocks in protected region " + standingIn.getName() + ".");

      event.setCancelled(true);
    }
  }
}
