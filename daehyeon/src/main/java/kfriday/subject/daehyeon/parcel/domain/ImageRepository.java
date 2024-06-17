package kfriday.subject.daehyeon.parcel.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Image i WHERE i.parcel = :parcel")
    void deleteAllByParcel(Parcel parcel);
}
