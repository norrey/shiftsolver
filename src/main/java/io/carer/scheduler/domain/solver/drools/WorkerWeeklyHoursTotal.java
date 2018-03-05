package io.carer.scheduler.domain.solver.drools;

import io.carer.scheduler.domain.Worker;
import java.util.Objects;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Jan 26, 2017, 10:57:44 AM
 */
public class WorkerWeeklyHoursTotal {

    private Worker worker;
    private int weekOfYear;
    private int total;

    public WorkerWeeklyHoursTotal(Worker worker, int weekOfYear, int total) {
        this.worker = worker;
        this.weekOfYear = weekOfYear;
        this.total = total;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public int getWeekOfYear() {
        return weekOfYear;
    }

    public void setWeekOfYear(int weekOfYear) {
        this.weekOfYear = weekOfYear;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WorkerWeeklyHoursTotal other = (WorkerWeeklyHoursTotal) obj;
        if (this.weekOfYear != other.weekOfYear) {
            return false;
        }
        if (!Objects.equals(this.worker, other.worker)) {
            return false;
        }
        return true;
    }

}
