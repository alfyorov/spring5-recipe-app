package guru.springframework.converters;

import static org.junit.Assert.*;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    static final String DESCRIPTION = "some description";
    static final Long ID = 1L;

    UnitOfMeasureCommandToUnitOfMeasure classUnderTest;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void convertNullParameter() {
        assertNull(classUnderTest.convert(null));
    }

    @Test
    public void convertEmptyObject() {
        assertNotNull(classUnderTest.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() {
        //Arrange
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);

        //Act
        UnitOfMeasure uom = classUnderTest.convert(command);

        //Assert
        assertNotNull(uom);
        assertEquals(ID, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());

    }
}