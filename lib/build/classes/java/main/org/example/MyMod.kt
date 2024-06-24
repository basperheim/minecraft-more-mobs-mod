package com.example

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext

@Mod("moremobsmod")
class MyMod {
    init {
        // Register the setup method for mod loading
        FMLJavaModLoadingContext.get().modEventBus.addListener(this::setup)
        FMLJavaModLoadingContext.get().modEventBus.addListener(this::doClientStuff)
        MinecraftForge.EVENT_BUS.register(this)
    }

    private fun setup(event: FMLCommonSetupEvent) {
        // Pre-initialization code
    }

    private fun doClientStuff(event: FMLClientSetupEvent) {
        // Client-side initialization code
    }

    @SubscribeEvent
    fun onEntitySpawn(event: EntityJoinWorldEvent) {
        // Increase spawn rate logic
        val entity = event.entity
        if (entity.isMonster) {
            // Example: double the spawn rate
            entity.world.addEntity(entity)
        }
    }
}