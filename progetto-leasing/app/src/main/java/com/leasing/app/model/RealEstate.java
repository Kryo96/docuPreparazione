package com.leasing.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "real_estate")
public class RealEstate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private double surfaceArea;
    private boolean includesMaintenance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public boolean isIncludesMaintenance() {
        return includesMaintenance;
    }

    public void setIncludesMaintenance(boolean includesMaintenance) {
        this.includesMaintenance = includesMaintenance;
    }
}
