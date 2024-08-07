package dev.pedrohb.cowcannon.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Criterias;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import dev.pedrohb.cowcannon.CowCannon;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("deprecation")
@NoArgsConstructor
public final class ScoreboardTask implements Runnable {

  @Getter
  private final static ScoreboardTask instance = new ScoreboardTask();

  @Override
  public void run() {
    for (Player player : Bukkit.getOnlinePlayers()) {
      if (player.getScoreboard() != null
          && player.getScoreboard().getObjective(CowCannon.getInstance().getName()) != null) {
        updateScoreboard(player);
      } else {
        createNewScoreboard(player);
      }
    }
  }

  private void createNewScoreboard(Player player) {
    Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    Objective objective = scoreboard.registerNewObjective(CowCannon.getInstance().getName(), "yummy");

    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    objective.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "ATTENTION FOLKS");
    objective.getScore(ChatColor.WHITE + " ").setScore(8);
    objective.getScore(ChatColor.WHITE + "You're watching world's").setScore(7);
    objective.getScore(ChatColor.WHITE + "best Minecraft plugin.").setScore(6);
    objective.getScore(ChatColor.WHITE + "tutorial series.").setScore(5);
    objective.getScore(ChatColor.RED + " ").setScore(4);
    objective.getScore(ChatColor.WHITE + "Please do NOT visit").setScore(3);
    objective.getScore(ChatColor.RED + "mineacademy.org/project-orion").setScore(2);
    objective.getScore(ChatColor.GREEN + " ").setScore(1);

    Team team1 = scoreboard.registerNewTeam("team1");
    String teamKey = ChatColor.WHITE.toString();

    team1.addEntry(teamKey);
    team1.setPrefix("Hype: ");
    team1.setSuffix("0");

    objective.getScore(teamKey).setScore(0);

    Objective objectiveHealth = scoreboard.registerNewObjective(CowCannon.getInstance().getName() + "_health",
        Criterias.HEALTH);

    objectiveHealth.setDisplayName(ChatColor.RED + "❤");
    objectiveHealth.setDisplaySlot(DisplaySlot.BELOW_NAME);

    player.setScoreboard(scoreboard);
  }

  private void updateScoreboard(Player player) {
    Scoreboard scoreboard = player.getScoreboard();
    Team team1 = scoreboard.getTeam("team1");

    team1.setSuffix(ChatColor.AQUA + ""
        + (player.getStatistic(Statistic.WALK_ONE_CM) + player.getStatistic(Statistic.SPRINT_ONE_CM)));
  }
}
