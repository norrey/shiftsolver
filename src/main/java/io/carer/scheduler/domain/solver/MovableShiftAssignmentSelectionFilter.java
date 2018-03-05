package io.carer.scheduler.domain.solver;

import io.carer.scheduler.ShiftAssignment;
import io.carer.scheduler.ShiftSchedule;
import io.carer.scheduler.domain.ShiftDate;
import javax.annotation.Nonnull;
import org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionFilter;
import org.optaplanner.core.impl.score.director.ScoreDirector;

public class MovableShiftAssignmentSelectionFilter implements SelectionFilter<ShiftAssignment> {

    public boolean accept(@Nonnull final ScoreDirector scoreDirector, @Nonnull final ShiftAssignment shiftAssignment) {
        final ShiftSchedule shiftSchedule = (ShiftSchedule) scoreDirector.getWorkingSolution();
        return accept(shiftSchedule, shiftAssignment);
    }

    public boolean accept(@Nonnull final ShiftSchedule shiftSchedule, @Nonnull final ShiftAssignment shiftAssignment) {
        final ShiftDate shiftDate = shiftAssignment.getShift().getShiftDate();
        return shiftSchedule.getSchedulerParameterization().isInPlanningWindow(shiftDate);
    }

}
