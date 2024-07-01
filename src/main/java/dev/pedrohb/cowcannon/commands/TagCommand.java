package dev.pedrohb.cowcannon.commands;

import dev.pedrohb.cowcannon.CowCannon;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class TagCommand implements CommandExecutor {

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage(ChatColor.RED + "Only players can use this command.");

      return true;
    }

    if (args.length != 1) {
      return false;
    }

    String tag = args[0];
    Player player = (Player) sender;
    UUID uniqueId = player.getUniqueId();

    if ("reset".equals(tag)) {
      CowCannon.getPlayerTags().remove(uniqueId);

      player.sendMessage(ChatColor.GREEN + "Your tag has been reset.");
    } else {
      tag = ChatColor.translateAlternateColorCodes('&', tag);

      if (tag.length() > 16) {
        tag = tag.substring(0, 16);
      }

      CowCannon.getPlayerTags().put(uniqueId, tag);

      player.sendMessage(ChatColor.GREEN + "Your tag has been set to " + tag + ChatColor.GREEN + ".");
    }

    return true;
  }
}
