package osonsot.entity.poster;

import lombok.*;
import osonsot.entity.Auditable;
import osonsot.entity.auth.AuthUser;
import osonsot.mainbot.enums.District;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(access = AccessLevel.PUBLIC)
public class Poster extends Auditable {
    @Column(columnDefinition = "text")
    private String description;

    @Column(length = 86)
    private String title;

    private Long price;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "added_date")
    private LocalDateTime addedDate;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    private Boolean sold;
    private Boolean accepted;
    private Boolean rejected;
    @Column(name = "telegraph_url")
    private String telegraphUrl;

    @Enumerated(EnumType.STRING)
    private District location;

    @Column(name = "accepted_by")
    private String acceptedBy;

    @OneToMany(mappedBy = "poster", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> images;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AuthUser owner;

    @Builder(builderMethodName = "parentBuilder")
    public Poster(Long id, boolean deleted, LocalDateTime createdAt, String description, String title, Long price, Long viewCount, LocalDateTime addedDate, LocalDateTime deleteDate, Boolean sold, Boolean accepted, Boolean rejected, String telegraphUrl, District location, String acceptedBy, List<Image> images, Category category, AuthUser owner) {
        super(id, deleted, createdAt);
        this.description = description;
        this.title = title;
        this.price = price;
        this.viewCount = viewCount;
        this.addedDate = addedDate;
        this.deleteDate = deleteDate;
        this.sold = sold;
        this.accepted = accepted;
        this.rejected = rejected;
        this.telegraphUrl = telegraphUrl;
        this.location = location;
        this.acceptedBy = acceptedBy;
        this.images = images;
        this.category = category;
        this.owner = owner;
    }
}
