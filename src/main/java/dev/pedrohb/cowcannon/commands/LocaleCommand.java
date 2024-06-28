package dev.pedrohb.cowcannon.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class LocaleCommand implements CommandExecutor {

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage(ChatColor.RED + "Only players can use this command.");

      return true;
    }

    if (args.length != 1) {
      return false;
    }

    Player player = (Player) sender;
    String param = args[0].toLowerCase();

    switch (param) {
      case "get":
        player.sendMessage("Your localization is: " + player.getLocale());

        break;
      case "translate":
        LocaleExecutor.executeLocale(player);

        break;
      case "key":
        LocaleExecutor.executeKey(player);

        break;
      default:
        return false;
    }

    return true;
  }
}

class LocaleExecutor {

  static void executeLocale(Player player) {
    try {
      player.sendMessage(Component.translatable("lanServer.port.invalid.new")
          .args(Component.text("Banana"), Component.text("Cherry"))
          .color(TextColor.fromCSSHexString("#cc11ff")));
    } catch (LinkageError err) {
      player.sendMessage("You need to be on 1.16+ to use this command.");
    }
  }

  static void executeKey(Player player) {
    try {
      player.sendMessage(Component.translatable("key.back"));
      player.sendMessage(Component.keybind("key.back"));
    } catch (LinkageError err) {
      player.sendMessage("You need to be on 1.16+ to use this command.");
    }
  }
}
