package com.leasing.app.model.common;

import com.leasing.app.model.Car;
import com.leasing.app.model.common.contract.ContractPeriod;
import com.leasing.app.model.common.contract.DocumentMetadata;
import com.leasing.app.model.common.contract.LeasingContract;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CAR")
public class CarLeasingContract extends LeasingContract {

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Embedded
    private ContractPeriod period;

    @Embedded
    private DocumentMetadata registrationDocument;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ContractPeriod getPeriod() {
        return period;
    }

    public void setPeriod(ContractPeriod period) {
        this.period = period;
    }

    public DocumentMetadata getRegistrationDocument() {
        return registrationDocument;
    }

    public void setRegistrationDocument(DocumentMetadata registrationDocument) {
        this.registrationDocument = registrationDocument;
    }
}
