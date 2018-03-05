package io.carer.scheduler.domain.solver.drools;

import io.carer.scheduler.domain.SupportSkillLevel;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Jan 25, 2017, 5:22:57 PM
 */
public class AcquiredSkillLevel {

    private SupportSkillLevel skillLevel;

    public SupportSkillLevel getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SupportSkillLevel skillLevel) {
        this.skillLevel = skillLevel;
    }

}
