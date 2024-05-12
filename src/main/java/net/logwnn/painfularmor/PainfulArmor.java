package net.logwnn.painfularmor;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import net.minecraft.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PainfulArmor implements ModInitializer {
	public static final String MOD_ID = "painful-armor";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private int tickCounter = 0;

	@Override
	public void onInitialize() {
		LOGGER.info("Painful Armor is Initialized");

		ServerTickEvents.START_WORLD_TICK.register(world -> {
			MinecraftServer server = world.getServer();
			if (server != null) {
				tickCounter++;
				if (tickCounter >= 20) { // Minecraft has 20 ticks per second
					tickCounter = 0;
					for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
						if (isWearingArmor(player)) {
							damagePlayer(player);
						}
					}
				}
			}
		});

	}

	private boolean isWearingArmor(PlayerEntity player) {
		for (ItemStack stack : player.getArmorItems()) {
			if (!stack.isEmpty()) {
				return true; // Player is wearing armor
			}
		}
		return false; // Player is not wearing armor
	}

	private void damagePlayer(ServerPlayerEntity player) {
		float damage = 1.0f; // Adjust the amount of damage as needed
		float newHealth = Math.max(player.getHealth() - damage, 0.0f);
		player.setHealth(newHealth);
	}
}