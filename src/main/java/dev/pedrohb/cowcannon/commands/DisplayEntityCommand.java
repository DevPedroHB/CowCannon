package dev.pedrohb.cowcannon.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings({ "deprecation", "unused" })
public class DisplayEntityCommand implements CommandExecutor, TabExecutor {

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
      @NotNull String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players can use this command.");

      return true;
    }

    Player player = (Player) sender;

    if (!(args.length == 1)) {
      return false;
    }

    try {
      if (args[0].equalsIgnoreCase("item")) {
        ItemDisplay item = player.getWorld().spawn(player.getLocation(), ItemDisplay.class);
        Transformation transformation = item.getTransformation();

        item.setItemStack(new ItemStack(Material.DIAMOND));
        item.setBillboard(Display.Billboard.CENTER); // auto-rotate
        item.setTransformation(transformation);
        transformation.getScale().set(2D);
      }

      if (args[0].equalsIgnoreCase("block")) {
        BlockDisplay block = player.getWorld().spawn(player.getLocation(), BlockDisplay.class);
        Transformation transformation = block.getTransformation();

        block.setBlock(Bukkit.createBlockData(Material.DIAMOND_BLOCK));
        block.setTransformation(transformation);
        transformation.getScale().set(2D);
      }

      if (args[0].equalsIgnoreCase("text")) {
        final TextDisplay text = player.getWorld().spawn(player.getLocation(), TextDisplay.class);

        text.setText(ChatColor.BOLD + "Warning: \n" + ChatColor.GREEN + "You Are An Idiot");
        text.setBillboard(Display.Billboard.CENTER);
      }
    } catch (final Throwable t) {
      sender.sendMessage("Error: " + t.getMessage());

      t.printStackTrace();
    }

    return true;
  }

  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
      @NotNull String label, @NotNull String[] args) {
    if (args.length == 1) {
      return Arrays.asList("item", "block", "text");
    }

    return new ArrayList<>();
  }
}
