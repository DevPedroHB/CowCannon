package dev.pedrohb.cowcannon.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tuple<A, B> {
  private A first;
  private B second;
}
