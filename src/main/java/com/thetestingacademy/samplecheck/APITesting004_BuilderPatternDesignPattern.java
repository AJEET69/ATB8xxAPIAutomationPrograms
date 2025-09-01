package com.thetestingacademy.samplecheck;

public class APITesting004_BuilderPatternDesignPattern {

    // Change return type of each method as Class type
    // "this" always points to current/calling Object. Returning the same to
    // have same reference

    public APITesting004_BuilderPatternDesignPattern step1() {
        System.out.println("Step 1 is Started");
        System.out.println("Step 2 is done");
        return this;
    }

    public APITesting004_BuilderPatternDesignPattern step2() {
        System.out.println("Step 2 is Started");
        System.out.println("Step 2 is done");
        return this;
    }

    public APITesting004_BuilderPatternDesignPattern step3(String name) {
        System.out.println("Step 3 is Started");
        System.out.println("Step 3 is done -> + name ");
        return  this;
    }

    public static void main(String[] args) {
          APITesting004_BuilderPatternDesignPattern bp = new APITesting004_BuilderPatternDesignPattern();
          bp.step1().step2().step3("Pramod");



    }
}