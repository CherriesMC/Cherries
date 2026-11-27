package org.endlessmc.cherries;

import org.endlessmc.cherries.command.CherriesCommand;
import org.leavesmc.leaves.LeavesLogger;

import java.io.File;

/**
 * Small entry point called from {@code LeavesConfig.init} after the server
 * command map is available.
 */
public final class CherriesBootstrap {

    private static boolean initialized;

    private CherriesBootstrap() {
    }

    public static void init(final File configFile) {
        if (initialized) {
            return;
        }
        initialized = true;

        CherriesConfig.init(configFile);
        try {
            CherriesCommand.register();
        } catch (final Exception ex) {
            LeavesLogger.LOGGER.severe("Failure to register /cherries", ex);
        }
    }
}
