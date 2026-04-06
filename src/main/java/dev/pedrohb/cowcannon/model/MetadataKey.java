package dev.pedrohb.cowcannon.model;

import org.mineacademy.fo.plugin.SimplePlugin;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MetadataKey {

  public static final String EXPLODING_COW = key("EXPLODING_COW");

  private static String key(String value) {
    return SimplePlugin.getNamed() + "." + value;
  }
}
