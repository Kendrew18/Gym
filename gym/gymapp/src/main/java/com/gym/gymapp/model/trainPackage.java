package com.gym.gymapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "packageTraining")
public class trainPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "package_name", columnDefinition = "TEXT")
    private String package_name;

    @Column(name = "package_desc", columnDefinition = "TEXT")
    private String package_desc;

    @Column(name = "package_difficulty")
    private String package_difficulty;

    @Column(name = "package_vid_list", columnDefinition = "TEXT")
    private List<Object> packageVidList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getPackage_desc() {
        return package_desc;
    }

    public void setPackage_desc(String package_desc) {
        this.package_desc = package_desc;
    }

    public String getPackage_difficulty() {
        return package_difficulty;
    }

    public void setPackage_difficulty(String package_difficulty) {
        this.package_difficulty = package_difficulty;
    }

    public List<Object> getPackageVidList() {
        return packageVidList;
    }

    public void setPackageVidList(List<Object> packageVidList) {
        this.packageVidList = packageVidList;
    }

}

