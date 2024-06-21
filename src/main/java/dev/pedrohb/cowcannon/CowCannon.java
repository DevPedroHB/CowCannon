package dev.pedrohb.cowcannon;

import dev.pedrohb.cowcannon.commands.*;
import dev.pedrohb.cowcannon.configs.Settings;
import dev.pedrohb.cowcannon.listeners.EntityListener;
import dev.pedrohb.cowcannon.listeners.GuiListener;
import dev.pedrohb.cowcannon.tasks.ButterflyTask;
import org.bukkit.scheduler.BukkitTask;
import org.mineacademy.fo.menu.button.ButtonReturnBack;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.remain.CompMaterial;

public final class CowCannon extends SimplePlugin {

  private BukkitTask task;

  public static CowCannon getInstance() {
    return (CowCannon) SimplePlugin.getInstance();
  }

  @Override
  public void onPluginStart() {
    ButtonReturnBack.setMaterial(CompMaterial.ARROW);

    // Events
    getServer().getPluginManager().registerEvents(new EntityListener(), this);
    getServer().getPluginManager().registerEvents(new GuiListener(), this);

    // commands
    getCommand("cow").setExecutor(new CowCommand());
    getCommand("butterfly").setExecutor(new ButterflyCommand());
    getCommand("displayentity").setExecutor(new DisplayEntityCommand());
    getCommand("customitem").setExecutor(new CustomItemCommand());
    getCommand("gui").setExecutor(new GuiCommand());

    // configs
    Settings.getInstance().load();

    // tasks
    task = getServer().getScheduler().runTaskTimer(this, ButterflyTask.getInstance(), 0, 1);

    getLogger().info("CowCannon has ben enabled.");
  }

  @Override
  public void onPluginStop() {
    if (task != null && !task.isCancelled()) {
      task.cancel();
    }

    getLogger().info("CowCannon has ben disabled.");
  }
}
