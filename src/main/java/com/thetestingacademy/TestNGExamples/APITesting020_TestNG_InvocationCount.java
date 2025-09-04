package com.thetestingacademy.TestNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting020_TestNG_InvocationCount {


    @Test(invocationCount = 10)

    public void test01() {
        Assert.assertTrue(true);
    }

}
