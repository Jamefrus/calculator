package calculator.service;

import calculator.domain.ComplexObject;
import org.junit.Assert;
import org.junit.Test;

public class CountingServiceImplTest {

    private CountingService service = new CountingServiceImpl();

    @Test
    public void addTest() {
        // Given
        ComplexObject objectA = new ComplexObject(1, 2);
        ComplexObject objectB = new ComplexObject(3, 4);

        ComplexObject expectedObject = new ComplexObject(4, 6);

        // When
        ComplexObject result = service.add(objectA, objectB);

        // Then
        Assert.assertEquals(expectedObject, result);
    }
}
