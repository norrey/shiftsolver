package io.carer.scheduler.move;

import io.carer.scheduler.ShiftAssignment;
import io.carer.scheduler.domain.Worker;
import java.util.Collection;
import java.util.Collections;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.optaplanner.core.impl.heuristic.move.AbstractMove;
import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.score.director.ScoreDirector;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 */
public class WorkerChangeMove extends AbstractMove {

    private ShiftAssignment shiftAssignment;
    private Worker toWorker;

    public ShiftAssignment getShiftAssignment() {
        return shiftAssignment;
    }

    public void setShiftAssignment(ShiftAssignment shiftAssignment) {
        this.shiftAssignment = shiftAssignment;
    }

    public Worker getToWorker() {
        return toWorker;
    }

    public void setToWorker(Worker toWorker) {
        this.toWorker = toWorker;
    }

    public WorkerChangeMove(ShiftAssignment shiftAssignment, Worker toWorker) {
        this.shiftAssignment = shiftAssignment;
        this.toWorker = toWorker;
    }

    /**
     *
     * @param scoreDirector
     */
    @Override
    protected void doMoveOnGenuineVariables(ScoreDirector scoreDirector) {
        SchedulerMoveHelper.moveWorker(scoreDirector, shiftAssignment, toWorker);
    }

    /**
     * You can only change the workers if they are different
     *
     * @param scoreDirector
     * @return
     */
    public boolean isMoveDoable(ScoreDirector scoreDirector) {
        return !ObjectUtils.equals(shiftAssignment.getWorker(), toWorker);
    }

    /**
     * Revert the change by assigning to the same shift's worker
     *
     * @param scoreDirector
     * @return
     */
    public Move createUndoMove(ScoreDirector scoreDirector) {
        return new WorkerChangeMove(shiftAssignment, shiftAssignment.getWorker());
    }

    /**
     * Return the planning entity's collection
     *
     * @return
     */
    public Collection<? extends Object> getPlanningEntities() {
        return Collections.singletonList(shiftAssignment);
    }

    /**
     * Return the planning value's collection
     *
     * @return
     */
    public Collection<? extends Object> getPlanningValues() {
        return Collections.singletonList(toWorker);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof WorkerChangeMove) {
            WorkerChangeMove otherChangeMove = (WorkerChangeMove) other;
            return new EqualsBuilder()
                    .append(shiftAssignment, otherChangeMove.shiftAssignment)
                    .append(toWorker, otherChangeMove.toWorker)
                    .isEquals();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(shiftAssignment)
                .append(toWorker)
                .toHashCode();
    }

}
