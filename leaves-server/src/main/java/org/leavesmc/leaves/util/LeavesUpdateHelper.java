package org.leavesmc.leaves.util;

import org.leavesmc.leaves.LeavesConfig;
import org.leavesmc.leaves.LeavesLogger;

/**
 * Cherries does not use the Leaves update service.
 *
 * <p>The upstream implementation downloaded builds from the Leaves update service,
 * which would replace a Cherries server with a Leaves server. Keep the class and
 * its public entry points for compatibility with the existing config command, but
 * never download anything here.</p>
 */
public class LeavesUpdateHelper {

    public static final String RELEASES_URL = "https://github.com/CherriesMC/Cherries/releases";

    public static void init() {
        if (LeavesConfig.mics.autoUpdate.enable) {
            LeavesLogger.LOGGER.warning(
                "auto-update is not supported by Cherries; please update manually from " + RELEASES_URL
            );
        }
    }

    public static void tryUpdateLeaves() {
        LeavesLogger.LOGGER.warning(
            "Cherries does not support auto-update; please download the latest build from " + RELEASES_URL
        );
    }
}
