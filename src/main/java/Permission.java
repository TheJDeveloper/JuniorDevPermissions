import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class Permission {

    private static final String path = "permissions.levels.";
    private static final HashMap<String, Permission> permissions = new HashMap<String, Permission>();

    /**
     * @return The default permission level
     */
    public static Permission getDefault(){return permissions.get("default");}
    public static Permission getPerm(String name){
        Permission toReturn = permissions.get(name);
        if(toReturn == null){
            toReturn = getDefault();
        }
        return toReturn;
    }

    private final String name;
    private final int level;
    private final String tag;


    /**
     * Constructs a new Permission object
     *
     * @param config The config file of the plugin
     * @param name The name of the permission to construct
     */
    public Permission(FileConfiguration config, String name){
        this.name = name;
        level = config.getInt(path + name + "level");
        tag = config.getString(path + name + "tags");

        permissions.put(name, this);
    }

    /**
     * Gets the numeric level of the permission
     *
     * @return The permission level
     */
    public int getLevel(){return level;}

    /**
     * Gets the tag of the permission
     *
     * @return The tags of the permission
     */
    public String getTag(){return tag;}

    /**
     * Gets the name of the permission
     *
     * @return The name of the permission
     */
    public String getName(){return name;}
}
