package cz.s1mplik.boss.brainder.commands;

import cz.s1mplik.boss.brainder.Brainder;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class BrainderSpawnCommand implements CommandExecutor {



    private float phase = 1;
    private Brainder plugin;
    public BrainderSpawnCommand(Brainder main){
        this.plugin = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("spawnbrainder")) {
            spawnEnderman();

        }

        return false;
    }

    private void spawnEnderman() {


        double BrainderHealth = plugin.getConfig().getDouble("brainder.health");
        int BrainderAddDamage = plugin.getConfig().getInt("brainder.plusdamage");


        float x = plugin.getConfig().getInt("brainderspawn.x");
        float y = plugin.getConfig().getInt("brainderspawn.y");
        float z = plugin.getConfig().getInt("brainderspawn.z");
        World spawnworld = Bukkit.getWorld(plugin.getConfig().getString("brainderspawn.world"));


        Location spawnLocation = new Location(spawnworld,x,y,z);  // Spawn endermana na místě, kde stojí hráč

        Enderman enderman = spawnworld.spawn(spawnLocation, Enderman.class);
        enderman.setMaxHealth(BrainderHealth);
        enderman.setHealth(BrainderHealth);

        enderman.setFallDistance(0);
        enderman.setCustomName("§c§lBrainder");
        enderman.setCustomNameVisible(true);
        enderman.getEquipment().setItemInHand(new ItemStack(Material.NETHERITE_SWORD));
        enderman.getEquipment().setHelmet(new ItemStack(Material.NETHERITE_HELMET));
        enderman.getEquipment().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
        enderman.getEquipment().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
        enderman.getEquipment().setBoots(new ItemStack(Material.NETHERITE_BOOTS));

        double currentDamage = Objects.requireNonNull(enderman.getAttribute(org.bukkit.attribute.Attribute.GENERIC_ATTACK_DAMAGE)).getBaseValue();

        double newDamage = currentDamage + BrainderAddDamage;

        enderman.getAttribute(org.bukkit.attribute.Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(newDamage);


        enderman.setMetadata("Brainder", new FixedMetadataValue(plugin, "brainder"));

        // Vytvoření BossBar pro sledování životů Braindera
        BossBar bossBar = Bukkit.createBossBar(ChatColor.RED + "§lBrainder", BarColor.RED, BarStyle.SOLID);
        bossBar.setProgress(enderman.getHealth() / enderman.getMaxHealth());

        // Přidání všech hráčů v okolí do BossBaru
        for (Player nearbyPlayer : spawnworld.getPlayers()) {
            if (nearbyPlayer.getLocation().distanceSquared(enderman.getLocation()) < 10000) {
                bossBar.addPlayer(nearbyPlayer);
            }
        }

        // Timer pro aktualizaci BossBar podle životů Braindera
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!enderman.isValid()) {
                    bossBar.removeAll();
                    cancel();


                    double BrainderZombieHealth = plugin.getConfig().getInt("brainderzombie.health");
                    int BrainderZombieAddDamage = plugin.getConfig().getInt("brainderzombie.plusdamage");


                    Zombie brainderzombie = spawnworld.spawn(spawnLocation, Zombie.class);
                    brainderzombie.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
                    brainderzombie.setBaby(false);
                    brainderzombie.setCustomName("§c§lBrainder Zombie");

                    double currentDamage = Objects.requireNonNull(enderman.getAttribute(org.bukkit.attribute.Attribute.GENERIC_ATTACK_DAMAGE)).getBaseValue();

                    double newDamage = currentDamage + BrainderZombieAddDamage;

                    brainderzombie.getAttribute(org.bukkit.attribute.Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(newDamage);

                    brainderzombie.setCustomNameVisible(true);
                    brainderzombie.setMaxHealth(BrainderZombieHealth);
                    brainderzombie.setHealth(BrainderZombieHealth);
                    brainderzombie.setMetadata("BrainderZombie", new FixedMetadataValue(plugin, "brainderzombie"));
                    brainderzombie.addPotionEffect(PotionEffectType.SPEED.createEffect(999999999, 2));
                    return;




                }



                bossBar.setProgress(enderman.getHealth() / enderman.getMaxHealth());
            }
        }.runTaskTimer(plugin, 0, 20);



        Bukkit.broadcastMessage("§cSpawnul se Brainder v Dark Valley");
    }

}
