package kfriday.subject.daehyeon.parcel.dto.request;

import java.util.List;

public record ParcelUpdateRequest(
        Long trackingNo,
        List<ImageUpdateRequest> images
) {
}
