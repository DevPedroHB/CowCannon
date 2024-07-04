package dev.pedrohb.cowcannon.gui;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;

import dev.pedrohb.cowcannon.CowCannon;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("deprecation")
public class Menu {

  @Getter
  private final List<Button> buttons = new ArrayList<>();

  @Setter
  private int size = 9 * 3;
  @Setter
  private String title = "Custom Menu";

  private Menu parent;
  private boolean extraButtonsRegistered;

  public Menu() {
    this(null);
  }

  public Menu(@Nullable Menu parent) {
    this.parent = parent;
  }

  protected final void addButton(Button button) {
    this.buttons.add(button);
  }

  public final void diplayTo(Player player) {
    Inventory inventory = Bukkit.createInventory(player, this.size, Common.colorize(title));

    for (Button button : this.buttons) {
      inventory.setItem(button.getSlot(), button.getItem());
    }

    if (this.parent != null && !this.extraButtonsRegistered) {
      this.extraButtonsRegistered = true;

      final Button returnBackButton = new Button(this.size - 1) {
        @Override
        public ItemStack getItem() {
          return ItemCreator.of(CompMaterial.ARROW, "Return Back").make();
        }

        @Override
        public void onClick(Player player) {
          try {
            final Menu newMenuInstance = parent.getClass().getConstructor().newInstance();

            newMenuInstance.diplayTo(player);
          } catch (final ReflectiveOperationException ex) {
            ex.printStackTrace();
          }
        }
      };

      this.buttons.add(returnBackButton);

      inventory.setItem(returnBackButton.getSlot(), returnBackButton.getItem());
    }

    if (player.hasMetadata("CowMenuMetadata")) {
      player.closeInventory();
    }

    player.setMetadata("CowMenuMetadata", new FixedMetadataValue(CowCannon.getInstance(), this));

    player.openInventory(inventory);
  }
}
