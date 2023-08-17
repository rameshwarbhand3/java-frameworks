package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)//Display test class,method name
//@DisplayNameGeneration(DisplayNameGenerator.Simple.class)//Display default test name
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)//use when we are providing underscore in method name
//@TestMethodOrder(MethodOrderer.DisplayName.class)//give order according to dispaly method by alphabetically
//@TestMethodOrder(MethodOrderer.MethodName.class)//give order according to method name by alphabetically
//@TestMethodOrder(MethodOrderer.Random.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DemoUtilsTest {
    DemoUtils demoUtils;

    @BeforeEach
    public void SetupTest() {
        System.out.println("Execute before Each test method");
        demoUtils = new DemoUtils();
    }

    @AfterEach
    public void afterEach() {
        System.out.println("Run after ");
        System.out.println();
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Run before once");
        System.out.println();
    }

    @AfterAll
    static void afterAll() {
        System.out.println();
        System.out.println("Run after once");
    }

    @Test
    //DisplayName("Add method test")//At terminal, we see name instead of test name.
    @DisplayName("Equal and Not equals")
    @Order(2)//for custom execution it will execute after @order(1)
    public void testEqualAndNotEquals() {
        System.out.println("Running test : testEqualAndNotEquals");
        //setup(when)
        //DemoUtils demoUtils = new DemoUtils();
        int expected = 6;
        //execute
        int actual = demoUtils.add(2, 4);

        //assert
        //assertEquals(expected,actual);
        assertEquals(6, demoUtils.add(2, 4));
        assertNotEquals(6, demoUtils.add(1, 9));
    }

    @Test
    void multiply(){
        assertEquals(10,demoUtils.multiply(2,5));
    }

    @Test
    @DisplayName("Null Not Null")
    @Order(1)
    public void testNullNotNull() {
        System.out.println("Running test : testNullNOtNull");
        DemoUtils demoUtils = new DemoUtils();
        assertNotNull(demoUtils.checkNull("Ram"), "object should nt be null");
        assertNull(demoUtils.checkNull(null), "object should be null");

    }

    @Test
    @DisplayName("Same or Not")
    void testSameOrNot() {
        String str = "luv2code";
        assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(), "object should be same");
        assertNotSame(str, demoUtils.getAcademy());
    }

    @Test
    @DisplayName("True And False")
    void testTrueFalse() {//check given method true or false
        int n1 = 10;
        int n2 = 5;
        assertTrue(demoUtils.isGreater(10, 5));
        assertFalse(demoUtils.isGreater(5, 10));
    }

    @Test
    @DisplayName("Array Equal")
    void testArrayEqual() {//check both array equal or not
        String arr[] = {"A", "B", "C"};
        assertArrayEquals(arr, demoUtils.getFirstThreeLettersOfAlphabet());
    }

    @Test
    @DisplayName("Iterable Equal")
    void testIterableEqual() {//check both iterable are equal or not(list,set,map)
        List<String> theList = List.of("luv", "2", "code");
        assertIterableEquals(theList, demoUtils.getAcademyInList());
    }

    @Test
    @DisplayName("Line Match")
    void testLineMatch() {
        List<String> list = Arrays.asList("luv", "2", "code");
        assertLinesMatch(list, demoUtils.getAcademyInList());
    }
    @Test
    @DisplayName("Thows And Does Not Throws")
    void testThrowsAndDoesNotThrows(){
        assertThrows(Exception.class,()->{demoUtils.throwException(-1);});
        assertDoesNotThrow(()->{demoUtils.throwException(10);});
    }
    @Test
    @DisplayName("Time out")
    void testTimeout(){
        assertTimeoutPreemptively(Duration.ofSeconds(6),()->{demoUtils.checkTimeout();},"");
    }
}