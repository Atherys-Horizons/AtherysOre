package com.atherys.ore;

import com.atherys.ore.persistence.OreManager;
import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;

import javax.inject.Inject;

import java.io.IOException;

import static com.atherys.ore.AtherysOre.*;

@Plugin(
        id = ID,
        name = NAME,
        description = DESCRIPTION,
        version = VERSION,
        dependencies = {
                @Dependency(id = "atheryscore"),
                @Dependency(id = "luckperms")
        }
)
public class AtherysOre {

    final static String ID = "atherystowns";
    final static String NAME = "A'therys Towns";
    final static String DESCRIPTION = "A land-management plugin for the Atherys Horizons server";
    final static String VERSION = "1.0.0";

    private static AtherysOre instance;

    private static OreConfig config;

    private static boolean init = false;

    @Inject
    private Logger logger;

    private OreManager oreManager;

    private void init() {
        instance = this;

        try {
            config = new OreConfig();
            config.init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if ( config.IS_DEFAULT ) {
            logger.error("AtherysOre config is set to default. Change 'is_default' to false in the config.conf file.");
            init = false;
        }

        init = true;
    }

    private void start() {
        oreManager = new OreManager();
    }

    private void stop() {

    }

    @Listener
    void onInit(GameInitializationEvent event) {
        init();
    }

    @Listener
    void onStart(GameStartingServerEvent event) {
        if (init) start();
    }

    @Listener
    void onStop(GameStoppingServerEvent event) {
        if (init) stop();
    }

    public static AtherysOre getInstance() {
        return instance;
    }

    public static OreConfig getConfig() {
        return config;
    }

    public static Logger getLogger() {
        return getInstance().logger;
    }

    public static OreManager getOreManager() {
        return getInstance().oreManager;
    }
}
