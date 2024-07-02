package dev.pedrohb.cowcannon.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import dev.pedrohb.cowcannon.events.CrawlEvent;

@SuppressWarnings("deprecation")
public class PlayerListener implements Listener {

  @EventHandler
  public void onCrawl(CrawlEvent event) {
    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
        "&8[&cCrawl&8] &7You are crawling!"));
  }
}
