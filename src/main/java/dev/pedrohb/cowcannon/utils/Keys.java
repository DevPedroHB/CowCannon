package dev.pedrohb.cowcannon.utils;

import dev.pedrohb.cowcannon.CowCannon;
import org.bukkit.NamespacedKey;

public class Keys {
  private static final CowCannon cowCannon = CowCannon.getInstance();

  public static final NamespacedKey CUSTOM_COW = new NamespacedKey(cowCannon, "CustomCow");
  public static final NamespacedKey CUSTOM_BUCKET = new NamespacedKey(cowCannon, "CustomBucket");
}
