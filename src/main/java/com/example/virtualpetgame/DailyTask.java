package com.example.virtualpetgame;

public class DailyTask {
    private TaskType taskType;
    private int reward;
    private boolean completed;

    DailyTask(TaskType taskType, int reward) {

        this.taskType = taskType;
        this.reward = reward;
        completed = false;


    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getReward() {
        return reward;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void complete(User user) {
        if (!completed) {
            completed = true;
            user.earnMoney(reward);
            System.out.println("Daily Task Completed! You earned $" + reward);
        } else {
            System.out.println("You already completed today’s task.");
        }
    }

}
