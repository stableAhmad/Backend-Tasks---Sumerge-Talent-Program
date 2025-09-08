package com.outerbean.outerbean;

import org.springframework.stereotype.Component;

@Component
public class ConcreteOuterBean implements OuterBean {
    @Override
    public void performAction() {
        System.out.println("ConcreteOuterBean is performing an action.");
    }
    
}
