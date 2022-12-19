import adeo.leroymerlin.cdp.EventRepository;
import adeo.leroymerlin.cdp.EventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Before
    public void setUp() {
        Mockito.when(eventRepository.findAllBy()).thenReturn(new ArrayList<>());
    }

    @Test
    public void getFilteredEventsWithNullValue() {
        Assert.notNull(eventService.getFilteredEvents(null));
    }

    @Test
    public void getFilteredEventsWithEmptyResult() {
        Assert.notNull(eventService.getFilteredEvents("Wa"));
    }

}
