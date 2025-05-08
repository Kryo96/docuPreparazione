package com.leasing.app.model.common.contract;

import com.leasing.app.model.Client;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name="leasing_contract")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "contract_type", discriminatorType = DiscriminatorType.STRING)
public abstract class LeasingContract {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Client client;

    private String description;
    private LocalDate init_date;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private List<ContractDocument> documents;

    @Version
    private Integer version;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "contract_code", unique = true)
    private String contractCode;

    @PrePersist
    @PreUpdate
    public void generateContractCode() {
        if (contractCode == null) {
            contractCode = "CONTRACT-" + System.currentTimeMillis();
        }
    }

    @PreRemove
    public void deactivateContract() {
        this.active = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getInit_date() {
        return init_date;
    }

    public void setInit_date(LocalDate init_date) {
        this.init_date = init_date;
    }

    public List<ContractDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<ContractDocument> documents) {
        this.documents = documents;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }
}
