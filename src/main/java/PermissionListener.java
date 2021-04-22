import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PermissionListener implements Listener {

    private JuniorDevPermissions plugin;

    public PermissionListener(JuniorDevPermissions plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }



    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();
        String name = player.getName();

        if(plugin.assignedPerm(name)){
            return;
        }

        plugin.changePermission(name, plugin.getDefaultPerm());

    }


}
