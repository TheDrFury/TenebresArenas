package com.chui.arenas.handlers;

import com.chui.arenas.utils.ChatUtilities;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Team {
    private static List<Team> allTeams = new ArrayList<Team>();
    private static List<Team> activeTeams = new ArrayList<Team>();

    private static List<String> members = new ArrayList<String>();

    private static HashMap<String, Team> playerTeams = new HashMap<String, Team>();

    private String teamName;

    private Team(String teamName) {
        this.teamName = teamName.trim();
        activeTeams.add(this);
        allTeams.add(this);
    }

    public Team(String... teamNames) {
        for (String s : teamNames)
            new Team(s);
    }

    public int getId() {
        for (int i = 0; i<allTeams.size(); i++)
            if (this == allTeams.get(i))
                return i;
        return -1;
    }

    public String getName() {
        return teamName;
    }

    public Location getSpawn() {
        return Map.getActiveMap().getSpawn(getId());
    }

    public void add(Player player) {
        playerTeams.put(player.getName(), this);
        members.add(player.getName());
    }

    public boolean remove(Player player) {
        if(hasTeam(player)) {
            playerTeams.remove(player.getName());
            members.remove(player.getName());

            if (members.isEmpty()) {
                activeTeams.remove(this);
                ChatUtilities.broadcast(getName() + "Team has been killed");
            }
            if (activeTeams.size() == 1) {
                Game.stop(activeTeams.get(0));
            }
            return true;
        }
        return false;
    }

    public static boolean hasTeam(Player player) {
        return playerTeams.containsKey(player.getName());
    }

    public static Team getTeam(Player player) {
        if (!hasTeam(player))
            return null;
        return playerTeams.get(player.getName());
    }

    public static Team getTeam(String name) {
        for (Team t : allTeams){
            if (t.teamName.equalsIgnoreCase(name))
                return t;
        }
        return null;

    }

    public static List<Team> getAllTeams() {
        return allTeams;
    }
}
