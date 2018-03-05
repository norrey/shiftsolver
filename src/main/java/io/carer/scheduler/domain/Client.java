package io.carer.scheduler.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Client extends AbstractDomain {

    private int clientId;
    private String name;
    private String avatar;
    private ShiftRequirements shiftRequirements;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public ShiftRequirements getShiftRequirements() {
        return shiftRequirements;
    }

    public void setShiftRequirements(ShiftRequirements shiftRequirements) {
        this.shiftRequirements = shiftRequirements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
