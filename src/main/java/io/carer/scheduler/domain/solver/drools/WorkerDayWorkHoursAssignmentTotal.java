package io.carer.scheduler.domain.solver.drools;

import io.carer.scheduler.domain.Worker;
import java.io.Serializable;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 */
public class WorkerDayWorkHoursAssignmentTotal implements Comparable<WorkerDayWorkHoursAssignmentTotal>, Serializable {

    private Worker worker;
    private int total;

    public WorkerDayWorkHoursAssignmentTotal(Worker worker, int total) {
        this.worker = worker;
        this.total = total;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof WorkerDayWorkHoursAssignmentTotal) {
            WorkerDayWorkHoursAssignmentTotal other = (WorkerDayWorkHoursAssignmentTotal) o;
            return new EqualsBuilder()
                    .append(worker, other.worker)
                    .append(total, other.total)
                    .isEquals();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(worker)
                .append(total)
                .toHashCode();
    }

    public int compareTo(WorkerDayWorkHoursAssignmentTotal other) {
        return new CompareToBuilder()
                .append(worker, other.worker)
                .append(total, other.total)
                .toComparison();
    }

    @Override
    public String toString() {
        return worker + " = " + total;
    }

}
