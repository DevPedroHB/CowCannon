package dev.pedrohb.cowcannon.configs;

import java.io.File;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;

import dev.pedrohb.cowcannon.CowCannon;
import lombok.Getter;

public final class Settings {

  @Getter
  private final static Settings instance = new Settings();
  private File file;
  private YamlConfiguration config;

  @Getter
  private EntityType explodingType;
  @Getter
  private List<String> headerLines;
  @Getter
  private List<String> footerLines;

  private Settings() {
  }

  public void load() {
    file = new File(CowCannon.getInstance().getDataFolder(), "settings.yml");

    if (!file.exists()) {
      CowCannon.getInstance().saveResource("settings.yml", false);
    }

    config = new YamlConfiguration();

    try {
      config.options().parseComments(true);
    } catch (Throwable t) {
      // Unsupported
    }

    try {
      config.load(file);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    explodingType = EntityType.valueOf(config.getString("explosion.entity-type"));
    headerLines = config.getStringList("tablist.header");
    footerLines = config.getStringList("tablist.hooter");
  }

  public void save() {
    try {
      config.save(file);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void set(String path, Object value) {
    config.set(path, value);

    save();
  }

  public void setExplodingType(EntityType explodingType) {
    this.explodingType = explodingType;

    set("explosion.entity-type", explodingType.name());
  }
}
