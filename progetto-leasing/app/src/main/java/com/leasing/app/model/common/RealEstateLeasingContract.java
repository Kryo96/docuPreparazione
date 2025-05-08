package com.leasing.app.model.common;

import com.leasing.app.model.RealEstate;
import com.leasing.app.model.common.contract.ContractPeriod;
import com.leasing.app.model.common.contract.DocumentMetadata;
import com.leasing.app.model.common.contract.LeasingContract;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("REAL_ESTATE")
public class RealEstateLeasingContract extends LeasingContract {

    @ManyToOne
    @JoinColumn(name = "real_estate_id")
    private RealEstate realEstate;

    @Embedded
    private ContractPeriod period;

    @Embedded
    private DocumentMetadata registrationDocument;

    public RealEstate getRealEstate() {
        return realEstate;
    }

    public void setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;
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
