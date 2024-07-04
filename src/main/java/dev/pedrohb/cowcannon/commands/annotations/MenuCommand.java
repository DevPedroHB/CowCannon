package dev.pedrohb.cowcannon.commands.annotations;

import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.command.SimpleCommand;

import dev.pedrohb.cowcannon.gui.StaffMenu;

@AutoRegister
public final class MenuCommand extends SimpleCommand {

  public MenuCommand() {
    super("menutest");
  }

  @Override
  protected void onCommand() {
    this.checkConsole();

    new StaffMenu().diplayTo(this.getPlayer());
  }
}
