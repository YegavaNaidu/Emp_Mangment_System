package com.sample.Jile.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="Emp_Images")
public class Images {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "fileName")
    private String fileName;
    @Column(name = "fileType")
    private String fileType;
    @Column(name = "fileByte",length = 50000000)
    private byte[] fileByte;


    public Images() {
    }

    public Images(String fileName, String fileType, byte[] fileByte) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileByte = fileByte;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public byte[] getFileByte() {
        return fileByte;
    }

    public void setFileByte(byte[] fileByte) {
        this.fileByte = fileByte;
    }
}
