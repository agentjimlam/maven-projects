package sg.edu.ntu.simple_crm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DemoServiceTest {

    DemoService demoService;

    // We could move the instantiation of the DemoService into the @BeforeEach method so that we do not have to repeat the code in every test method.
    @BeforeEach
    public void init(){
        demoService = new DemoService();
    }
    
    @Test
    public void testAdd(){
        // 1. SETUP
        // Create the instance of the class to test
        // DemoService demoService = new DemoService();

        // Define expected result
        int expectedResult = 8;

        // 2. EXECUTE
        // Execute the method to be tested
        int actualResult = demoService.add(3, 5);

        // 3. Assert
        // Compare actual result with expected result
        assertEquals(expectedResult, actualResult, "3 + 5 should be 8");
    }

    @Test
    public void testSubtract(){
        // 1. SETUP
        // DemoService demoService = new DemoService();

        int expectedResult = 2;

        // 2. EXECUTE
        int actualResult = demoService.subtract(5, 3);

        // 3. ASSERT
        assertEquals(expectedResult, actualResult, "5 - 3 should be 2");
    }

    @Test
    public void testMultiply(){
        // 1. SETUP
        int expectedResult = 15;

        // 2. EXECUTE
        int actualResult = demoService.multiply(5, 3);

        // 3. ASSERT
        assertEquals(expectedResult, actualResult, "5 * 3 should be 15");
    }


    @Test
    public void testDivide(){
        // 1. SETUP
        int expectedResult = 2;

        // 2. EXECUTE
        int actualResult = demoService.divide(10, 5);

        // 3. ASSERT
        assertEquals(expectedResult, actualResult, "10 / 5 should be 2");
    }

    @Test
    public void testDivideByZeroThrowsException(){
        assertThrows(ArithmeticException.class, () -> demoService.divide(10, 0));
    }

    // Separate out the tests so each test can focus on a specific scenario 
    // Easier to see which case failed and fix the problem directly
    @Test
    public void testIsEvenWhenTrue(){
        // 1. SETUP
        // 2. EXECUTE
        boolean actualResult1 = demoService.isEven(2);

        // 3. ASSERT
        assertEquals(true, actualResult1, "2 is an even number");
    }

    @Test
    public void testIsEvenWhenFalse(){
        // 1. SETUP
        // 2. EXECUTE
        boolean actualResult2 = demoService.isEven(3);

        // 3. ASSERT
        assertEquals(false, actualResult2, "3 is not an even number");
    }

}
