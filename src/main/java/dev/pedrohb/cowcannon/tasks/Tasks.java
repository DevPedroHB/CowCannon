package dev.pedrohb.cowcannon.tasks;

import java.util.ArrayList;
import java.util.List;

import dev.pedrohb.cowcannon.CowCannon;
import dev.pedrohb.cowcannon.models.Scheduler;

public class Tasks {

  private static final List<Scheduler.Task> tasks = new ArrayList<>();

  public static void registerTasks() {
    tasks.add(Scheduler.runTimer(ButterflyTask.getInstance(), 0, 1));
    tasks.add(Scheduler.runTimer(LaserPointerTask.getInstance(), 0, 1));
    tasks.add(Scheduler.runTimer(TablistTask.getInstance(), 0, 20));
    tasks.add(Scheduler.runTimer(ItemPickupTask.getInstance(), 0, 2));

    if (!Scheduler.isFolia()) {
      tasks.add(Scheduler.runTimer(ScoreboardTask.getInstance(), 0, 20));
    }
  }

  public static void unregisterTasks(CowCannon cowCannon) {
    for (Scheduler.Task task : tasks) {
      if (task != null) {
        task.cancel();
      }
    }

    tasks.clear();
  }
}
