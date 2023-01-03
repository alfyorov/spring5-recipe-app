package guru.springframework.domain;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

    Category classUnderTest;

    @Before
    public void setUp() {
        classUnderTest = new Category();
    }

    @Test
    public void testGetId() {
        Long id = 4L;
        classUnderTest.setId(id);

        assertEquals(id, classUnderTest.getId());
    }

    @Test
    public void testGetDescription() {
        String description = "Some description";
        classUnderTest.setDescription(description);

        assertEquals(description, classUnderTest.getDescription());
    }

    @Test
    public void testGetRecipes() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe);
        classUnderTest.setRecipes(recipeData);

        assertEquals(1, classUnderTest.getRecipes().size());
    }
}