package dev.pedrohb.cowcannon.hooks;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

@SuppressWarnings("unused")
public final class VaultHook {

  private static Economy economy = null;
  private static Permission permissions = null;
  private static Chat chat = null;

  static {
    if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
      setupEconomy();
      setupChat();
      setupPermissions();
    }
  }

  private VaultHook() {
  }

  private static void setupEconomy() {
    final RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);

    if (rsp != null) {
      economy = rsp.getProvider();
    }
  }

  private static void setupChat() {
    RegisteredServiceProvider<Chat> rsp = Bukkit.getServicesManager().getRegistration(Chat.class);

    if (rsp != null) {
      chat = rsp.getProvider();
    }
  }

  private static void setupPermissions() {
    RegisteredServiceProvider<Permission> rsp = Bukkit.getServicesManager().getRegistration(Permission.class);

    if (rsp != null) {
      permissions = rsp.getProvider();
    }
  }

  public static boolean hasEconomy() {
    return economy != null;
  }

  public static double getBalance(OfflinePlayer target) {
    if (!hasEconomy()) {
      throw new UnsupportedOperationException("Vault Economy not found, call hasEconomy() to check it first.");
    }

    return economy.getBalance(target);
  }

  public static String withdraw(OfflinePlayer target, double amount) {
    if (!hasEconomy()) {
      throw new UnsupportedOperationException("Vault Economy not found, call hasEconomy() to check it first.");
    }

    return economy.withdrawPlayer(target, amount).errorMessage;
  }

  public static String deposit(OfflinePlayer target, double amount) {
    if (!hasEconomy()) {
      throw new UnsupportedOperationException("Vault Economy not found, call hasEconomy() to check it first.");
    }

    return economy.depositPlayer(target, amount).errorMessage;
  }

  public static String formatCurrencySymbol(double amount) {
    if (!hasEconomy()) {
      throw new UnsupportedOperationException("Vault Economy not found, call hasEconomy() to check it first.");
    }

    return economy.format(amount);
  }
}
