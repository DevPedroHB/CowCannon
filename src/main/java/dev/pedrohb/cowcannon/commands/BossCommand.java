package dev.pedrohb.cowcannon.commands;

import org.mineacademy.fo.annotation.AutoRegister;

import dev.pedrohb.cowcannon.commands.annotations.Parameter;

@AutoRegister
public final class BossCommand extends AnnotatedCommand {

  public BossCommand() {
    super("boss");
  }

  @Parameter("") // /boss
  public void onMain() {
    this.tell("Boss command main menu...");
  }

  @Parameter("create") // /boss create
  public void onCreate() {
    this.tell("Creating boss...");
  }

  @Parameter(value = "remove") // /boss remove
  public void onRemove() {
    this.tell("Removing boss...");
  }
}
