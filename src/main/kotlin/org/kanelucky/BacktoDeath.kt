package org.kanelucky

import org.allaymc.api.plugin.Plugin
import org.allaymc.api.registry.Registries
import org.allaymc.api.server.Server

import org.kanelucky.command.backCommand
import org.kanelucky.listener.onDeath

class BacktoDeath : Plugin() {
    override fun onEnable() {
        val deathListener = onDeath()
        Server.getInstance().eventBus.registerListener(deathListener)
        Registries.COMMANDS.register(backCommand(deathListener))
        pluginLogger.info("Enabled")
    }
    override fun onDisable() {
        pluginLogger.info("Disabled")
    }

}