package org.endlessmc.cherries.bootstrap;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Extracts Cherries-bundled plugins before Paper scans the plugins directory.
 *
 * <p>This must run from {@code Main} before {@code PaperBootstrap.boot()};
 * {@code LeavesConfig.init()} happens later, so it cannot use {@code CherriesConfig}.
 * Only the small {@code cherries.yml} flag needed for the decision is read here.</p>
 */
public final class BundledPlugins {

    private static final String ACCELERATED_RECOILING_VERSION = "21.8.22-cherries.1";
    private static final String ACCELERATED_RECOILING_FILE = "AcceleratedRecoiling.jar";
    private static final String ACCELERATED_RECOILING_RESOURCE =
        "/META-INF/cherries/plugins/AcceleratedRecoiling.jar";

    private BundledPlugins() {
    }

    public static void prepare(final File pluginDirectory) {
        if (pluginDirectory == null) {
            return;
        }

        final boolean acceleratedRecoilingEnabled = isAcceleratedRecoilingEnabled();
        final File target = new File(pluginDirectory, ACCELERATED_RECOILING_FILE);
        final File marker = new File(new File(pluginDirectory, ".cherries"), "acceleratedrecoiling.version");

        if (acceleratedRecoilingEnabled) {
            installAcceleratedRecoiling(pluginDirectory, target, marker);
        } else {
            removeManagedAcceleratedRecoiling(target, marker);
        }

        if (target.isFile()) {
            // Leaves plugins with mixins need this before the plugin loader starts.
            System.setProperty("leavesclip.enable.mixin", "true");
        }
    }

    private static boolean isAcceleratedRecoilingEnabled() {
        final File cherriesFile = new File("cherries.yml");
        if (!cherriesFile.isFile()) {
            return false;
        }

        try {
            final YamlConfiguration config = YamlConfiguration.loadConfiguration(cherriesFile);
            return config.getBoolean("optimizations.accelerated-recoiling.enabled", false);
        } catch (final Exception ex) {
            log("Failed to read cherries.yml, bundled plugins stay disabled: " + ex.getMessage());
            return false;
        }
    }

    private static void installAcceleratedRecoiling(final File pluginDirectory, final File target, final File marker) {
        final String installedVersion = readMarker(marker);
        if (target.isFile() && installedVersion == null) {
            log("AcceleratedRecoiling.jar is user-managed, leaving it unchanged");
            return;
        }
        if (target.isFile() && ACCELERATED_RECOILING_VERSION.equals(installedVersion)) {
            return;
        }

        try (InputStream in = BundledPlugins.class.getResourceAsStream(ACCELERATED_RECOILING_RESOURCE)) {
            if (in == null) {
                log("Bundled AcceleratedRecoiling.jar is missing from the server jar");
                return;
            }
            if (!pluginDirectory.exists() && !pluginDirectory.mkdirs()) {
                throw new IOException("Can't create plugins directory: " + pluginDirectory);
            }
            final File markerDirectory = marker.getParentFile();
            if (markerDirectory != null && !markerDirectory.exists() && !markerDirectory.mkdirs()) {
                throw new IOException("Can't create marker directory: " + markerDirectory);
            }

            Files.copy(in, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
            Files.writeString(marker.toPath(), ACCELERATED_RECOILING_VERSION);
            log("Installed bundled AcceleratedRecoiling " + ACCELERATED_RECOILING_VERSION);
        } catch (final IOException ex) {
            log("Failed to install bundled AcceleratedRecoiling: " + ex.getMessage());
        }
    }

    private static void removeManagedAcceleratedRecoiling(final File target, final File marker) {
        if (!marker.isFile()) {
            return;
        }

        try {
            Files.deleteIfExists(target.toPath());
            Files.deleteIfExists(marker.toPath());
            log("Removed bundled AcceleratedRecoiling because optimizations.accelerated-recoiling.enabled=false");
        } catch (final IOException ex) {
            log("Failed to remove bundled AcceleratedRecoiling: " + ex.getMessage());
        }
    }

    private static String readMarker(final File marker) {
        if (!marker.isFile()) {
            return null;
        }
        try {
            return Files.readString(marker.toPath()).trim();
        } catch (final IOException ex) {
            return null;
        }
    }

    private static void log(final String message) {
        System.out.println("[Cherries] " + message);
    }
}
