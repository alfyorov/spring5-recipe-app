package guru.springframework.domain;

import static org.junit.Assert.assertEquals;

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
    }

    @Test
    public void testGetRecipes() {
    }
}