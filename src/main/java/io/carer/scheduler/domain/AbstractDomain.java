package io.carer.scheduler.domain;

import java.io.Serializable;
import org.apache.commons.lang3.builder.CompareToBuilder;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 */
public class AbstractDomain implements Serializable, Comparable<AbstractDomain> {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int compareTo(AbstractDomain other) {
        return new CompareToBuilder()
                .append(getClass().getName(), other.getClass().getName())
                .append(id, other.getId())
                .toComparison();
    }

}
