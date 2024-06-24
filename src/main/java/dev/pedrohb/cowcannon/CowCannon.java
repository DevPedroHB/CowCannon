package dev.pedrohb.cowcannon;

import dev.pedrohb.cowcannon.commands.*;
import dev.pedrohb.cowcannon.configs.Settings;
import dev.pedrohb.cowcannon.listeners.ChatListener;
import dev.pedrohb.cowcannon.listeners.EntityListener;
import dev.pedrohb.cowcannon.listeners.GuiListener;
import dev.pedrohb.cowcannon.listeners.LaserPointerListener;
import dev.pedrohb.cowcannon.models.CustomRecipe;
import dev.pedrohb.cowcannon.tasks.ButterflyTask;
import dev.pedrohb.cowcannon.tasks.LaserPointerTask;
import dev.pedrohb.cowcannon.tasks.ScoreboardTask;
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
    ButtonReturnBack.setMaterial(CompMaterial.ARROW);

    // Events
    getServer().getPluginManager().registerEvents(new EntityListener(), this);
    getServer().getPluginManager().registerEvents(new GuiListener(), this);
    getServer().getPluginManager().registerEvents(new LaserPointerListener(), this);
    getServer().getPluginManager().registerEvents(new ChatListener(), this);

    // commands
    getCommand("cow").setExecutor(new CowCommand());
    getCommand("butterfly").setExecutor(new ButterflyCommand());
    getCommand("displayentity").setExecutor(new DisplayEntityCommand());
    getCommand("customitem").setExecutor(new CustomItemCommand());
    getCommand("gui").setExecutor(new GuiCommand());

    // configs
    Settings.getInstance().load();

    // recipes
    CustomRecipe.register();

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
