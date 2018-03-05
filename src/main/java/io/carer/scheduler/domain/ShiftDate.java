package io.carer.scheduler.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Calendar;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShiftDate {

    private Calendar start;
    private Calendar end;

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

}
