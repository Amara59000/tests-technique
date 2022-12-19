package adeo.leroymerlin.cdp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAllBy();
    }

    public void delete(Long id) {
        eventRepository.delete(id);
    }

    /**
     * Filter events by member name
     * @param query
     * @return
     */
    public List<Event> getFilteredEvents(String query) {
        // Filter the events list in pure JAVA here

        if(StringUtils.isEmpty(query)) {
            return Collections.EMPTY_LIST;
        }

        final List<Event> result = eventRepository.findAllBy();

        if(CollectionUtils.isEmpty(result)) {
            return  Collections.EMPTY_LIST;
        }

        //Get only event that have at least one band containing at least one member with a that contains the param
        final List<Event> filteredResult =  result.stream()
                .filter(e -> Optional.ofNullable(e.getBands()).orElseGet(Collections::emptySet).stream()
                        .anyMatch(b ->  Optional.ofNullable(b.getMembers()).orElseGet(Collections::emptySet).stream().
                                anyMatch(m -> m.getName().contains(query))))
                .collect(Collectors.toList());

        //Add child informations on the result
        return addChildInformationsOnFilteredEvents(filteredResult);

    }

    /**
     * Filtered events using bdd query
     * @param query
     * @return
     */
    public List<Event> getFilteredEventsWithQuery(String query) {
        if(StringUtils.isEmpty(query)) {
            return Collections.EMPTY_LIST;
        }
        return eventRepository.searchByNameOfMember(query);

    }

    /**
     * Add child informations on filtered events
     * @param eventList
     * @return
     */
    private List<Event> addChildInformationsOnFilteredEvents (final List<Event> eventList) {
        if(!CollectionUtils.isEmpty(eventList)) {
            eventList.forEach(e -> e.setTitle(e.getTitle() + "[" + e.getBands().size() + "]"));
            eventList.forEach(e -> e.getBands().forEach(b -> b.setName(b.getName() + "[" + b.getMembers().size() + "]")));
        }

        return eventList;
    }



    public Event updateEvent(Event event) {
        eventRepository.save(event);
        return event;
    }
}
