package dev.pedrohb.cowcannon.gui.fundation;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.TimeUtil;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;
import org.mineacademy.fo.slider.ColoredTextSlider;
import org.mineacademy.fo.slider.ItemSlider;
import org.mineacademy.fo.slider.Slider;

@SuppressWarnings("unused")
public final class MenuOne extends Menu {

  // @Position(start = StartPosition.CENTER, value = -2)
  private final Button currentTimeButton;

  public MenuOne() {
    this.setSize(9 * 3);
    this.setTitle("Animated Menu");

    this.currentTimeButton = new Button() {
      int ticksLived = 0;
      boolean toggle = false;

      @Override
      public void onClickedInMenu(Player player, Menu menu, ClickType clickType) {
        new AnotherPage().displayTo(player);
      }

      @Override
      public ItemStack getItem() {
        this.ticksLived++;

        if ((this.ticksLived % 20) == 0) { // 20 / 20 = 1.00
          this.ticksLived = 0;

          this.toggle = !this.toggle;
        }

        return ItemCreator.of(
            this.toggle ? CompMaterial.RECOVERY_COMPASS : CompMaterial.COMPASS,
            "Current Time",
            "",
            "Value: " + TimeUtil.getFormattedDate())
            .glow(true)
            .make();
      }

      @Override
      public int getSlot() {
        return getSize() - 5;
      }
    };
  }

  @Override
  protected void onPostDisplay(Player viewer) {
    this.animate(1, this::redrawButtons);

    final String menuTitle = "Animated Menu";
    final Slider<String> textSlider = ColoredTextSlider.from(menuTitle)
        .width(10)
        .primaryColor("&6&l")
        .secondaryColor("&0&l");

    this.animateAsync(2, () -> setTitle(textSlider.next()));

    final Slider<List<ItemStack>> itemsSlider = ItemSlider.from(
        ItemCreator.of(CompMaterial.GRAY_STAINED_GLASS_PANE, ""),
        ItemCreator.of(CompMaterial.NETHER_STAR, "Slided Item!"))
        .width(9);

    this.animate(10, new MenuRunnable() {
      @Override
      public void run() {
        final List<ItemStack> items = itemsSlider.next();

        for (int i = 0; i < items.size(); i++) {
          setItem(9 + i, items.get(i));
        }

        // If the slider has moved the start to the last slot, cancel the animation
        if (items.get(items.size() - 1).getType() == CompMaterial.NETHER_STAR.getMaterial()) {
          this.cancel();
        }
      }
    });
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
