package com.thetestingacademy.TestNGExamples;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class APITesting017_Testing_Parameter {


    @Parameters("browser")
    @Test
    void demo1(String value){
    System.out.println("Browser is " + value);
    // Open the Browser and Select dadadadada
    if (value.equalsIgnoreCase("Chrome")){
     System.out.println("Start my Testing Chrome");
    }
    if (value.equalsIgnoreCase("firefox")){
     System.out.println("Start my Testing in firefox");


    }
    }
}
