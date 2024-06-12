package dev.pedrohb.cowcannon.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisplayEntityCommand implements CommandExecutor, TabExecutor {

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

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

        //item.setViewRange(0.1F); // 0.1 = 16 blocks
        //item.setShadowRadius(0.3F); // 1 = 1 block
        //item.setShadowRadius(1F);
        //item.setShadowStrength(5F); // >= 5F = "black hole"
        //item.setDisplayWidth(50F);
        //item.setDisplayHeight(50F);
        //item.setGlowColorOverride(Color.RED); // only works for scoreboard
        //item.setBrightness(new Brightness(15, 15)); // 0-15, will override auto brightness

        //transformation.getLeftRotation().x = 1; // 1 to -1, forward/backward lay
        //transformation.getLeftRotation().y = 0.5F; // 1 to -1, horizontal rotation
        //transformation.getLeftRotation().z = -1F; // 1 to -1, right/left tilt
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

        //text.setBackgroundColor(Color.RED);
        //text.setLineWidth(50);
        //text.setTextOpacity(Byte.MAX_VALUE); // transparent
      }
    } catch (final Throwable t) {
      sender.sendMessage("Error: " + t.getMessage());

      t.printStackTrace();
    }

    return true;
  }

  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (args.length == 1) {
      return Arrays.asList("item", "block", "text");
    }

    return new ArrayList<>();
  }
}
