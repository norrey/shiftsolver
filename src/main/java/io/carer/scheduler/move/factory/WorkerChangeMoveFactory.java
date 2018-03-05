package io.carer.scheduler.move.factory;

import io.carer.scheduler.ShiftAssignment;
import io.carer.scheduler.ShiftSchedule;
import io.carer.scheduler.domain.Worker;
import io.carer.scheduler.domain.solver.MovableShiftAssignmentSelectionFilter;
import io.carer.scheduler.move.WorkerChangeMove;
import java.util.ArrayList;
import java.util.List;

import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.heuristic.selector.move.factory.MoveListFactory;

public class WorkerChangeMoveFactory implements MoveListFactory<ShiftSchedule> {

    private MovableShiftAssignmentSelectionFilter filter = new MovableShiftAssignmentSelectionFilter();

    /**
     * Create a list of movable worker change moves available
     *
     * @param shiftSchedule
     * @return
     */
    public List<Move> createMoveList(ShiftSchedule shiftSchedule) {
        List<Move> moveList = new ArrayList<Move>();
        List<Worker> employeeList = shiftSchedule.getWorkers();
        for (ShiftAssignment shiftAssignment : shiftSchedule.getShiftAssignments()) {
            if (filter.accept(shiftSchedule, shiftAssignment)) {
                for (Worker worker : employeeList) {
                    moveList.add(new WorkerChangeMove(shiftAssignment, worker));
                }
            }
        }
        return moveList;
    }

}
