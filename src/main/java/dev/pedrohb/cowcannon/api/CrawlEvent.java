package dev.pedrohb.cowcannon.api;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class CrawlEvent extends Event implements Cancellable {

  private static final HandlerList handlers = new HandlerList();

  @Setter
  @Getter
  private Player player;
  private boolean cancelled;

  public CrawlEvent(Player player) {
    this.player = player;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }

  @Override
  public boolean isCancelled() {
    return cancelled;
  }

  @Override
  public void setCancelled(boolean cancelled) {
    this.cancelled = cancelled;
  }

  public HandlerList getHandlers() {
    return handlers;
  }
}
