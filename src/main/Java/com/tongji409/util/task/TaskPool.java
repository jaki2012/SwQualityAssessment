package com.tongji409.util.task;

import com.tongji409.domain.Task;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 任务池
 *
 * Project: SwQualityAssesment
 * Package: com.tongji409.util.task
 * Author:  Novemser
 * 2016/12/15
 */

public class TaskPool {
    private int corePoolSize;

    private int maximumPoolSize;

    private ThreadPoolExecutor taskExecutor;

    private LinkedBlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();


    public Integer getCurrentRunning() {
        return taskQueue.size();
    }

    public boolean enTask(Task task) {
        return taskQueue.add(task);
    }

    public Task deTask(Task task) {
        return taskQueue.poll();
    }

    public Task peekTask() {
        return taskQueue.peek();
    }

    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }

    public boolean containsTask(Task task) {
        return taskQueue.contains(task);
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public ThreadPoolExecutor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(ThreadPoolExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
}
