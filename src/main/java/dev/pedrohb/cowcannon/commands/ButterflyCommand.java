package dev.pedrohb.cowcannon.commands;

import dev.pedrohb.cowcannon.tasks.ButterflyTask;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ButterflyCommand implements CommandExecutor {

  private static final ButterflyTask butterflyTask = ButterflyTask.getInstance();

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players can use this command.");

      return true;
    }

    Player player = (Player) sender;

    if (butterflyTask.hasPlayer(player.getUniqueId())) {
      butterflyTask.removePlayer(player.getUniqueId());

      player.sendMessage(ChatColor.RED + "You are no longer viewing butterfly wings.");
    } else {
      butterflyTask.addPlayer(player.getUniqueId());

      player.sendMessage(ChatColor.GREEN + "You are now viewing butterfly wings.");
    }

    return true;
  }
}
