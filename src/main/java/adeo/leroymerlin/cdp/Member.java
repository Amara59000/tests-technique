package adeo.leroymerlin.cdp;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    String name;

    @ManyToOne
    @JoinTable(name = "BAND_MEMBERS",
            joinColumns = {@JoinColumn(name = "MEMBERS_ID")},
            inverseJoinColumns = {@JoinColumn(name = "BAND_ID")}
    )
    private Band band;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
