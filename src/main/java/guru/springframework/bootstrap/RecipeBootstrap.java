package guru.springframework.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        UnitOfMeasure eachUom = getUnitOfMeasure("Each");
        UnitOfMeasure tableSpoonUom = getUnitOfMeasure("Tablespoon");
        UnitOfMeasure teaSpoonUom = getUnitOfMeasure("Teaspoon");
        UnitOfMeasure dashUom = getUnitOfMeasure("Dash");
        UnitOfMeasure pintUom = getUnitOfMeasure("Pint");
        UnitOfMeasure cupUom = getUnitOfMeasure("Cup");

        Category americanCategory = getCategory("American");
        Category mexicanCategory = getCategory("Mexican");

        //Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setCookTime(0);
        guacRecipe.setPrepTime(10);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut the avocado:\n"
                + "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n"
                + "\n"
                + "2 Mash the avocado flesh:\n"
                + "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n"
                + "\n"
                + "3 Add the remaining ingredients to taste:\n"
                + "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n"
                + "\n"
                + "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n"
                + "\n"
                + "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n"
                + "\n"
                + "4 Serve immediately:\n"
                + "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n"
                + "\n"
                + "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n"
                + "\n"
                + "Refrigerate leftover guacamole up to 3 days.");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");
        guacRecipe.setNotes(guacNotes);

        guacRecipe
                .addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom))
                .addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaSpoonUom))
                .addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom))
                .addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom))
                .addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom))
                .addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom))
                .addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom))
                .addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        //add to return list
        recipes.add(guacRecipe);

        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");
        tacosRecipe.setNotes(tacoNotes);


        tacosRecipe
                .addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom))
                .addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaSpoonUom))
                .addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teaSpoonUom))
                .addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaSpoonUom))
                .addIngredient(new Ingredient("Salt", new BigDecimal(".5"), teaSpoonUom))
                .addIngredient(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), eachUom))
                .addIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1), tableSpoonUom))
                .addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom))
                .addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tableSpoonUom))
                .addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), tableSpoonUom))
                .addIngredient(new Ingredient("small corn tortillasr", new BigDecimal(8), eachUom))
                .addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cupUom))
                .addIngredient(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), eachUom))
                .addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom))
                .addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUom))
                .addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUom))
                .addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom))
                .addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupUom))
                .addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUom));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacosRecipe);

        log.info("Added recipes");
        return recipes;
    }

    private UnitOfMeasure getUnitOfMeasure(String description) {
        return unitOfMeasureRepository
                .findByDescription(description)
                .orElseThrow(() -> new RuntimeException("Unit of measure " + description + " not found"));
    }

    private Category getCategory(String description) {
        return categoryRepository
                .findByDescription(description)
                .orElseThrow(() -> new RuntimeException("Category" + description + " not found"));
    }
}
