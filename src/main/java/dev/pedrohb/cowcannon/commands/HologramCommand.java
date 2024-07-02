package dev.pedrohb.cowcannon.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import dev.pedrohb.cowcannon.models.Hologram;

public class HologramCommand implements CommandExecutor {

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
      @NotNull String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players can use this command.");

      return true;
    }

    if (args.length == 0) {
      return false;
    }

    Player player = (Player) sender;
    Hologram hologram = new Hologram(String.join(" ", args).split("\\|"));

    hologram.spawn(player.getLocation());

    return true;
  }
}
