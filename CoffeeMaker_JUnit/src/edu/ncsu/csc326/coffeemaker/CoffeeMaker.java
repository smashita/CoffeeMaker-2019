package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;

/*
 * @author Sarah Heckman
 */
public class CoffeeMaker {
  /** Array of recipes in coffee maker. */
  private static RecipeBook recipeBook;
  /** Inventory of the coffee maker. */
  private static Inventory inventory;
	
  /**
   * Constructor for the coffee maker.
   *
   */
  public CoffeeMaker() {
    recipeBook = new RecipeBook();
    inventory = new Inventory();
  }
	
  /**
   * Returns true if the recipe is added to the
   * list of recipes in the CoffeeMaker and false
   * otherwise.
   * @param r recipe
   * @return boolean
   */
  public boolean addRecipe(Recipe r) {
    return recipeBook.addRecipe(r);
  }
	
  /**
   * Returns the name of the successfully deleted recipe
   * or null if the recipe cannot be deleted.
   * 
   * @param recipeToDelete recipe to be deleted
   * @return String
   */
  public String deleteRecipe(int recipeToDelete) {
    return recipeBook.deleteRecipe(recipeToDelete);
  }
	
  /**
   * Returns the name of the successfully edited recipe
   * or null if the recipe cannot be edited.
   * @param recipeToEdit recipe to be editted
   * @param r recipe
   * @return String
   */
  public String editRecipe(int recipeToEdit, Recipe r) {
    return recipeBook.editRecipe(recipeToEdit, r);
  }
    
  /**
   * Returns true if inventory was successfully added.
   * @param amtCoffee amount of coffee to add
   * @param amtMilk amount of milk to add
   * @param amtSugar amount of sugar to add
   * @param amtChocolate amount of chocolate to add
   */
  public synchronized void addInventory(final String amtCoffee, final String amtMilk, 
      final String amtSugar, final String amtChocolate) throws InventoryException {
    inventory.addCoffee(amtCoffee);
    inventory.addMilk(amtMilk);
    inventory.addSugar(amtSugar);
    inventory.addChocolate(amtChocolate);
  }
    
  /**
   * Returns the inventory of the coffee maker.
   * @return Inventory
   */
  public synchronized String checkInventory() {
    return inventory.toString();
  }
    
  /**
   * Returns the change of a user's beverage purchase, or
   * the user's money if the beverage cannot be made.
   * @param recipeToPurchase recipe to be purchased
   * @param amtPaid amount to be paid
   * @return int
   */
  public synchronized int makeCoffee(int recipeToPurchase, int amtPaid) {
    int change = 0;
        
    if (getRecipes()[recipeToPurchase] == null) {
      change = amtPaid;
    } else if (getRecipes()[recipeToPurchase].getPrice() <= amtPaid) {
      if (inventory.useIngredients(getRecipes()[recipeToPurchase])) {
        change = amtPaid - getRecipes()[recipeToPurchase].getPrice();
      } else {
        change = amtPaid;
      }
    } else {
      change = amtPaid;
    }
        
    return change;
  }

  /**
   * Returns the list of Recipes in the RecipeBook.
   * @return Recipe []
   */
  public synchronized Recipe[] getRecipes() {
    return recipeBook.getRecipes();
  }
}
