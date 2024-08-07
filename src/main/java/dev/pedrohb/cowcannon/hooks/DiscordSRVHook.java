package dev.pedrohb.cowcannon.hooks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.DiscordGuildMessageReceivedEvent;
import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;

@SuppressWarnings("deprecation")
public class DiscordSRVHook {

  private static final DiscordSRVHook instance = new DiscordSRVHook();

  private static boolean isDiscordSRVHooked = false;

  static {
    isDiscordSRVHooked = Bukkit.getPluginManager().getPlugin("DiscordSRV") != null;
  }

  private DiscordSRVHook() {
  }

  public static void sendMessage(String channel, String message) {
    if (isDiscordSRVHooked) {
      TextChannel textChannel = DiscordSRV.getPlugin().getDestinationTextChannelForGameChannelName(channel);

      if (textChannel != null) {
        textChannel.sendMessage(message).complete();
      }
    }
  }

  public static void register() {
    DiscordSRV.api.subscribe(instance);
  }

  public static void unregister() {
    DiscordSRV.api.unsubscribe(instance);
  }

  public static boolean isDiscordSRVHooked() {
    return isDiscordSRVHooked;
  }

  @Subscribe
  public void onMessageReceived(DiscordGuildMessageReceivedEvent event) {
    String playerName = event.getMember().getEffectiveName();
    String channel = event.getChannel().getName();
    String message = event.getMessage().getContentRaw();

    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
        "&7[&bDiscord&7] &f" + playerName + " &7in &f" + channel + " &8» &f" + message));
  }
}
