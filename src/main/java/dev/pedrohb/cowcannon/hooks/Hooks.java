package dev.pedrohb.cowcannon.hooks;

import org.bukkit.Bukkit;

public class Hooks {

  public static void setupHooks() {
    if (Bukkit.getPluginManager().getPlugin("ProtocolLib") != null) {
      ProtocolLibHook.register();

      Bukkit.getLogger().info("ProtocolLib registrado com sucesso.");
    }

    if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
      CowEconomyHook.register();

      Bukkit.getLogger().info("Vault registrado com sucesso.");
    }

    if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
      PlaceholderAPIHook.registerHook();

      Bukkit.getLogger().info("PlaceholderAPI registrado com sucesso.");
    }

    if (Bukkit.getPluginManager().getPlugin("DiscordSRV") != null) {
      DiscordSRVHook.register();

      Bukkit.getLogger().info("DiscordSRV registrado com sucesso.");
    }
  }
}
