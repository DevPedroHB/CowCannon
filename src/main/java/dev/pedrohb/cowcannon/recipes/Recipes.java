package dev.pedrohb.cowcannon.recipes;

public class Recipes {

  public static void setupRecipes(Integer version) {
    if (version >= 13) {
      CustomRecipe.register();
    }
  }
}
