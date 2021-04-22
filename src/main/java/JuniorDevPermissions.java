import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class JuniorDevPermissions extends JavaPlugin {

    private final String permNamePath = "permissions.names";


    private FileConfiguration config;

    private List<String> permNames;


    @Override
    public void onEnable() {

        config = getConfig();
        initPerms();

        super.onEnable();
    }

    private void initPerms(){

        permNames = config.getStringList(permNamePath);




    }





}
