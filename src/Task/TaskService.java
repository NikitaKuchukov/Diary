package Task;

import exceptions.TaskNotFoundException;

import java.util.*;

public class TaskService {
    private final Map<Integer, Task> taskMap = new HashMap<>();

    public void add(Task t) {
        taskMap.put(t.getId(), t);
    }

    public Task remove(int a) throws TaskNotFoundException {
        if (taskMap.get(a) == null) throw new TaskNotFoundException();
        taskMap.keySet().removeIf(key -> key == a);
        return taskMap.get(a);
    }

    public Collection<Task> getAllByDate(Calendar calendar) {
        List<Task> taskList = new ArrayList<>();
        for (Map.Entry<Integer, Task> mapTask : taskMap.entrySet()) {
            if (mapTask.getValue().appearsIn(calendar)) {
                taskList.add(mapTask.getValue());
            }
        }
        return taskList;
    }
}

