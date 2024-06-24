package dev.pedrohb.cowcannon.commands;

import dev.pedrohb.cowcannon.models.MainMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GuiCommand implements CommandExecutor {

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players can use this command.");

      return true;
    }

    Player player = (Player) sender;

    new MainMenu().displayTo(player);

    /*Inventory inventory = Bukkit.createInventory(player, 9 * 3, ChatColor.GREEN + "Preferences Menu");
    ItemStack diamond = new ItemStack(Material.DIAMOND);
    ItemMeta diamondMeta = diamond.getItemMeta();
    diamondMeta.setDisplayName(ChatColor.AQUA + "Get Diamond");
    diamond.setItemMeta(diamondMeta);

    ItemStack lavaBucket = new ItemStack(Material.LAVA_BUCKET);
    ItemMeta lavaBucketMeta = diamond.getItemMeta();
    lavaBucketMeta.setDisplayName(ChatColor.RED + "Clear Inventory");
    lavaBucket.setItemMeta(lavaBucketMeta);

    ItemStack sunflower = new ItemStack(Material.SUNFLOWER);
    ItemMeta sunflowerMeta = diamond.getItemMeta();
    sunflowerMeta.setDisplayName(ChatColor.YELLOW + "Clear Weather");
    sunflower.setItemMeta(sunflowerMeta);

    inventory.setItem(11, diamond);
    inventory.setItem(13, lavaBucket);
    inventory.setItem(15, sunflower);

    player.openInventory(inventory);
    player.setMetadata("OpenedMenu", new FixedMetadataValue(cowCannon, "Preferences Menu"));*/

    return true;
  }
}
