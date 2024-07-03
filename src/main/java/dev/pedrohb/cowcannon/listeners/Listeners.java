package dev.pedrohb.cowcannon.listeners;

import dev.pedrohb.cowcannon.CowCannon;

public class Listeners {

  public static void setupListeners(CowCannon cowCannon, Integer version) {
    cowCannon.getServer().getPluginManager().registerEvents(new EntityListener(), cowCannon);
    cowCannon.getServer().getPluginManager().registerEvents(new GuiListener(), cowCannon);
    cowCannon.getServer().getPluginManager().registerEvents(new LaserPointerListener(), cowCannon);
    cowCannon.getServer().getPluginManager().registerEvents(new ChatListener(), cowCannon);
    cowCannon.getServer().getPluginManager().registerEvents(new PlayerListener(), cowCannon);
    cowCannon.getServer().getPluginManager().registerEvents(new InventoryListener(), cowCannon);

    if (version >= 14) {
      cowCannon.getServer().getPluginManager().registerEvents(new CrawlListener(), cowCannon);
    }
  }
}
