package dev.pedrohb.cowcannon.commands;

import dev.pedrohb.cowcannon.CowCannon;
import dev.pedrohb.cowcannon.configs.Settings;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CowCommand implements CommandExecutor, TabExecutor {

  private static final Settings settings = Settings.getInstance();
  private static final CowCannon cowCannon = CowCannon.getInstance();

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players can use this command.");

      return true;
    }

    Player player = (Player) sender;

    if (args.length > 1) {
      if (args[0].equalsIgnoreCase("set")) {
        EntityType type;

        try {
          type = EntityType.valueOf(args[1].toUpperCase());
        } catch (IllegalArgumentException e) {
          sender.sendMessage(ChatColor.RED + "Invalid entity type: " + args[1]);

          return true;
        }

        if (!type.isSpawnable() || !type.isAlive()) {
          sender.sendMessage(ChatColor.RED + "You can only use living entities.");

          return true;
        }

        settings.setEntityType(type);

        return true;
      }

      return false;
    }

    LivingEntity entity = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), settings.getEntityType());

    if (args.length == 1 && args[0].equalsIgnoreCase("baby")) {
      if (entity instanceof Ageable) {
        ((Ageable) entity).setBaby();
      } else {
        sender.sendMessage(ChatColor.RED + "This entity cannot be a baby.");

        return true;
      }
    }

    entity.setMetadata("CowCannon", new FixedMetadataValue(cowCannon, true));
    entity.setCustomName(ChatColor.RED + "Milk Me");
    entity.setCustomNameVisible(true);

    return true;
  }

  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (args.length == 1) {
      return Arrays.asList("baby", "set");
    }

    if (args.length == 2) {
      String name = args[1].toUpperCase();

      return Arrays.stream(EntityType.values())
          .filter(type -> type.isSpawnable() && type.isAlive() && type.name().startsWith(name))
          .map(Enum::name)
          .collect(Collectors.toList());
    }

    return new ArrayList<>();
  }
}
