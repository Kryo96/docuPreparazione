package com.leasing.app.model.common.contract;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class ContractPeriod {
    private LocalDate startDate;
    private LocalDate endDate;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return LocalDate.now().isBefore(endDate);
    }
}
