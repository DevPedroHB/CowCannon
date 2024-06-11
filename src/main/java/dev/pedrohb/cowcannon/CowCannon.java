package dev.pedrohb.cowcannon;

import dev.pedrohb.cowcannon.commands.CowCommand;
import dev.pedrohb.cowcannon.configs.Settings;
import dev.pedrohb.cowcannon.listeners.EntityListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class CowCannon extends JavaPlugin {

  public static CowCannon getInstance() {
    return getPlugin(CowCannon.class);
  }

  @Override
  public void onEnable() {
    // Events
    getServer().getPluginManager().registerEvents(new EntityListener(), this);

    // commands
    getCommand("cow").setExecutor(new CowCommand());

    // configs
    Settings.getInstance().load();

    getLogger().info("CowCannon has ben enabled.");
  }

  @Override
  public void onDisable() {
    getLogger().info("CowCannon has ben disabled.");
  }
}
