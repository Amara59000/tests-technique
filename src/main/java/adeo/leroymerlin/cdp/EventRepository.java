package adeo.leroymerlin.cdp;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = false)
public interface EventRepository extends CrudRepository<Event, Long> {
    @Query("select e From Event as e join e.bands as b join b.members as m where m.name like %:query%")
    List<Event> searchByNameOfMember(@Param("query")String query);

    void delete(Long eventId);

    List<Event> findAllBy();

}
