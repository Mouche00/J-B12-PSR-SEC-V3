package org.yc.keycloak.events;

import lombok.Getter;
import org.yc.keycloak.dtos.RaceDTO;
import org.springframework.context.ApplicationEvent;

@Getter
public class RaceClosedEvent extends ApplicationEvent {
    private final RaceDTO race;
    public RaceClosedEvent(Object source, RaceDTO race) {
        super(source);
        this.race = race;
    }
}
