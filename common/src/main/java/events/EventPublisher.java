package events;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventPublisher {
    private static final EventPublisher instance = new EventPublisher();

    private final Map<EGameEvent, List<IEventListener>> listenersMap = new ConcurrentHashMap<>();

    private EventPublisher() {}

    public static EventPublisher getInstance() {
        return instance;
    }

    public void subscribe(EGameEvent topic, IEventListener listener) {
        listenersMap.computeIfAbsent(topic, k -> new ArrayList<>()).add(listener);
    }

    public void unsubscribe(EGameEvent topic, IEventListener listener) {
        List<IEventListener> listeners = listenersMap.get(topic);
        if (listeners != null) {
            listeners.remove(listener);
            if (listeners.isEmpty()) {
                listenersMap.remove(topic);
            }
        }
    }

    public void publish(EGameEvent topic, GameEvent event) {
        List<IEventListener> listeners = listenersMap.get(topic);
        if (listeners != null) {
            for (IEventListener listener : listeners) {
                listener.onEvent(event);
            }
        }
    }
}
