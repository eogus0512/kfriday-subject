package kfriday.subject.daehyeon.parcel.dto.request;

import java.util.List;

public record ParcelCreateRequest(
        Long trackingNo,
        List<ImageCreateRequest> images
) {
}
