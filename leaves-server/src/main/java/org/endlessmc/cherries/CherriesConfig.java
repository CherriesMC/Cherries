package org.endlessmc.cherries;

import org.bukkit.configuration.file.YamlConfiguration;
import org.leavesmc.leaves.LeavesLogger;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

/**
 * Cherries-only configuration.
 *
 * <p>This is deliberately independent from {@code leaves.yml}. Leaves keys and
 * their defaults must stay untouched so existing servers and plugins keep
 * working. Everything new belongs here.</p>
 */
public final class CherriesConfig {

    public static final String FILE_NAME = "cherries.yml";
    public static final String CONFIG_HEADER = "Configuration file for Cherries.";
    public static final int CURRENT_CONFIG_VERSION = 1;

    private static File configFile;
    private static YamlConfiguration config = new YamlConfiguration();

    public static final General general = new General();

    private CherriesConfig() {
    }

    public static void init(final File file) {
        configFile = file;
        config = new YamlConfiguration();
        config.options().setHeader(Collections.singletonList(CONFIG_HEADER));
        config.options().copyDefaults(true);

        boolean loaded = false;
        if (!file.exists()) {
            try {
                final File parent = file.getParentFile();
                if (parent != null && !parent.exists() && !parent.mkdirs()) {
                    throw new IOException("Can't create directory: " + parent);
                }
                if (!file.createNewFile()) {
                    throw new IOException("Can't create file: " + file);
                }
                loaded = true;
            } catch (final IOException ex) {
                LeavesLogger.LOGGER.severe("Failure to create cherries config", ex);
            }
        } else {
            try {
                config.load(file);
                loaded = true;
            } catch (final Exception ex) {
                LeavesLogger.LOGGER.severe("Failure to load cherries config, using defaults", ex);
            }
        }

        read();
        if (loaded) {
            save();
        }
        LeavesLogger.LOGGER.info("Loaded cherries.yml (config-version " + CURRENT_CONFIG_VERSION + ")");
    }

    public static void reload() {
        if (configFile == null || !configFile.exists()) {
            throw new IllegalStateException("cherries.yml not found, please restart the server");
        }

        try {
            config.load(configFile);
            read();
            LeavesLogger.LOGGER.info("Reloaded cherries.yml");
        } catch (final Exception ex) {
            LeavesLogger.LOGGER.severe("Failure to reload cherries config", ex);
            throw new RuntimeException(ex);
        }
    }

    public static void save() {
        if (configFile == null) {
            return;
        }

        try {
            config.set("config-version", CURRENT_CONFIG_VERSION);
            config.set("general.check-updates", general.checkUpdates);
            config.save(configFile);
        } catch (final IOException ex) {
            LeavesLogger.LOGGER.severe("Unable to save cherries config", ex);
        }
    }

    private static void read() {
        general.checkUpdates = config.getBoolean("general.check-updates", true);
    }

    public static YamlConfiguration getConfig() {
        return config;
    }

    public static File getConfigFile() {
        return configFile;
    }

    public static final class General {
        private boolean checkUpdates = true;

        public boolean isCheckUpdates() {
            return checkUpdates;
        }
    }
}
