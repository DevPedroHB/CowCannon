package dev.pedrohb.cowcannon.models;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import dev.pedrohb.cowcannon.utils.Common;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("deprecation")
@RequiredArgsConstructor
public final class Hologram {

  @Getter
  private final String[] lines;

  public void spawn(Location originLocation) {
    for (String line : lines) {
      ArmorStand stand = originLocation.getWorld().spawn(originLocation, ArmorStand.class);

      stand.setVisible(false);
      stand.setGravity(false);
      stand.setInvulnerable(true);
      stand.setCustomNameVisible(true);
      stand.setCustomName(Common.colorize(line));

      originLocation.subtract(0, 0.25, 0);
    }
  }
}
