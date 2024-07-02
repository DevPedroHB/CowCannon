package dev.pedrohb.cowcannon;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.mineacademy.fo.menu.button.ButtonReturnBack;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.remain.CompMaterial;

import dev.pedrohb.cowcannon.commands.ButterflyCommand;
import dev.pedrohb.cowcannon.commands.CowCommand;
import dev.pedrohb.cowcannon.commands.CrawlCommand;
import dev.pedrohb.cowcannon.commands.CustomItemCommand;
import dev.pedrohb.cowcannon.commands.DisplayEntityCommand;
import dev.pedrohb.cowcannon.commands.EconomyCommand;
import dev.pedrohb.cowcannon.commands.GuiCommand;
import dev.pedrohb.cowcannon.commands.LocaleCommand;
import dev.pedrohb.cowcannon.commands.TagCommand;
import dev.pedrohb.cowcannon.commands.ToastCommand;
import dev.pedrohb.cowcannon.configs.Settings;
import dev.pedrohb.cowcannon.hooks.DiscordSRVHook;
import dev.pedrohb.cowcannon.hooks.Hooks;
import dev.pedrohb.cowcannon.models.Scheduler;
import dev.pedrohb.cowcannon.recipes.CustomRecipe;
import dev.pedrohb.cowcannon.tasks.ButterflyTask;
import dev.pedrohb.cowcannon.tasks.LaserPointerTask;
import dev.pedrohb.cowcannon.tasks.ScoreboardTask;
import dev.pedrohb.cowcannon.tasks.TablistTask;
import lombok.Getter;

@SuppressWarnings("deprecation")
public final class CowCannon extends SimplePlugin {

  @Getter
  private static final Map<UUID, String> playerTags = new HashMap<>();

  private Scheduler.Task butterflyTask;
  private Scheduler.Task scoreboardTask;
  private Scheduler.Task laserPointerTask;
  private Scheduler.Task tablistTask;

  public static CowCannon getInstance() {
    return (CowCannon) SimplePlugin.getInstance();
  }

  @Override
  public void onPluginStart() {
    // Updated for the disappearance of safeguard in 1.20.5+ on Paper. Supports all
    // versions including legacy and Spigot.
    final String bukkitVersion = Bukkit.getServer().getBukkitVersion(); // 1.20.6-R0.1-SNAPSHOT
    final String versionString = bukkitVersion.split("\\-")[0]; // 1.20.6
    final String[] versions = versionString.split("\\.");
    final int version = Integer.parseInt(versions[1]); // 20 in 1.20.6

    // hooks
    Hooks.setupHooks();

    // commands
    getCommand("cow").setExecutor(new CowCommand());
    getCommand("butterfly").setExecutor(new ButterflyCommand());
    getCommand("customitem").setExecutor(new CustomItemCommand());
    getCommand("gui").setExecutor(new GuiCommand());
    getCommand("economy").setExecutor(new EconomyCommand());
    getCommand("locale").setExecutor(new LocaleCommand());
    getCommand("tag").setExecutor(new TagCommand());

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

    // tasks
    butterflyTask = Scheduler.runTimer(ButterflyTask.getInstance(), 0, 1);
    laserPointerTask = Scheduler.runTimer(LaserPointerTask.getInstance(), 0, 1);
    tablistTask = Scheduler.runTimer(TablistTask.getInstance(), 0, 20);

    if (!Scheduler.isFolia()) {
      scoreboardTask = Scheduler.runTimer(ScoreboardTask.getInstance(), 0, 20);
    }

    ButtonReturnBack.setMaterial(CompMaterial.ARROW);

    getLogger().info("CowCannon has ben enabled.");
  }

  @Override
  public void onPluginStop() {
    if (butterflyTask != null) {
      butterflyTask.cancel();
    }

    if (scoreboardTask != null) {
      scoreboardTask.cancel();
    }

    if (laserPointerTask != null) {
      laserPointerTask.cancel();
    }

    if (tablistTask != null) {
      tablistTask.cancel();
    }

    if (getServer().getPluginManager().getPlugin("DiscordSRV") != null) {
      DiscordSRVHook.unregister();
    }

    getLogger().info("CowCannon has ben disabled.");
  }
}
