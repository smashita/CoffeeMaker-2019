package edu.ncsu.csc326.coffeemaker;

import org.apache.commons.lang3.StringUtils;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

/** this is the main of application.
 * 
 * @author Sarah Heckman
 *     Starts the console UI for the CoffeeMaker.
 */
public class Main {
  private static CoffeeMaker coffeeMaker;

  /**
    * Prints the main menu and handles user input for 
    * main menu commands.
    */
  public static void mainMenu() {
    System.out.println("1. Add a recipe");
    System.out.println("2. Delete a recipe");
    System.out.println("3. Edit a recipe");
    System.out.println("4. Add inventory");
    System.out.println("5. Check inventory");
    System.out.println("6. Make coffee");
    System.out.println("0. Exit\n");
    
        
    //Get user input
    try {
      int userInput = Integer.parseInt(inputOutput(""
          + "Please press the number that corresponds to"
          + " what you would like the coffee maker to do."));
      if (userInput >= 0 && userInput <= 6) {
        if (userInput == 1) {
          addRecipe();
        }
        if (userInput == 2) { 
          deleteRecipe();
        }
        if (userInput == 3) { 
          editRecipe();
        }
        if (userInput == 4) {
          addInventory();
        }
        if (userInput == 5) {
          checkInventory();
        }
        if (userInput == 6) {
          makeCoffee();
        }
        if (userInput == 0) {
          System.exit(0);
        }
      } else {
        System.out.println("Please enter a number from 0 - 6");
        mainMenu();
      }
    } catch (NumberFormatException e) {
      System.out.println("Please enter a number from 0 - 6");
      mainMenu();
    }
  }
    
  /**
    * The add recipe user interface that process user input.
    */
    
