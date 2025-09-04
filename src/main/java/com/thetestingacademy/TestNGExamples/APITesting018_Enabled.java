package com.thetestingacademy.TestNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting018_Enabled {

    @Test
    public void test1(){
    Assert.assertTrue(true);
}

@Test(enabled = false)
public void test2(){
    Assert.assertTrue(true);
}


@Test
public void test3(){
    Assert.assertTrue(true);

}
}
