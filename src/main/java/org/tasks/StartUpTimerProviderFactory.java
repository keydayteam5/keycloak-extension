package com.defensepoint.startup;

import main.java.org.tasks.CustomScheduledTask;
import org.keycloak.Config;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.timer.basic.BasicTimerProviderFactory;

public class StartUpTimerProviderFactory extends BasicTimerProviderFactory {

    // The default interval is 1 hour
    private long interval = 3600000;

    @Override
    public void init(Config.Scope config) {
        super.init(config);

        if(config.get("interval") != null)
            interval = Long.parseLong(config.get("interval"));
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        create(factory.create()).scheduleTask(new CustomScheduledTask(), interval, "ScheduledTask");
    }
}
