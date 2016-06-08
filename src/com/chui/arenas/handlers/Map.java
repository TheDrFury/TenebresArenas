package com.chui.arenas.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private static Map activeMap = null;
    private static List<Map> allMaps = new ArrayList<Map>();

    private List<String> spawns;

    private String fileName, mapName;

    public Map(String mapName, String fileName, List<String> spawns) {
        this.spawns = spawns;
        this.fileName = fileName;
        this.mapName = mapName;

        allMaps.add(this);
    }

    public static List<Map> getAllMaps() {
        return allMaps;
    }

    public static void setActiveMap(Map map) {
        activeMap = map;
    }

    public static Map getActiveMap() {
        return activeMap;
    }

    public World getWorld() {
        return Bukkit.getWorld(fileName);
    }

    public Location getSpawn(int teamId) {
        for (Team t : Team.getAllTeams()) {
            int tId = t.getId();
            if (tId != teamId)
                continue;
            int actualTeamId = tId;
            if (spawns.get(tId) == null)
                actualTeamId = 0;

            String teamSpawn = spawns.get(actualTeamId);
            String[] teamSpawns = teamSpawn.split(",");
            return new Location(getWorld(), Integer.valueOf(teamSpawns[0])+ 0.5, Integer.valueOf(teamSpawns[0])+ 0.5, Integer.valueOf(teamSpawns[0])+ 0.5);
        }
        return null;
    }
}
