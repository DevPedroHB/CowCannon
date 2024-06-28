package dev.pedrohb.cowcannon;

import dev.pedrohb.cowcannon.commands.*;
import dev.pedrohb.cowcannon.configs.Settings;
import dev.pedrohb.cowcannon.hooks.CowEconomyHook;
import dev.pedrohb.cowcannon.hooks.DiscordSRVHook;
import dev.pedrohb.cowcannon.hooks.PlaceholderAPIHook;
import dev.pedrohb.cowcannon.hooks.ProtocolLibHook;
import dev.pedrohb.cowcannon.listeners.*;
import dev.pedrohb.cowcannon.recipes.CustomRecipe;
import dev.pedrohb.cowcannon.tasks.ButterflyTask;
import dev.pedrohb.cowcannon.tasks.LaserPointerTask;
import dev.pedrohb.cowcannon.tasks.ScoreboardTask;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import org.mineacademy.fo.menu.button.ButtonReturnBack;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.remain.CompMaterial;

public final class CowCannon extends SimplePlugin {

  private BukkitTask butterflyTask;
  private BukkitTask scoreboardTask;
  private BukkitTask laserPointerTask;

  public static CowCannon getInstance() {
    return (CowCannon) SimplePlugin.getInstance();
  }

  @Override
  public void onPluginStart() {
    // Updated for the disappearance of safeguard in 1.20.5+ on Paper. Supports all versions including legacy and Spigot.
    final String bukkitVersion = Bukkit.getServer().getBukkitVersion(); // 1.20.6-R0.1-SNAPSHOT
    final String versionString = bukkitVersion.split("\\-")[0]; // 1.20.6
    final String[] versions = versionString.split("\\.");
    final int version = Integer.parseInt(versions[1]); // 20 in 1.20.6

    ButtonReturnBack.setMaterial(CompMaterial.ARROW);

    // Events
    getServer().getPluginManager().registerEvents(new EntityListener(), this);
    getServer().getPluginManager().registerEvents(new GuiListener(), this);
    getServer().getPluginManager().registerEvents(new LaserPointerListener(), this);
    getServer().getPluginManager().registerEvents(new ChatListener(), this);

    if (version >= 14) {
      getServer().getPluginManager().registerEvents(new CrawlListener(), this);
    }

    // commands
    getCommand("cow").setExecutor(new CowCommand());
    getCommand("butterfly").setExecutor(new ButterflyCommand());
    getCommand("customitem").setExecutor(new CustomItemCommand());
    getCommand("gui").setExecutor(new GuiCommand());
    getCommand("economy").setExecutor(new EconomyCommand());

    if (version >= 19) {
      getCommand("displayentity").setExecutor(new DisplayEntityCommand());
    }

    if (version >= 14) {
      getCommand("crawl").setExecutor(new CrawlCommand());
    }

    if (version >= 12) {
      getCommand("toast").setExecutor(new ToastCommand());
    }

    // configs
    Settings.getInstance().load();

    // recipes
    if (version >= 13) {
      CustomRecipe.register();
    }

    // hooks
    if (Bukkit.getPluginManager().getPlugin("ProtocolLib") != null) {
      ProtocolLibHook.register();
    }

    if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
      CowEconomyHook.register();
    }

    if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
      PlaceholderAPIHook.registerHook();
    }

    if (Bukkit.getPluginManager().getPlugin("DiscordSRV") != null) {
      DiscordSRVHook.register();
    }

    // tasks
    butterflyTask = getServer().getScheduler().runTaskTimer(this, ButterflyTask.getInstance(), 0, 1);
    scoreboardTask = getServer().getScheduler().runTaskTimer(this, ScoreboardTask.getInstance(), 0, 20);
    laserPointerTask = getServer().getScheduler().runTaskTimer(this, LaserPointerTask.getInstance(), 0, 1);

    getLogger().info("CowCannon has ben enabled.");
  }

  @Override
  public void onPluginStop() {
    if (butterflyTask != null && !butterflyTask.isCancelled()) {
      butterflyTask.cancel();
    }

    if (scoreboardTask != null && !scoreboardTask.isCancelled()) {
      scoreboardTask.cancel();
    }

    if (laserPointerTask != null && !laserPointerTask.isCancelled()) {
      laserPointerTask.cancel();
    }

    getLogger().info("CowCannon has ben disabled.");
  }
}
