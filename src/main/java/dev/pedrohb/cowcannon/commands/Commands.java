package dev.pedrohb.cowcannon.commands;

import dev.pedrohb.cowcannon.CowCannon;

@SuppressWarnings("deprecation")
public class Commands {

  public static void setupCommands(CowCannon cowCannon, Integer version) {
    cowCannon.getCommand("cow").setExecutor(new CowCommand());
    cowCannon.getCommand("butterfly").setExecutor(new ButterflyCommand());
    cowCannon.getCommand("customitem").setExecutor(new CustomItemCommand());
    cowCannon.getCommand("gui").setExecutor(new GuiCommand());
    cowCannon.getCommand("economy").setExecutor(new EconomyCommand());
    cowCannon.getCommand("locale").setExecutor(new LocaleCommand());
    cowCannon.getCommand("tag").setExecutor(new TagCommand());
    cowCannon.getCommand("hologram").setExecutor(new HologramCommand());
    cowCannon.getCommand("vanish").setExecutor(new VanishCommand());
    cowCannon.getCommand("fly").setExecutor(new FlyCommand());

    if (version >= 19) {
      cowCannon.getCommand("displayentity").setExecutor(new DisplayEntityCommand());
    }

    if (version >= 14) {
      cowCannon.getCommand("crawl").setExecutor(new CrawlCommand());
    }

    if (version >= 12) {
      cowCannon.getCommand("toast").setExecutor(new ToastCommand());
    }
  }
}
