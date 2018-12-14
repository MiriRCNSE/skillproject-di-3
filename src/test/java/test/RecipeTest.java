package test;

import static org.junit.Assert.*;

import org.junit.Test;

import verkocht.model.Category;
import verkocht.model.Ingredient;
import verkocht.model.Recipe;
import verkocht.model.Unit;

public class RecipeTest {
    Recipe recipe = new Recipe("Pfannenkuchen", Category.VEGETARIAN, 2, 20);
    Ingredient mehl = new Ingredient("Mehl", Unit.GRAMM);
    Ingredient salz = new Ingredient("Salz", Unit.BRISE);
    Ingredient ei = new Ingredient("Ei", Unit.STUECK);
    Ingredient milch = new Ingredient("Milch", Unit.MILLILITER);
    Ingredient notAdded = new Ingredient("NotAdded", Unit.ESSLOEFFEL);

    @Test
    public void testConstructorAndGetter() {
        assertEquals("Should be Pfannenkuchen", "Pfannenkuchen", recipe.getName());
        assertEquals("Should be vegetarian", Category.VEGETARIAN, recipe.getCategory());
        assertEquals("Should be for two persons", 2, recipe.getNumberOfPeople());
        assertEquals("Should be done in 20 minutes", 20, recipe.getCookingTime());
    }
    
    @Test
    public void testIngredients() {
        Recipe.saveRecipe(recipe);
        recipe.addIngredient(mehl, 200);
        recipe.addIngredient(ei, 1);
        recipe.addIngredient(salz, 1);
        recipe.addIngredient(milch, 100);
        
        assertEquals("Should have four ingredients", 4, recipe.getIngredients().size());
        
        assertFalse(recipe.modifyByUnit("NotAdded", 4));
        assertTrue(recipe.modifyByUnit("Mehl",  new Integer(300)));
        assertEquals("Should have four ingredients", 4, recipe.getIngredients().size());
       
        assertEquals("Should be 300 now", 300, (int) recipe.getIngredients().get(mehl));

        // replacing already existing ingredient
        recipe.addIngredient(mehl, 100);
        assertEquals("Should be 100 now", 100, (int) recipe.getIngredients().get(mehl));
    }

}
