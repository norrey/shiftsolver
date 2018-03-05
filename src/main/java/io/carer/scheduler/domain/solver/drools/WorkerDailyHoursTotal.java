package io.carer.scheduler.domain.solver.drools;

import io.carer.scheduler.domain.Worker;
import java.util.Objects;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Jan 26, 2017, 10:57:44 AM
 */
public class WorkerDailyHoursTotal {

    private Worker worker;
    private int dayOfMonth;
    private int total;

    public WorkerDailyHoursTotal(Worker worker, int dayOfMonth, int total) {
        this.worker = worker;
        this.dayOfMonth = dayOfMonth;
        this.total = total;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
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
        final WorkerDailyHoursTotal other = (WorkerDailyHoursTotal) obj;
        if (this.dayOfMonth != other.dayOfMonth) {
            return false;
        }
        if (!Objects.equals(this.worker, other.worker)) {
            return false;
        }
        return true;
    }

}
