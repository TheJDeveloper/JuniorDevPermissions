import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JuniorDevPermissions extends JavaPlugin {

    private FileConfiguration config;
    private String defaultPerm;

    private List<String> permNames;
    private List<Permission> permissions;
    private HashMap<String, Permission> playerPerms;



    @Override
    public void onEnable() {

        config = getConfig();
        initPerms();
        new PermissionListener(this);
        super.onEnable();
    }


    /**
     * Initializes the permission settings for the plugin
     */
    private void initPerms(){

        String permNamePath = "permissions.names";
        permNames = config.getStringList(permNamePath);
        permissions = new ArrayList<Permission>();
        defaultPerm = config.getString("permissions.default");

        //Sets the permissions from the config
        for(String s: permNames){
            Permission permission = new Permission(config, s);
            permissions.add(permission);
        }

        //Sets the player's permissions from the config
        playerPerms = new HashMap<String, Permission>();
        List<String> players = config.getStringList("players.players");
        for(String s: players){
            String permission = config.getString("players." + s);
            playerPerms.put(s, Permission.getPerm(permission));
        }

        //Checks if the default exists
        if(playerPerms.get(defaultPerm) == null){
            defaultPerm = "default";
        }

    }

    /**
     * Saves the permission level of all players
     */
    private void saveInfo(){
        String playerPermPath = "players.";
        for(String s: playerPerms.keySet()) {
            config.set(playerPermPath + s, playerPerms.get(s).getName());
        }
        config.set("permissions.default", defaultPerm);

        saveConfig();
    }

    /**
     * Updates the permission level of a specified player
     *
     * @param name Player's name to change permission for
     * @param permission Permission to update to
     */
    public void changePermission(String name, String permission){
        playerPerms.put(name, Permission.getPerm(permission));
        saveInfo();
    }

    /**
     * Returns the permission of a player or default if it does not exist
     *
     * @param name Player name to get permission level of
     * @return The permission level of the player
     */
    public Permission getPermission(String name){
        Permission playerPerm = playerPerms.get(name);
        if(playerPerm == null){
            playerPerm = Permission.getDefault();
        }
        return playerPerm;
    }

    /**
     * @param name Name of the player
     * @return The current permission level of the player
     */
    public boolean assignedPerm(String name){
        return playerPerms.containsKey(name);
    }

    /**
     * @return The default permission level
     */
    public String getDefaultPerm(){return defaultPerm;}
}
