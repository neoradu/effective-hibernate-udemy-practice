package com.arnoldgalovics.projectionspractice.domain;

public class ProductProjection {
    private String name;
    
    public ProductProjection(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
