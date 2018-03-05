package io.carer.scheduler.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Shift extends AbstractDomain {

    private int minDayHours = 4;
    private int maxDayHours = 8;
    private int maxWeekHours = 40;
    /*
     * Handover time is recorded in minutes
     */
    private int handOverTime = 30;
    private DayOfWeek dayOfWeek;

    private ShiftDate shiftDate;
    private Client client;
    private List<Worker> workers;
    private ShiftStatus status;

    public int getMaxDayHours() {
        return maxDayHours;
    }

    public void setMaxDayHours(int maxDayHours) {
        this.maxDayHours = maxDayHours;
    }

    public int getMinDayHours() {
        return minDayHours;
    }

    public void setMinDayHours(int minDayHours) {
        this.minDayHours = minDayHours;
    }

    public int getMaxWeekHours() {
        return maxWeekHours;
    }

    public void setMaxWeekHours(int maxWeekHours) {
        this.maxWeekHours = maxWeekHours;
    }

    public int getHandOverTime() {
        return handOverTime;
    }

    public void setHandOverTime(int handOverTime) {
        this.handOverTime = handOverTime;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public ShiftDate getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(ShiftDate shiftDate) {
        this.shiftDate = shiftDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public ShiftStatus getStatus() {
        return status;
    }

    public void setStatus(ShiftStatus status) {
        this.status = status;
    }

}
