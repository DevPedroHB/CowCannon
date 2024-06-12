package dev.pedrohb.cowcannon;

import dev.pedrohb.cowcannon.commands.ButterflyCommand;
import dev.pedrohb.cowcannon.commands.CowCommand;
import dev.pedrohb.cowcannon.configs.Settings;
import dev.pedrohb.cowcannon.listeners.EntityListener;
import dev.pedrohb.cowcannon.tasks.ButterflyTask;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class CowCannon extends JavaPlugin {

  private BukkitTask task;

  public static CowCannon getInstance() {
    return getPlugin(CowCannon.class);
  }

  @Override
  public void onEnable() {
    // Events
    getServer().getPluginManager().registerEvents(new EntityListener(), this);

    // commands
    getCommand("cow").setExecutor(new CowCommand());
    getCommand("butterfly").setExecutor(new ButterflyCommand());

    // configs
    Settings.getInstance().load();

    // tasks
    task = getServer().getScheduler().runTaskTimer(this, ButterflyTask.getInstance(), 0, 1);

    getLogger().info("CowCannon has ben enabled.");
  }

  @Override
  public void onDisable() {
    if (task != null && !task.isCancelled()) {
      task.cancel();
    }

    getLogger().info("CowCannon has ben disabled.");
  }
}
