package dev.pedrohb.cowcannon.commands;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import dev.pedrohb.cowcannon.utils.Keys;

@SuppressWarnings("deprecation")
public class CustomItemCommand implements CommandExecutor {

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
      @NotNull String[] args) {

    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players can use this command.");

      return true;
    }

    Player player = (Player) sender;
    ItemStack customBucket = new ItemStack(Material.BUCKET);
    ItemMeta meta = customBucket.getItemMeta();

    meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Custom Bucket");
    meta.setLore(Arrays.asList(
        "",
        ChatColor.GRAY + "Right click me on a cow."));
    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    meta.addEnchant(Enchantment.SHARPNESS, 1, true);
    meta.getPersistentDataContainer().set(Keys.CUSTOM_BUCKET, PersistentDataType.BOOLEAN, true);

    customBucket.setItemMeta(meta);

    player.getInventory().addItem(customBucket);

    return true;
  }
}