  public static void addRecipe() {
    //Read in recipe name
    String name = inputOutput("\nPlease enter the recipe name: ");
    //Read in recipe price
    String priceString = inputOutput("\nPlease enter the recipe price: $");
    do {
      priceString = inputOutput("\nThe price must be positive!. Please re-enter the recipe price: $");	
    }while(!StringUtils.isNumeric(priceString);   
    //Read in amt coffee
    String coffeeString = inputOutput("\nPlease enter the units of coffee in the recipe: ");
    do {
    	coffeeString = inputOutput("\nThe unit must be positive!. Please re-enter the units: $");	
    }while(!StringUtils.isNumeric(coffeeString);
    //Read in amt milk
    String milkString = inputOutput("\nPlease enter the units of milk in the recipe: ");
    do {
    	milkString = inputOutput("\nThe unit must be positive!. Please re-enter the units: $");	
    }while(!StringUtils.isNumeric(milkString);
    //Read in amt sugar
    String sugarString = inputOutput("\nPlease enter the units of sugar in the recipe: ");
    do {
    	sugarString = inputOutput("\nThe unit must be positive!. Please re-enter the units: $");	
    }while(!StringUtils.isNumeric(sugarString);
    //Read in amt chocolate
    String chocolateString = inputOutput("\nPlease enter the units of chocolate in the recipe: ");
    do {
    	chocolateString = inputOutput("\nThe unit must be positive!. Please re-enter the units: $");	
    }while(!StringUtils.isNumeric(chocolateString);
    
    Recipe r = new Recipe();
    try {
      r.setName(name);
      r.setPrice(priceString);
      r.setAmtCoffee(coffeeString);
      r.setAmtMilk(milkString);
      r.setAmtSugar(sugarString);
      r.setAmtChocolate(chocolateString);
      boolean recipeAdded = coffeeMaker.addRecipe(r);
      if (recipeAdded) {
        System.out.println(name + " successfully added.\n");
      } else {
        System.out.println(name + " could not be added.\n");
      }
    } catch (RecipeException e) {
      System.out.println(e.getMessage());
    } finally {
      mainMenu();
    }
  }
        
  /**
   * Delete recipe user interface that procceses input.   
   */
        
  public static void deleteRecipe() {
    Recipe [] recipes = coffeeMaker.getRecipes();
    for (int i = 0; i < recipes.length; i++) {
      if (recipes[i] != null) {
        System.out.println((i + 1) + ". " + recipes[i].getName());
      }
    }
    int recipeToDelete = recipeListSelection("Please select the number of the recipe to delete.");
    if (recipeToDelete < 0) {
      System.out.println("The recipe you have selected to be deleted is not exist.\n");
      mainMenu();
    }
    
    if (recipeToDelete >= 0) {
      if (recipes[recipeToDelete] == null) {
        System.out.println("The recipe you have selected to be deleted is not exist.\n");
        mainMenu();
      }
    }
    
    for (int j = recipeToDelete; j < recipes.length; j++) {
      if (recipes[j] != null) {
        recipes[j] = recipes[j + 1];
      }
    }
    
    mainMenu();
  }
    
  /**
     * Edit recipe user interface the processes user input.
     */
        
  public static void editRecipe() {
    Recipe [] recipes = coffeeMaker.getRecipes();
    for (int i = 0; i < recipes.length; i++) {
      if (recipes[i] != null) {
        System.out.println((i + 1) + ". " + recipes[i].getName());
      }
    }
    int recipeToEdit = recipeListSelection("Please select the number of the recipe to edit.");   
    if (recipeToEdit < 0) {
      mainMenu();
    }
    
    //recipe list validation
    boolean test = true;
    for (int i = 0; i < recipes.length; i++) {
      if (recipes[i] != null) {
        if (!(recipeToEdit != (i + 1))) {
          test = false;
        }
      }
    }
    if (test == false) {
      System.out.println("There is no such number in the recipe!!!\n");
      mainMenu();
    }
  
    try {
      //Read in recipe price
      String priceString = inputOutput("\nPlease enter the recipe price: $");
      recipes[recipeToEdit].setPrice(priceString);
  	
      //Read in amt coffee
      String coffeeString = inputOutput("\nPlease enter the units of coffee in the recipe: ");
      recipes[recipeToEdit].setAmtCoffee(coffeeString);
  	
      //Read in amt milk
      String milkString = inputOutput("\nPlease enter the units of milk in the recipe: ");
      recipes[recipeToEdit].setAmtMilk(milkString);
  	
      //Read in amt sugar
      String sugarString = inputOutput("\nPlease enter the units of sugar in the recipe: ");
      recipes[recipeToEdit].setAmtSugar(sugarString);
  	
      //Read in amt chocolate
      String chocolateString = inputOutput("\nPlease enter the units of chocolate in the recipe: ");
      recipes[recipeToEdit].setAmtChocolate(chocolateString);
      System.out.println("Successfully edited.\n");
    } catch (RecipeException e) {
      System.out.println("could not be edited.\n");
      System.out.println(e.getMessage());
    } finally {
      mainMenu();
    }
  }
    
  /**
   * Add inventory user interface that processes input.
   */
    
  public static void addInventory() {
    boolean flag = true;
    try {
    String coffeeString = "", milkString = "", sugarString = "", chocolateString = "";
    while (flag==true) {

      //Read in amt coffee
      coffeeString = inputOutput("\nPlease enter the units of coffee to add: ");
      if (Integer.parseInt(coffeeString)<0) {
        flag = false;
        break;
      }

      //Read in amt milk
      milkString = inputOutput("\nPlease enter the units of milk to add: ");
      if (Integer.parseInt(milkString)<0) {
        flag = false; break;
      }

      //Read in amt sugar
      sugarString = inputOutput("\nPlease enter the units of sugar to add: ");
      if (Integer.parseInt(sugarString)<0) {
        flag = false; break;
      }

      //Read in amt chocolate
      chocolateString = inputOutput("\nPlease enter the units of chocolate to add: ");
      if (Integer.parseInt(chocolateString)<0) {
        flag = false; break;
      }
      // if flag continues to be true
      coffeeMaker.addInventory(coffeeString, milkString, sugarString, chocolateString);
      System.out.println("Inventory successfully added"); flag = false; mainMenu();
    }
        } catch (NumberFormatException | InventoryException e) {
          System.out.println("\n");
        } finally {
          flag = false;
          if (!false) {
          System.out.println("Inventory was not added");
          System.out.println("-- Please enter a positive int only and not char or string...");
          addInventory();
          mainMenu();}
        }
  }
  /**
    * Check inventory user interface that processes input.
    */
  public static void checkInventory() {
    System.out.println(coffeeMaker.checkInventory());
    mainMenu();
  }

  /**
    * Make coffee user interface the processes input.
    */
  public static void makeCoffee() {
    Recipe [] recipes = coffeeMaker.getRecipes();
    int counter = 0;
    for (int i = 0; i < recipes.length; i++) {
      if (recipes[i] == null) {
        counter++;
      }
    }
    if (counter == recipes.length) {
      System.out.println("No recipes in the recipeBook yet.");
    } else {
      for (int i = 0; i < recipes.length; i++) {
        if (recipes[i] != null) {
          System.out.println((i + 1) + ". " + recipes[i].getName()
              + " " + "price: RM" + recipes[i].getPrice());
        }
      }
      int recipeToPurchase = recipeListSelection("Please select the number "
          + "of the recipe to purchase.");
      String amountPaid ="";
      if (recipeToPurchase != -1) {
    	  amountPaid = inputOutput("Please enter the amount you wish to pay");
      }
      int amtPaid = 0;
      try {
        amtPaid = Integer.parseInt(amountPaid);
      } catch (NumberFormatException e) {
        System.out.println("Please enter a positive integer");
        mainMenu();
      }
      int change = coffeeMaker.makeCoffee(recipeToPurchase, amtPaid);
      if (change == amtPaid) {
        System.out.println("Insufficient funds to purchase.");
      } else {
        System.out.println("Thank you for purchasing "
            + coffeeMaker.getRecipes()[recipeToPurchase].getName());
        System.out.println("Your change is: " + change + "\n");
      }
      
    }
    mainMenu();
  }

  /**
   * Passes a prompt to the user and returns the user specified
   * string.
   * @param message represent prompt
   * @return String
   */
  private static String inputOutput(String message) {
    System.out.println(message);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String returnString = "";
    try {
      returnString = br.readLine();
    } catch (IOException e) {
      System.out.println("Error reading in value");
      mainMenu();
    }
    return returnString;
  }


//  private static String inputInt(String message) {
//  try {
//    digit = newScan.nextInt()
//  } catch (InputMismatchException e) {
//    e.printStackTrace();
//    System.err.println("Entered value is not an integer");
//  }

  /**
   * Passes a prompt to the user that deals with the recipe list
   * and returns the user selected number.
   * @param message represent prompt
   * @return int
   */
  private static int recipeListSelection(String message) {
	  Recipe [] recipes = coffeeMaker.getRecipes();
	  String userSelection = inputOutput(message);
    int recipe = 0;
    try {
      recipe = Integer.parseInt(userSelection) - 1;
      if (recipes[recipe] != null) {
    	  //do nothing
      }
      else {
    	System.out.println("recipe selected not in the system");
        recipe = -1;
      }
    } catch (NumberFormatException e) {
      System.out.println("Please select a number from 1-4.");
      recipe = -1;
    }
    return recipe;
  }

  /**
   * Starts the coffee maker program.
   * @param args represent arguments
   */
  public static void main(final String[] args) {
    coffeeMaker = new CoffeeMaker();
    System.out.println("Welcome to the CoffeeMaker!\n");
    mainMenu();
  }
}
