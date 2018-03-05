package io.carer.scheduler;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.carer.scheduler.domain.AbstractDomain;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import io.carer.scheduler.domain.Shift;
import io.carer.scheduler.domain.Worker;

@PlanningEntity
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShiftAssignment extends AbstractDomain {

    private Worker worker;
    private Shift shift;

    @PlanningVariable(valueRangeProviderRefs = {"workerRange"})
    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

}
