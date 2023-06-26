package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        if (memory.containsKey(model.getId())) {
            return false;
        }
        memory.put(model.getId(), model);
        return true;
    }

    /**
     * В кеше же нужно перед обновлением данных проверить поле version.
     * Если version у модели и в кеше одинаковы, то можно обновить.
     * Если нет, то выбросить OptimisticException.
     * Перед обновлением данных необходимо проверять текущую версию и ту что обновляем
     * и увеличивать на единицу каждый раз, когда произошло обновление. Если версии не равны -
     * кидать исключение.
     */
    public boolean update(Base model) {
        model.getVersion();
        memory.get(model);

        /* TODO impl */
        return false;
    }

    public void delete(Base model) {
        /* TODO impl */
    }
}