package dev.pedrohb.cowcannon.configs;

import dev.pedrohb.cowcannon.CowCannon;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;

import java.io.File;

public class Settings {

  private final static Settings instance = new Settings();

  private File file;
  private YamlConfiguration config;

  private EntityType entityType;

  private Settings() {
  }

  public static Settings getInstance() {
    return instance;
  }

  public void load() {
    file = new File(CowCannon.getInstance().getDataFolder(), "settings.yml");

    if (!file.exists()) {
      CowCannon.getInstance().saveResource("settings.yml", false);
    }

    config = new YamlConfiguration();

    config.options().parseComments(true);

    try {
      config.load(file);
    } catch (Exception e) {
      e.printStackTrace();
    }

    entityType = EntityType.valueOf(config.getString("explosion.entity-type"));
  }

  public void save() {
    try {
      config.save(file);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void set(String path, Object value) {
    config.set(path, value);

    save();
  }

  public EntityType getEntityType() {
    return entityType;
  }

  public void setEntityType(EntityType entityType) {
    this.entityType = entityType;

    set("explosion.entity-type", entityType.name());
  }
}
