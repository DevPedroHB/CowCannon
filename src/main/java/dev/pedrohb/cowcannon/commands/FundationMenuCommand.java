package dev.pedrohb.cowcannon.commands;

import java.util.List;

import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.command.SimpleCommand;

import dev.pedrohb.cowcannon.gui.fundation.MenuOne;

@AutoRegister
public final class FundationMenuCommand extends SimpleCommand {

  public FundationMenuCommand() {
    super("fundationmenu", List.of("fmenu", "menuf", "menufundation"));
  }

  @Override
  protected void onCommand() {
    this.checkConsole();

    new MenuOne().displayTo(this.getPlayer());
  }
}
