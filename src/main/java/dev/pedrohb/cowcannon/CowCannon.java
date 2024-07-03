package dev.pedrohb.cowcannon;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.mineacademy.fo.menu.button.ButtonReturnBack;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.remain.CompMaterial;

import dev.pedrohb.cowcannon.commands.Commands;
import dev.pedrohb.cowcannon.configs.Configs;
import dev.pedrohb.cowcannon.hooks.Hooks;
import dev.pedrohb.cowcannon.listeners.Listeners;
import dev.pedrohb.cowcannon.recipes.Recipes;
import dev.pedrohb.cowcannon.tasks.Tasks;
import lombok.Getter;

public final class CowCannon extends SimplePlugin {

  @Getter
  private static final Map<UUID, String> playerTags = new HashMap<>();

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

    Hooks.setupHooks();
    Configs.setupConfigs();
    Commands.setupCommands(this, version);
    Listeners.setupListeners(this, version);
    Tasks.registerTasks();
    Recipes.setupRecipes(version);

    ButtonReturnBack.setMaterial(CompMaterial.ARROW);

    getLogger().info("CowCannon has ben enabled.");
  }

  @Override
  public void onPluginStop() {
    Tasks.unregisterTasks(this);

    getLogger().info("CowCannon has ben disabled.");
  }
}
