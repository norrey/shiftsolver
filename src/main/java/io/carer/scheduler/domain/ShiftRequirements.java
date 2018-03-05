package io.carer.scheduler.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShiftRequirements extends AbstractDomain {

    private boolean requiresNightWork;
    private boolean requiresReligiousObservation;
    private boolean requiresSupportOwnCar;
    private boolean requiresWeekendSupport;
    private boolean requiresCriminalRecordsCheck;
    private boolean requiresDriversLicence;

    private ShiftType shiftType;
    private List<SupportSkillLevel> requiredSupportSkillLevels;
    private List<SupportActivity> requiredSupportActivities;
    private List<Worker> compatibleSupport;

    public boolean isRequiresDriversLicence() {
        return requiresDriversLicence;
    }

    public void setRequiresDriversLicence(boolean requiresDriversLicence) {
        this.requiresDriversLicence = requiresDriversLicence;
    }

    public boolean isRequiresNightWork() {
        return requiresNightWork;
    }

    public void setRequiresNightWork(boolean requiresNightWork) {
        this.requiresNightWork = requiresNightWork;
    }

    public boolean isRequiresReligiousObservation() {
        return requiresReligiousObservation;
    }

    public void setRequiresReligiousObservation(boolean requiresReligiousObservation) {
        this.requiresReligiousObservation = requiresReligiousObservation;
    }

    public boolean isRequiresSupportOwnCar() {
        return requiresSupportOwnCar;
    }

    public void setRequiresSupportOwnCar(boolean requiresSupportOwnCar) {
        this.requiresSupportOwnCar = requiresSupportOwnCar;
    }

    public boolean isRequiresCriminalRecordsCheck() {
        return requiresCriminalRecordsCheck;
    }

    public void setRequiresCriminalRecordsCheck(boolean requiresCriminalRecordsCheck) {
        this.requiresCriminalRecordsCheck = requiresCriminalRecordsCheck;
    }

    public ShiftType getShiftType() {
        return shiftType;
    }

    public void setShiftType(ShiftType shiftType) {
        this.shiftType = shiftType;
    }

    public boolean isRequiresWeekendSupport() {
        return requiresWeekendSupport;
    }

    public void setRequiresWeekendSupport(boolean requiresWeekendSupport) {
        this.requiresWeekendSupport = requiresWeekendSupport;
    }

    public List<SupportSkillLevel> getRequiredSupportSkillLevels() {
        return requiredSupportSkillLevels;
    }

    public void setRequiredSupportSkillLevels(List<SupportSkillLevel> requiredSupportSkillLevels) {
        this.requiredSupportSkillLevels = requiredSupportSkillLevels;
    }

    public List<SupportActivity> getRequiredSupportActivities() {
        return requiredSupportActivities;
    }

    public void setRequiredSupportActivities(List<SupportActivity> requiredSupportActivities) {
        this.requiredSupportActivities = requiredSupportActivities;
    }

    public List<Worker> getCompatibleSupport() {
        return compatibleSupport;
    }

    public void setCompatibleSupport(List<Worker> compatibleSupport) {
        this.compatibleSupport = compatibleSupport;
    }

}
