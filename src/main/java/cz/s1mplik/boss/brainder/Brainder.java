package cz.s1mplik.boss.brainder;
import cz.s1mplik.boss.brainder.commands.BrainderSpawnCommand;
import cz.s1mplik.boss.brainder.events.BrainderManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Brainder extends JavaPlugin {

    public Player commandSenderPlayer;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BrainderManager(), this);
        getCommand("spawnbrainder").setExecutor(new BrainderSpawnCommand(this));
        getLogger().info("Plugin Brainder se zapnul");
        saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Brainder se vypnul");
    }

}
