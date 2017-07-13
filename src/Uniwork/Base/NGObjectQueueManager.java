package Uniwork.Base;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGObjectQueueManager extends NGObject {

    protected CopyOnWriteArrayList<NGObjectQueue> FQueues;

    public NGObjectQueueManager() {
        super();
        FQueues = new CopyOnWriteArrayList<NGObjectQueue>();
    }

    public Integer getSize() {
        return FQueues.size();
    }

    public NGObjectQueue getQueue(String aName) {
        for (NGObjectQueue queue : FQueues) {
            if (queue.getName().equals(aName)) {
                return queue;
            }
        }
        return null;
    }

    public NGObjectQueue addQueue(String aName) {
        NGObjectQueue queue = getQueue(aName);
        if (queue == null) {
            queue = new NGObjectQueue(aName);
            FQueues.add(queue);
        }
        return queue;
    }

    public void removeQueue(String aName) {
        NGObjectQueue queue = getQueue(aName);
        if (queue != null) {
            FQueues.remove(queue);
        }
    }

    public void enterQueue(String aName, Object aObject) {
        enterQueue(aName, "", aObject);
    }

    public void enterQueue(String aName, String aObjectName, Object aObject) {
        NGObjectQueue queue = getQueue(aName);
        queue.enter(aObjectName, aObject);
    }

    public Object leaveQueue(String aName) {
        return leaveQueue(aName, "");
    }

    public Object leaveQueue(String aName, String aObjectName) {
        NGObjectQueue queue = getQueue(aName);
        if (aObjectName.length() == 0) {
            return queue.leave();
        }
        else {
            while (!queue.isEmpty()) {
                NGObjectQueueItem item = queue.leaveItem();
                if (item.getName().equals(aObjectName)) {
                    return item.getObject();
                }
            }
        }
        return null;
    }

    public Object front(String aName) {
        NGObjectQueue queue = getQueue(aName);
        return queue.front();
    }

    public Object back(String aName) {
        NGObjectQueue queue = getQueue(aName);
        return queue.back();
    }

    public Boolean isQueueEmpty(String aName) {
        NGObjectQueue queue = getQueue(aName);
        return queue.isEmpty();
    }

    public Integer getQueueSize(String aName) {
        NGObjectQueue queue = getQueue(aName);
        return queue.getSize();
    }

}
