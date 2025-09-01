package com.thetestingacademy.samplecheck;

public class APITesting003_NoDesignPatten {
    public void step1(){
        System.out.println("Step 1");
    }

    public void step2(){
        System.out.println("Step 2");
    }

    public void step3(String p){
        System.out.println("Step 3");
    }

    public static void main(String[] args) {
        APITesting003_NoDesignPatten np = new APITesting003_NoDesignPatten();
        np.step1();
        np.step2();
        np.step3("Pramod");

  // np.step1().step2().step3("Pramod")

    }
}
