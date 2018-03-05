package io.carer.scheduler.move;

import io.carer.scheduler.ShiftAssignment;
import io.carer.scheduler.domain.Worker;
import org.optaplanner.core.impl.score.director.ScoreDirector;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 */
public class SchedulerMoveHelper {

    private SchedulerMoveHelper() {
    }

    /**
     * Moves the specified worker to the specified shiftAssignment
     *
     * @param scoreDirector
     * @param shiftAssignment
     * @param worker
     */
    public static void moveWorker(final ScoreDirector scoreDirector, final ShiftAssignment shiftAssignment, final Worker worker) {
        scoreDirector.beforeVariableChanged(shiftAssignment, "worker");
        shiftAssignment.setWorker(worker);
        scoreDirector.afterVariableChanged(shiftAssignment, "worker");
    }

}
