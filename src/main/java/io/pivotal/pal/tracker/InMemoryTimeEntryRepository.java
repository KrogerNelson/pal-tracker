package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private long idGenerator = 0;
    private Map<Long, TimeEntry> timeEntryMap = new HashMap<>();

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry newTimeEntry = new TimeEntry(++idGenerator,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours());
        timeEntryMap.put(newTimeEntry.getId(), newTimeEntry);
        return newTimeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {

        return timeEntryMap.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return timeEntryMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {
        if (timeEntryMap.get(timeEntryId) == null) {
            return null;
        }
        TimeEntry updatedTimeEntry = new TimeEntry(timeEntryId,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours());
        timeEntryMap.put(updatedTimeEntry.getId(), updatedTimeEntry);
        return updatedTimeEntry;
    }

    @Override
    public void delete(long timeEntryId) {
        timeEntryMap.remove(timeEntryId);
    }
}
