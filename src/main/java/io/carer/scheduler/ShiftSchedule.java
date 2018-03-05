package io.carer.scheduler;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import io.carer.scheduler.domain.*;
import java.util.stream.Collectors;

@PlanningSolution
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShiftSchedule extends AbstractDomain implements Solution<HardSoftScore> {

    private List<Worker> workers;
    private List<Shift> shifts;
    private List<ShiftDate> shiftDates;
    private List<ShiftRequirements> shiftRequirements;
    private List<SupportActivity> supportActivities;
    private List<SupportSkillLevel> supportSkillLevels;
    private List<Client> clients;

    //Planning entity
    private List<ShiftAssignment> shiftAssignments;
    private HardSoftScore score;

    private SchedulerParameterization schedulerParameterization;

    private boolean solved;
    private long timeSpent;
    private String responseUrl;

    @PlanningEntityCollectionProperty
    public List<ShiftAssignment> getShiftAssignments() {
        return shiftAssignments;
    }

    public void setShiftAssignments(List<ShiftAssignment> shiftAssignments) {
        this.shiftAssignments = shiftAssignments;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }

    public List<ShiftDate> getShiftDates() {
        return shiftDates;
    }

    public void setShiftDates(List<ShiftDate> shiftDates) {
        this.shiftDates = shiftDates;
    }

    public List<ShiftRequirements> getShiftRequirements() {
        return shiftRequirements;
    }

    public void setShiftRequirements(List<ShiftRequirements> shiftRequirements) {
        this.shiftRequirements = shiftRequirements;
    }

    public List<SupportActivity> getSupportActivities() {
        return supportActivities;
    }

    public void setSupportActivities(List<SupportActivity> supportActivities) {
        this.supportActivities = supportActivities;
    }

    public List<SupportSkillLevel> getSupportSkillLevels() {
        return supportSkillLevels;
    }

    public void setSupportSkillLevels(List<SupportSkillLevel> supportSkillLevels) {
        supportSkillLevels.stream().map(level -> level.getId()).collect(Collectors.toList());
        this.supportSkillLevels = supportSkillLevels;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public SchedulerParameterization getSchedulerParameterization() {
        return schedulerParameterization;
    }

    public void setSchedulerParameterization(SchedulerParameterization schedulerParameterization) {
        this.schedulerParameterization = schedulerParameterization;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public long getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(long timeSpent) {
        this.timeSpent = timeSpent;
    }

    public String getResponseUrl() {
        return responseUrl;
    }

    public void setResponseUrl(String responseUrl) {
        this.responseUrl = responseUrl;
    }

    public Collection<? extends Object> getProblemFacts() {
        List<Object> facts = new ArrayList<Object>();
        facts.addAll(workers);
        facts.addAll(shifts);
        facts.addAll(shiftDates);
        facts.addAll(shiftRequirements);
        facts.addAll(supportActivities);
        facts.addAll(supportSkillLevels);
        facts.addAll(clients);
        return facts;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    @ValueRangeProvider(id = "workerRange")
    public List<Worker> getWorkers() {
        return workers;
    }

}
