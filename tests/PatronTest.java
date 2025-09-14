import bcu.cmp5332.librarysystem.model.Patron;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class PatronTest {

    @Test
    public void testPatronHasEmail() {
        Patron patron = new Patron(0, "TestName", "TestPhone", "TestEmail", false);
        assertNotNull(patron.getEmail());
        patron.setEmail("test@test.test");
        assertEquals("test@test.test", patron.getEmail());
    }
}
