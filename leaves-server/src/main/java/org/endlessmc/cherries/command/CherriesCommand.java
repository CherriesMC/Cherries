package org.endlessmc.cherries.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.endlessmc.cherries.CherriesConfig;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;

/**
 * The Cherries-only command. The Leaves command stays untouched.
 */
public final class CherriesCommand extends Command implements TabExecutor {

    private static final String BASE_PERMISSION = "cherries.command";
    private static final List<String> SUBCOMMANDS = List.of("reload", "config", "version", "report");
    private static final String USAGE = "Usage: /cherries <reload|config|version|report>";
    private static final String ISSUES_URL = "https://github.com/CherriesMC/Cherries/issues";

    public CherriesCommand() {
        super("cherries", "Cherries related commands", USAGE, List.of());
        this.setPermission(BASE_PERMISSION);
    }

    public static void register() {
        if (Bukkit.getPluginManager().getPermission(BASE_PERMISSION) == null) {
            Bukkit.getPluginManager().addPermission(new Permission(BASE_PERMISSION, PermissionDefault.OP));
        }
        MinecraftServer.getServer().server.getCommandMap().register("cherries", "cherries", new CherriesCommand());
        MinecraftServer.getServer().server.syncCommands();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return execute(sender, label, args);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!testPermission(sender)) {
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(Component.text(USAGE, NamedTextColor.RED));
            return true;
        }

        switch (args[0].toLowerCase(Locale.ROOT)) {
            case "reload" -> {
                try {
                    CherriesConfig.reload();
                    sender.sendMessage(Component.text("cherries.yml reloaded.", NamedTextColor.GREEN));
                } catch (final Exception ex) {
                    sender.sendMessage(Component.text("Failed to reload cherries.yml: " + ex.getMessage(), NamedTextColor.RED));
                }
            }
            case "config" -> {
                sender.sendMessage(Component.text("cherries.yml: " + CherriesConfig.getConfigFile(), NamedTextColor.GRAY));
                sender.sendMessage(Component.text("config-version: " + CherriesConfig.CURRENT_CONFIG_VERSION, NamedTextColor.GRAY));
                sender.sendMessage(Component.text("general.check-updates: " + CherriesConfig.general.isCheckUpdates(), NamedTextColor.GRAY));
                sender.sendMessage(Component.text("optimizations.accelerated-recoiling.enabled: " + CherriesConfig.optimizations.acceleratedRecoiling.isEnabled(), NamedTextColor.GRAY));
                sender.sendMessage(Component.text("optimizations.accelerated-recoiling.enable-entity-collision: " + CherriesConfig.optimizations.acceleratedRecoiling.isEnableEntityCollision(), NamedTextColor.GRAY));
                sender.sendMessage(Component.text("optimizations.accelerated-recoiling.full-bounding-box-ladders: " + CherriesConfig.optimizations.acceleratedRecoiling.isFullBoundingBoxLadders(), NamedTextColor.GRAY));
            }
            case "version" -> {
                sender.sendMessage(Component.text("Cherries " + Bukkit.getVersion(), NamedTextColor.GRAY));
                sender.sendMessage(Component.text("cherries.yml config-version: " + CherriesConfig.CURRENT_CONFIG_VERSION, NamedTextColor.GRAY));
            }
            case "report" -> sender.sendMessage(Component.text("Report issues at: ", NamedTextColor.GRAY)
                .append(Component.text(ISSUES_URL, NamedTextColor.GOLD)
                    .clickEvent(ClickEvent.openUrl(ISSUES_URL))));
            default -> sender.sendMessage(Component.text(USAGE, NamedTextColor.RED));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length != 1) {
            return List.of();
        }
        final String prefix = args[0].toLowerCase(Locale.ROOT);
        return SUBCOMMANDS.stream().filter(sub -> sub.startsWith(prefix)).toList();
    }
}
