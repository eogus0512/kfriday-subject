package kfriday.subject.daehyeon.parcel.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long trackingNo;

    @OneToMany(mappedBy = "parcel")
    List<Image> images = new ArrayList<>();

    @Builder
    public Parcel(Long trackingNo, List<Image> images) {
        this.trackingNo = trackingNo;
        this.images = images;
    }

    public void update(Long trackingNo, List<Image> images) {
        this.trackingNo = trackingNo;
        this.images = images;
    }
}
