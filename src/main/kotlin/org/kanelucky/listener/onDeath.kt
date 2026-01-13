package org.kanelucky.listener

import org.allaymc.api.eventbus.EventHandler
import org.allaymc.api.eventbus.event.entity.EntityDieEvent
import org.allaymc.api.math.location.Location3d
import org.allaymc.api.math.location.Location3dc

import java.util.UUID

class onDeath {
    val deathLocations: MutableMap<UUID, Location3dc> = HashMap()
    @EventHandler
    fun onDeath(event: EntityDieEvent) {
        val entity = event.entity
        if (!entity.isPlayer) return

        val loc = entity.location

        deathLocations[entity.uniqueId] = Location3d(
            loc.x(),
            loc.y(),
            loc.z(),
            loc.dimension()
        )
    }
}