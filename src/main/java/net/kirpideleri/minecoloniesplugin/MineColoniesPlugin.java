package net.kirpideleri.minecoloniesplugin;

import com.ldtteam.structurize.util.LanguageHandler;
import net.kirpideleri.minecoloniesplugin.items.ItemReportBoard;
import net.kirpideleri.minecoloniesplugin.util.constant.Constants;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("minecoloniesplugin")
public class MineColoniesPlugin
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);

    public static final RegistryObject<ItemReportBoard> REPORT_BOARD = ITEMS.register("reportboard",()-> new ItemReportBoard(new Item.Properties()));

    public MineColoniesPlugin() {

        LanguageHandler.loadLangPath("assets/minecoloniesplugin/lang/%s.json"); // hotfix config comments, it's ugly bcs it's gonna be replaced

        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
