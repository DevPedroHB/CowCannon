package dev.pedrohb.cowcannon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntityAge {
  BABY("baby"),
  ADULT("adult");

  private final String entityAge;
}
