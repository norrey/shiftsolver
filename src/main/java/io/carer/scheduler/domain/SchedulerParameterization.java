package io.carer.scheduler.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SchedulerParameterization {

    private ShiftDate firstShiftDate;
    private ShiftDate lastShiftDate;

    private ShiftDate planningWindowStart;

    public ShiftDate getPlanningWindowStart() {
        return planningWindowStart;
    }

    public void setPlanningWindowStart(ShiftDate planningWindowStart) {
        this.planningWindowStart = planningWindowStart;
    }

    public ShiftDate getFirstShiftDate() {
        return firstShiftDate;
    }

    public void setFirstShiftDate(ShiftDate firstShiftDate) {
        this.firstShiftDate = firstShiftDate;
    }

    public ShiftDate getLastShiftDate() {
        return lastShiftDate;
    }

    public void setLastShiftDate(ShiftDate lastShiftDate) {
        this.lastShiftDate = lastShiftDate;
    }

    public boolean isInPlanningWindow(ShiftDate shiftDate) {
        return planningWindowStart.getStart().before(shiftDate.getStart());
    }

}
