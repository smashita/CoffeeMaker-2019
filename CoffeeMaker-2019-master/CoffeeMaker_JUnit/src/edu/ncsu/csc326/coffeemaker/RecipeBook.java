package edu.ncsu.csc326.coffeemaker;

public class RecipeBook {

  /** Array of recipes in coffee maker. */
  private Recipe[] recipeArray;
  /** Number of recipes in coffee maker. */
  private final int numRecipes = 4;

  /**
   * Default constructor for a RecipeBook.
   */
  public RecipeBook()  {
    recipeArray = new Recipe[numRecipes];
  }

  /**
   * Returns the recipe array.
   * @return Recipe[]
   */
  public synchronized Recipe[] getRecipes() {
    return recipeArray;
  }

  /**
   * addRecipe.
   * @return boolean
   */
  public synchronized boolean addRecipe(final Recipe r) {
    // Assume recipe doesn't exist in the array until
    // find out otherwise
    boolean exists = false;
    // Check that recipe doesn't already exist in array
    for (int i = 0; i < recipeArray.length; i++) {
      if (r.equals(recipeArray[i])) {
        exists = true;
      }
    }
    // Assume recipe cannot be added until find an empty
    // spot
    boolean added = false;
    // Check for first empty spot in array
    if (!exists) {
      for (int i = 0; i < recipeArray.length && !added; i++) {
        if (recipeArray[i] == null) {
          recipeArray[i] = r;
          added = true;
        }
      }
    }
    return added;
  }

  /**
   * Returns the name of the recipe deleted at the position specified and null if
   * the recipe does not exist.
   * @param recipeToDelete
   *          do something
   * @return String
   */
  public synchronized String deleteRecipe(final int recipeToDelete) {
    if (recipeArray[recipeToDelete] != null) {
      String recipeName = recipeArray[recipeToDelete].getName();
      recipeArray[recipeToDelete] = new Recipe();
      return recipeName;
    } else {
      return null;
    }
  }

  /**
   * Returns the name of the recipe edited at the position specified and null if
   * the recipe does not exist.
   * @param recipeToEdit
   *          do something
   * @param newRecipe
   *          do something
   * @return String
   */
  public synchronized String editRecipe(final int recipeToEdit, final Recipe newRecipe) {
    if (recipeArray[recipeToEdit] != null) {
      String recipeName = recipeArray[recipeToEdit].getName();
      newRecipe.setName("");
      recipeArray[recipeToEdit] = newRecipe;
      return recipeName;
    } else {
      return null;
    }
  }
}
