package dev.pedrohb.cowcannon.gui.fundation;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.TimeUtil;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;

@SuppressWarnings("unused")
public final class MenuOne extends Menu {

  // @Position(start = StartPosition.CENTER, value = -2)
  private final Button currentTimeButton;

  public MenuOne() {
    this.setSize(9 * 3);
    this.setTitle("&4My First Animated Menu");
    this.setSlotNumbersVisible();

    this.currentTimeButton = new Button(this.getCenterSlot()) {
      @Override
      public void onClickedInMenu(Player player, Menu menu, ClickType click) {
        animateTitle("&2Hello There");
        new AnotherPage().displayTo(player);
      }

      @Override
      public ItemStack getItem() {
        return ItemCreator.of(
            CompMaterial.CLOCK,
            "Current Time",
            "",
            "Value: " + TimeUtil.getFormattedDate())
            .glow(true)
            .make();
      }

      @Override
      public int getSlot() {
        return getCenterSlot();
      }
    };
  }

  @Override
  protected void onPostDisplay(Player viewer) {
    this.animate(20, this::redrawButtons);
  }

  private final class AnotherPage extends Menu {

    private final Button setDayButton;

    private AnotherPage() {
      super(MenuOne.this);

      this.setTitle("Another menu page");

      this.setDayButton = new Button(this.getCenterSlot()) {
        @Override
        public void onClickedInMenu(Player player, Menu menu, ClickType click) {
          player.getWorld().setTime(1000);

          animateTitle("Time set to day.");
        }

        @Override
        public ItemStack getItem() {
          return ItemCreator.of(CompMaterial.PLAYER_HEAD)
              .skullOwner(getViewer().getName())
              .make();
        }
      };
    }
  }
}
