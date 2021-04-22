import org.bukkit.configuration.file.FileConfiguration;

public class Permission {

    private static final String path = "permissions.levels.";

    private String name;
    private int level;
    private String tag;

    public Permission(FileConfiguration config, String name){
        this.name = name;
        level = config.getInt(path + name + "level");
        tag = config.getString(path + name + "tags");
    }

    public int getLevel(){return level;}
    public String getTag(){return tag;}

}
