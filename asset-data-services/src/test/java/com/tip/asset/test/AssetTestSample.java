package com.tip.asset.test;

import org.junit.*;

/**
 * Sample Test Class for AssetTestSample
 */
public class AssetTestSample {

    // Run once for Database connection, connection pool test
    @BeforeClass
    public static void runOnceBeforeClass() {
        System.out.println("@BeforeClass - runOnceBeforeClass");
    }

    // Run once for close connection, cleanup
    @AfterClass
    public static void runOnceAfterClass() {
        System.out.println("@AfterClass - runOnceAfterClass");
    }

    // e.g. Creating an similar object and share for all @Test
    @Before
    public void runBeforeTestMethod() {
        System.out.println("@Before - runBeforeTestMethod");
    }

    // @AfterTestMethod
    @After
    public void runAfterTestMethod() {
        System.out.println("@After - runAfterTestMethod");
    }

    @Test
    public void test_method_1() {
        System.out.println("@Test - test_method_1");
    }

    @Test
    public void test_method_2() {
        System.out.println("@Test - test_method_2");
    }

}