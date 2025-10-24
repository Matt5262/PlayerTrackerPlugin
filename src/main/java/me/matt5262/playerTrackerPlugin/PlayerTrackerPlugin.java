package me.matt5262.playerTrackerPlugin;

import me.matt5262.playerTrackerPlugin.commands.AvgPlayersCommand;
import me.matt5262.playerTrackerPlugin.listeners.PlayerTrackerLisener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

public final class PlayerTrackerPlugin extends JavaPlugin {


    private File logFile;
    private Connection connection;
    public boolean isShuttingDown = false;
    // field


    @Override
    public void onEnable() {

        PlayerTrackerLisener listenerInstance = new PlayerTrackerLisener();
        getServer().getPluginManager().registerEvents(listenerInstance, this);
        Objects.requireNonNull(getCommand("avgplayers")).setExecutor(new AvgPlayersCommand());
        // register stuff


        getLogger().info("PlayerTrackerPlugin enabled!");
        // wow, it works
    }

    @Override
    public void onDisable() {

        isShuttingDown = true;

    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                File databaseFile1 = new File(getDataFolder(), "database.db");
                // continue here...
            }
        }
    }

    private void setupDatabase(){

        try {
            File databaseFile2 = new File(getDataFolder(), "database.db");
            if (!databaseFile2.getParentFile().exists() && !databaseFile2.getParentFile().mkdirs()) {
                getLogger().severe("Failed to create plugin folder for database: " + databaseFile2.getAbsolutePath());
            }

            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseFile2.getAbsolutePath());

        }

    }



}
