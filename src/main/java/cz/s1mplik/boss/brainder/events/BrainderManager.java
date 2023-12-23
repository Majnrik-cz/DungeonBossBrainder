package cz.s1mplik.boss.brainder.events;

import org.bukkit.Effect;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

public class BrainderManager implements Listener {


    @EventHandler
    public void onDamage (EntityDamageByEntityEvent e) {
        if(e.getEntity() instanceof Enderman &&e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if(e.getEntity().hasMetadata("Brainder")) {
                if(ThreadLocalRandom.current().nextDouble() < 0.5) {
                    e.setCancelled(true);
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 10,10);
                    p.sendMessage("§c§lTvůj útok byl vyblokován!");
                }

                if(ThreadLocalRandom.current().nextDouble() < 0.2) {
                    p.getWorld().strikeLightningEffect(p.getLocation());
                    p.sendMessage("§c§lByl si zasažen bleskem!");

                }
                if(ThreadLocalRandom.current().nextDouble() < 0.1) {
                    p.setVelocity(new Vector(0,2,0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,60,2));
                    p.sendMessage("§c§lByl si vyhozen do vzduchu");
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.ENDERMITE);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.ENDERMITE);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.ENDERMITE);
                    p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP,15,10);

                }
                if(ThreadLocalRandom.current().nextDouble() < 0.1) {
                    p.setVelocity(new Vector(0,0,0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,10,2));
                    p.sendMessage("§4§lByl jsi zpomalen");
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SILVERFISH);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SILVERFISH);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SILVERFISH);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SILVERFISH);
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_DESTROY,15,10);

                }

                if (ThreadLocalRandom.current().nextDouble() < 0.5) {
                    p.setFireTicks(60);
                    p.sendMessage("§c§lZačíná tu být horko!");
                    p.getWorld().playEffect(p.getLocation(), Effect.BOW_FIRE, 1, 10);
                }

                if (ThreadLocalRandom.current().nextDouble() < 0.5) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 40, 1)); // Přidá hráči efekt zmatení
                    p.sendMessage("§9§lZtrácíš orientaci!");
                }






            }
        }
        if(e.getEntity() instanceof Zombie &&e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if(e.getEntity().hasMetadata("BrainderZombie")) {
                if(ThreadLocalRandom.current().nextDouble() < 0.5) {
                    e.setCancelled(true);
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 10,10);
                    p.sendMessage("§c§lTvůj útok byl vyblokován!");
                }

                if(ThreadLocalRandom.current().nextDouble() < 0.2) {
                    p.getWorld().strikeLightningEffect(p.getLocation());
                    p.sendMessage("§c§lByl si zasažen bleskem!");

                }
                if(ThreadLocalRandom.current().nextDouble() < 0.1) {
                    p.setVelocity(new Vector(0,2,0));
                    p.sendMessage("§c§lOživnuli monstra");
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);


                    p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP,15,10);

                }
                if(ThreadLocalRandom.current().nextDouble() < 0.1) {
                    p.setVelocity(new Vector(0,0,0));
                    p.sendMessage("§4§lZlé ryby oživli");
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SILVERFISH);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SILVERFISH);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SILVERFISH);
                    p.getWorld().spawnEntity(p.getLocation(), EntityType.SILVERFISH);
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_DESTROY,15,10);

                }






            }
        }

    }
}
