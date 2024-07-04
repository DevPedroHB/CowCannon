package dev.pedrohb.cowcannon.commands;

import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.MinecraftVersion;
import org.mineacademy.fo.MinecraftVersion.V;
import org.mineacademy.fo.command.SimpleCommand;
import org.mineacademy.fo.remain.CompMaterial;

import dev.pedrohb.cowcannon.enchants.BlackNovaEnchant;

public class EnchantsCommand extends SimpleCommand {

  protected EnchantsCommand(String label) {
    super("enchants");

    setDescription("Treat yourself to some good enchantments.");
  }

  @Override
  protected void onCommand() {
    checkConsole();
    checkBoolean(MinecraftVersion.atLeast(V.v1_13), "Custom enchants require at least Minecraft 1.13.");

    final ItemStack sword = new ItemStack(CompMaterial.DIAMOND_SWORD.getMaterial());

    sword.addEnchantment(BlackNovaEnchant.getInstance().toBukkit(), 10);

    getPlayer().getInventory().addItem(sword);
  }
}
