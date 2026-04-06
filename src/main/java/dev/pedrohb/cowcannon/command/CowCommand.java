package dev.pedrohb.cowcannon.command;

import java.util.List;

import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.command.SimpleCommand;
import org.mineacademy.fo.model.Replacer;
import org.mineacademy.fo.remain.CompMetadata;
import org.mineacademy.fo.remain.Remain;
import org.mineacademy.fo.settings.Lang;

import dev.pedrohb.cowcannon.model.EntityAge;
import dev.pedrohb.cowcannon.model.MetadataKey;

@AutoRegister
public final class CowCommand extends SimpleCommand {

  private static final String LANG_PATH = "Commands.Cow.";
  private static final int TARGET_DISTANCE = 5;

  public CowCommand() {
    super("cow|vaca");

    this.setDescription(this.cowLangOf("Description"));
    this.setMinArguments(1);
  }

  @Override
  protected String[] getMultilineUsageMessage() {
    final String ages = Common.join(EntityAge.values(), ", ");

    return Replacer.replaceArray(this.cowLangOf("Usage", ages), "ages", ages).split("\n");
  }

  @Override
  protected void onCommand() {
    this.checkConsole();

    switch (this.args[0].toLowerCase()) {
      case "spawn":
        this.handleSpawn();
        break;

      case "info":
        this.handleInfo();
        break;

      default:
        this.returnInvalidArgs();
        break;
    }
  }

  @Override
  protected List<String> tabComplete() {
    if (this.args.length == 1) {
      return this.completeLastWord("spawn", "info");
    }

    if ("spawn".equalsIgnoreCase(this.args[0]) && this.args.length == 2) {
      return this.completeLastWord(Common.toList(EntityAge.values()));
    }

    return NO_COMPLETE;
  }

  private void handleSpawn() {
    this.checkUsage(this.args.length <= 2);

    final Player player = this.getPlayer();
    final Cow cow = player.getWorld().spawn(player.getLocation(), Cow.class);

    final EntityAge age = this.args.length == 2
        ? this.findEnum(EntityAge.class, this.args[1], this.cowLangOf("Invalid_Age"))
        : EntityAge.ADULT;

    if (age == EntityAge.BABY) {
      cow.setBaby();
    }

    CompMetadata.setMetadata(cow, MetadataKey.EXPLODING_COW, "true");
    Remain.setCustomName(cow, this.cowLangOf("Cow_Name"));

    this.tellSuccess(this.cowLangOf("Spawn_Success"));
  }

  private void handleInfo() {
    this.checkUsage(this.args.length <= 1);

    final Entity entity = this.getPlayer().getTargetEntity(TARGET_DISTANCE);

    this.checkBoolean(entity instanceof Cow, this.cowLangOf("Must_Look_At_Cow"));

    final boolean isExplodingCow = CompMetadata.hasMetadata(entity, MetadataKey.EXPLODING_COW);

    if (isExplodingCow) {
      this.tellInfo(this.cowLangOf("Info.Exploding"));
    } else {
      this.tellInfo(this.cowLangOf("Info.Not_Exploding"));
    }
  }

  private String cowLangOf(String path, Object... variables) {
    return Lang.of(LANG_PATH + path, variables);
  }
}
