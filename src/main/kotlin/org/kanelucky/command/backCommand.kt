package org.kanelucky.command

import org.allaymc.api.command.Command
import org.allaymc.api.command.tree.CommandTree
import org.allaymc.api.permission.OpPermissionCalculator

import org.kanelucky.listener.onDeath

class backCommand(private val deathListener: onDeath) : Command(
    "back",
    "Return to your last death location",
    "back.run"
) {
    init {
        OpPermissionCalculator.NON_OP_PERMISSIONS.addAll(this.permissions);
    }
    override fun prepareCommandTree(tree: CommandTree) {
        tree.root
            .exec { context ->
                val sender = context.sender
                if (!sender.isPlayer) {
                    return@exec context.fail()
                }

                val player = sender.asPlayer()

                val last = deathListener.deathLocations[player.uniqueId]
                if (last == null) {
                    player.sendMessage("You have never died!")
                    return@exec context.fail()
                }

                player.teleport(last)
                player.sendMessage("Teleported successfully!")
                context.success()
            }
    }

}