package com.leasing.app.model.common.contract;

import jakarta.persistence.*;

import java.time.LocalDate;

@Embeddable
public class DocumentMetadata {

    private String fileName;
    private String fileType;
    private LocalDate uploadDate;

    @PrePersist
    @PreUpdate
    public void setDefaultValues() {
        if (uploadDate == null) {
            uploadDate = LocalDate.now();
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }
}
