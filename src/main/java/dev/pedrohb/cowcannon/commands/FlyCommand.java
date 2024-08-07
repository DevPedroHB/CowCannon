package dev.pedrohb.cowcannon.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public final class FlyCommand implements CommandExecutor {

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
      @NotNull String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players can use this command.");

      return true;
    }

    Player player = (Player) sender;

    if (player.getAllowFlight()) {
      player.setAllowFlight(false);

      player.sendMessage(ChatColor.RED + "Flight disabled.");
    } else {
      player.setAllowFlight(true);

      player.sendMessage(ChatColor.GREEN + "Flight enabled.");
    }

    return true;
  }
}
