package dev.pedrohb.cowcannon.enchants;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.enchant.SimpleEnchantment;
import org.mineacademy.fo.enchant.SimpleEnchantmentRarity;
import org.mineacademy.fo.remain.CompParticle;
import org.mineacademy.fo.remain.CompSound;

import lombok.Getter;

public class BlackNovaEnchant extends SimpleEnchantment {

  @Getter
  private static final BlackNovaEnchant instance = new BlackNovaEnchant();

  protected BlackNovaEnchant() {
    super("Black Nova", 10);
  }

  @EventHandler
  public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
    if (!(event.getDamager() instanceof Player)) {
      return;
    }

    final Player player = (Player) event.getDamager();
    final ItemStack handItem = player.getInventory().getItemInMainHand();
    final Entity victim = event.getEntity();

    if (this.hasEnchant(handItem)) {
      CompSound.BLAZE_HIT.play(victim.getLocation());
      CompParticle.CRIT.spawn(victim.getLocation());

      victim.setFireTicks(3 * 20);
    }
  }

  @Override
  protected void onDamage(int level, LivingEntity damager, EntityDamageByEntityEvent event) {
    System.out.println("damaged: " + damager + " with black nova lavel " + level);
  }

  @Override
  public SimpleEnchantmentRarity getRarity() {
    return SimpleEnchantmentRarity.COMMON;
  }

  @Override
  public boolean conflictsWith(Enchantment other) {
    return super.conflictsWith(other);
  }
}
