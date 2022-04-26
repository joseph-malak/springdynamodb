package JavaBrains;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@DisplayName("when running mathutils")
class MathUtilsTest {

    //member variable, kind of shared state
    MathUtils mathUtils;

    //will run this method before each method call... in this case instaniating mathUtils
    @BeforeEach
    void init() {
        mathUtils = new MathUtils();
    }

    @Nested
    @DisplayName("Add method")
    @Tag("Math")
    class AddTest{

        @Test
        @DisplayName("Test add method for positive")
        void testAddPositiveNumbers(){
            assertEquals(2,mathUtils.add(1,1),"should add two numbers");
        }

        @Test
        @DisplayName("Test add method for negative")
        void testAddNegativeNumbers(){
            //because of the lambda we have set as the message producer (the third variable in the assertEquals), it will only produce the string if the method fails
            int expected = -2;
            int actual = mathUtils.add(-1,-1);
            assertEquals(-2,actual ,()->"should add two numbers but returns" +expected+"and not" + actual);
        }

    }

    @AfterEach
    void cleanUp(){
        System.out.println("Cleaning up");
    }

    @Test
    void test(){
       int expected = 6;
       int actual = 7;
       assertEquals(expected, actual, "we can add an additional message for detailed testing");
    }

    @RepeatedTest(3)//this will repeat this test the number of times passed in
    void testComputeCircleRadius(RepetitionInfo repetitionInfo){
        repetitionInfo.getCurrentRepetition();
        assertEquals(314.15, mathUtils.computeCirclearea(10),"Should return right circle area");

    }

    @Test
    @DisplayName("add method test")
    void addingTest(){
        assertEquals(2,mathUtils.add(1,1),"should add");
    }

    @Test
    void sub() {
        assertEquals(5, mathUtils.sub(7,2), "we can add an additional message for detailed testing");
    }

    @Test
    void mult() {

        int expected = 6;
        int actual = 7;
        assertEquals(expected, actual, "we can add an additional message for detailed testing");
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void div() {

        boolean isServerUp = false;

//will only run the test if true
        //assertAll means that everything is correct
        assumeTrue(isServerUp);


        assertThrows(ArithmeticException.class, () -> mathUtils.div(1,0), "divide by zero should throw");


    }

    @Test
    @Disabled
    @DisplayName("TDD method.Should not run")
    void testDisable(){
        fail("test should be disabled");
    }

    @Test
    @DisplayName("multiply method")
    void testMultiply(){
        //assertEquals(4, mathUtils.mult(2,2), "Should return the right product");

        //if any fail then the test fails
        assertAll(
                //This is why lambdas are very important
                () -> assertEquals(4, mathUtils.mult(2,2), "Should return the right product"),
                () -> assertEquals(0, mathUtils.mult(2,0), "Should return the right product"),
                () -> assertEquals(-2, mathUtils.mult(2,-1), "Should return the right product")
        );
    }




}