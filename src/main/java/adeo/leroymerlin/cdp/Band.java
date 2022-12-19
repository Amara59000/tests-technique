package adeo.leroymerlin.cdp;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;


    @ManyToMany(fetch = FetchType.EAGER)
    @OrderBy(value = "id ASC")
    @JoinTable(name = "EVENT_BANDS",
            joinColumns = @JoinColumn(name="BANDS_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "EVENT_ID", referencedColumnName = "ID"))
    private Set<Event> event;


    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "band")
    private Set<Member> members;

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
