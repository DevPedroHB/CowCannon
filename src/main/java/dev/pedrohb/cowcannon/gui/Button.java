package dev.pedrohb.cowcannon.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Button {

  @Getter
  private final int slot;

  public abstract ItemStack getItem();

  public abstract void onClick(Player player);
}
