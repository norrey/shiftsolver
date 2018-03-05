package io.carer.scheduler.move.factory;

import io.carer.scheduler.ShiftAssignment;
import io.carer.scheduler.ShiftSchedule;
import io.carer.scheduler.domain.solver.MovableShiftAssignmentSelectionFilter;
import io.carer.scheduler.move.ShiftAssignmentSwapMove;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.heuristic.selector.move.factory.MoveListFactory;

/**
 * 
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 */
public class ShiftAssignmentSwapMoveFactory implements MoveListFactory<ShiftSchedule> {

    private MovableShiftAssignmentSelectionFilter filter = new MovableShiftAssignmentSelectionFilter();

    public List<Move> createMoveList(ShiftSchedule shiftSchedule) {
        // Filter out every immovable ShiftAssignment
        List<ShiftAssignment> shiftAssignmentList = new ArrayList<ShiftAssignment>(
                shiftSchedule.getShiftAssignments());
        for (Iterator<ShiftAssignment> it = shiftAssignmentList.iterator(); it.hasNext();) {
            ShiftAssignment shiftAssignment = it.next();
            if (!filter.accept(shiftSchedule, shiftAssignment)) {
                it.remove();
            }
        }
        List<Move> moveList = new ArrayList<Move>();
        for (ListIterator<ShiftAssignment> leftIt = shiftAssignmentList.listIterator(); leftIt.hasNext();) {
            ShiftAssignment leftShiftAssignment = leftIt.next();
            for (ListIterator<ShiftAssignment> rightIt = shiftAssignmentList.listIterator(leftIt.nextIndex()); rightIt.hasNext();) {
                ShiftAssignment rightShiftAssignment = rightIt.next();
                moveList.add(new ShiftAssignmentSwapMove(leftShiftAssignment, rightShiftAssignment));
            }
        }
        return moveList;
    }

}
