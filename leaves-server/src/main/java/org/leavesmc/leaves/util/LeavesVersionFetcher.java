package org.leavesmc.leaves.util;

import com.destroystokyo.paper.PaperVersionFetcher;
import io.papermc.paper.ServerBuildInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.TextColor.color;

/**
 * Version message for Cherries.
 *
 * <p>Only GitHub is queried. The upstream Leaves API is intentionally not used:
 * it reports Leaves builds and would point users at the wrong download page.</p>
 */
public class LeavesVersionFetcher extends PaperVersionFetcher {

    private static final int DISTANCE_ERROR = -1;
    private static final int DISTANCE_UNKNOWN = -2;
    private static final String REPOSITORY = "CherriesMC/Cherries";
    private static final String DOWNLOAD_PAGE = "https://github.com/CherriesMC/Cherries/releases";

    @NotNull
    @Override
    public Component getVersionMessage() {
        final ServerBuildInfo build = ServerBuildInfo.buildInfo();
        if (build.buildNumber().isEmpty() && build.gitCommit().isEmpty()) {
            return text("You are running a development version without access to version information", color(0xFF5300));
        }
        if (build.buildNumber().isEmpty()) {
            return text("You are running a development version from CI", color(0xFF5300));
        }
        return getUpdateStatusMessage(build);
    }

    private static Component getUpdateStatusMessage(@NotNull final ServerBuildInfo build) {
        int distance = DISTANCE_ERROR;
        final Optional<String> gitBranch = build.gitBranch();
        final Optional<String> gitCommit = build.gitCommit();
        if (gitBranch.isPresent() && gitCommit.isPresent()) {
            distance = fetchDistanceFromGitHub(REPOSITORY, gitBranch.get(), gitCommit.get());
        }

        return switch (distance) {
            case DISTANCE_ERROR -> Component.text("Error obtaining version information", NamedTextColor.YELLOW);
            case 0 -> Component.text("You are running the latest version", NamedTextColor.GREEN);
            case DISTANCE_UNKNOWN -> Component.text("Unknown version", NamedTextColor.YELLOW);
            default -> Component.text("You are " + distance + " version(s) behind", NamedTextColor.YELLOW)
                .append(Component.newline())
                .append(Component.text("Download the new version at: ")
                    .append(Component.text(DOWNLOAD_PAGE, NamedTextColor.GOLD)
                        .hoverEvent(Component.text("Click to open", NamedTextColor.WHITE))
                        .clickEvent(ClickEvent.openUrl(DOWNLOAD_PAGE))));
        };
    }
}
