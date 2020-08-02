package u.u;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

@Environment(EnvType.CLIENT)
public class Client implements net.fabricmc.api.ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(Mod.CT, screen::new);
    }
}
