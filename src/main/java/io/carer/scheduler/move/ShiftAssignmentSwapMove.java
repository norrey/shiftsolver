package io.carer.scheduler.move;

import io.carer.scheduler.ShiftAssignment;
import io.carer.scheduler.domain.Worker;
import java.util.Arrays;
import java.util.Collection;
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
public class ShiftAssignmentSwapMove extends AbstractMove {

    private ShiftAssignment leftShiftAssignment;
    private ShiftAssignment rightShiftAssignment;

    public ShiftAssignmentSwapMove(ShiftAssignment leftShiftAssignment, ShiftAssignment rightShiftAssignment) {
        this.leftShiftAssignment = leftShiftAssignment;
        this.rightShiftAssignment = rightShiftAssignment;
    }

    public ShiftAssignment getLeftShiftAssignment() {
        return leftShiftAssignment;
    }

    public void setLeftShiftAssignment(ShiftAssignment leftShiftAssignment) {
        this.leftShiftAssignment = leftShiftAssignment;
    }

    public ShiftAssignment getRightShiftAssignment() {
        return rightShiftAssignment;
    }

    public void setRightShiftAssignment(ShiftAssignment rightShiftAssignment) {
        this.rightShiftAssignment = rightShiftAssignment;
    }

    @Override
    protected void doMoveOnGenuineVariables(ScoreDirector scoreDirector) {
        Worker oldLeftWorker = leftShiftAssignment.getWorker();
        Worker oldRightWorker = rightShiftAssignment.getWorker();
        SchedulerMoveHelper.moveWorker(scoreDirector, leftShiftAssignment, oldRightWorker);
        SchedulerMoveHelper.moveWorker(scoreDirector, rightShiftAssignment, oldLeftWorker);
    }

    /**
     * Swap is only possible if the left shift worker is not equal the right shift worker
     *
     * @param scoreDirector
     * @return
     */
    public boolean isMoveDoable(ScoreDirector scoreDirector) {
        return !ObjectUtils.equals(leftShiftAssignment.getWorker(), rightShiftAssignment.getWorker());
    }

    public Move createUndoMove(ScoreDirector scoreDirector) {
        return new ShiftAssignmentSwapMove(rightShiftAssignment, leftShiftAssignment);
    }

    /**
     * Return the affected planning entities during swap
     *
     * @return
     */
    public Collection<? extends Object> getPlanningEntities() {
        return Arrays.asList(leftShiftAssignment, rightShiftAssignment);
    }

    /**
     * Return the planning values for the affected shift assignments
     *
     * @return
     */
    public Collection<? extends Object> getPlanningValues() {
        return Arrays.asList(leftShiftAssignment.getWorker(), rightShiftAssignment.getWorker());
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof ShiftAssignmentSwapMove) {
            ShiftAssignmentSwapMove otherSwapMove = (ShiftAssignmentSwapMove) other;
            return new EqualsBuilder()
                    .append(leftShiftAssignment, otherSwapMove.leftShiftAssignment)
                    .append(rightShiftAssignment, otherSwapMove.rightShiftAssignment)
                    .isEquals();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(leftShiftAssignment)
                .append(rightShiftAssignment)
                .toHashCode();
    }

}
