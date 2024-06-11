package dev.pedrohb.cowcannon;

import dev.pedrohb.cowcannon.Commands.CowCommand;
import dev.pedrohb.cowcannon.Listeners.EntityListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class CowCannon extends JavaPlugin {

  public static CowCannon getInstance() {
    return getPlugin(CowCannon.class);
  }

  @Override
  public void onEnable() {
    // Events
    getServer().getPluginManager().registerEvents(new EntityListener(), this);

    // Commands
    getCommand("cow").setExecutor(new CowCommand());

    getLogger().info("CowCannon has ben enabled.");
  }

  @Override
  public void onDisable() {
    getLogger().info("CowCannon has ben disabled.");
  }
}
