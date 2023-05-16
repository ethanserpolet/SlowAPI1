package fun.slowfeew.api;

import fun.slowfeew.api.Database.Mongo;
import fun.slowfeew.api.Player.PlayerInfos;
import fun.slowfeew.api.Player.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class SlowAPI extends JavaPlugin {

    static SlowAPI INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        System.out.println("[SlowAPI] SlowAPI is enabled !");
        PlayerManager.createPlayer("SlowFeew");

    }

    @Override
    public void onDisable() {
        INSTANCE = null;
    }

    public static SlowAPI getInstance() {
        return INSTANCE;
    }

}