package io.carer.scheduler.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Worker extends AbstractDomain {

    private int workerId;
    private boolean nightSupport;
    private boolean hasCar;
    private boolean ciminalRecordsChecked;
    private boolean weekendSupport;
    private boolean hasDrivingLicense;
    private boolean religiousObservationSupport;
    private String name;
    private String avatar;
    private List<Worker> compatibleWorkers;
    private List<SupportSkillLevel> skillLevels;
    private List<SupportActivity> supportActivities;

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public boolean isNightSupport() {
        return nightSupport;
    }

    public void setNightSupport(boolean nightSupport) {
        this.nightSupport = nightSupport;
    }

    public boolean isHasCar() {
        return hasCar;
    }

    public void setHasCar(boolean hasCar) {
        this.hasCar = hasCar;
    }

    public boolean isCiminalRecordsChecked() {
        return ciminalRecordsChecked;
    }

    public void setCiminalRecordsChecked(boolean ciminalRecordsChecked) {
        this.ciminalRecordsChecked = ciminalRecordsChecked;
    }

    public boolean isWeekendSupport() {
        return weekendSupport;
    }

    public void setWeekendSupport(boolean weekendSupport) {
        this.weekendSupport = weekendSupport;
    }

    public boolean isHasDrivingLicense() {
        return hasDrivingLicense;
    }

    public void setHasDrivingLicense(boolean hasDrivingLicense) {
        this.hasDrivingLicense = hasDrivingLicense;
    }

    public boolean isReligiousObservationSupport() {
        return religiousObservationSupport;
    }

    public void setReligiousObservationSupport(boolean religiousObservationSupport) {
        this.religiousObservationSupport = religiousObservationSupport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Worker> getCompatibleWorkers() {
        return compatibleWorkers;
    }

    public void setCompatibleWorkers(List<Worker> compatibleWorkers) {
        this.compatibleWorkers = compatibleWorkers;
    }

    public List<SupportSkillLevel> getSkillLevels() {
        return skillLevels;
    }

    public void setSkillLevels(List<SupportSkillLevel> skillLevels) {
        this.skillLevels = skillLevels;
    }

    public List<SupportActivity> getSupportActivities() {
        return supportActivities;
    }

    public void setSupportActivities(List<SupportActivity> supportActivities) {
        this.supportActivities = supportActivities;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
