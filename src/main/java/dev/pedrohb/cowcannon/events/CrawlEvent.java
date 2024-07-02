package dev.pedrohb.cowcannon.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.Setter;

public final class CrawlEvent extends Event implements Cancellable {

  private static final HandlerList handlers = new HandlerList();

  @Setter
  @Getter
  private Player player;
  @Getter
  @Setter
  private boolean cancelled;

  public CrawlEvent(Player player) {
    this.player = player;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }

  @Override
  public HandlerList getHandlers() {
    return handlers;
  }
}
