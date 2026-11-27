package org.leavesmc.leaves.command.subcommands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.leavesmc.leaves.command.LeavesSubcommand;
import org.leavesmc.leaves.util.LeavesUpdateHelper;

public class UpdateCommand implements LeavesSubcommand {

    @Override
    public void execute(CommandSender sender, String subCommand, String[] args) {
        sender.sendMessage(Component.text("Cherries does not support auto-update; download from " + LeavesUpdateHelper.RELEASES_URL, NamedTextColor.GRAY));
        LeavesUpdateHelper.tryUpdateLeaves();
    }
}
