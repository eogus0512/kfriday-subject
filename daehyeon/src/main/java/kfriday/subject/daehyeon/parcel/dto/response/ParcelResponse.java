package kfriday.subject.daehyeon.parcel.dto.response;

import java.util.List;

public record ParcelResponse(
        Long trackingNo,
        List<ImageResponse> images
) {
    public static ParcelResponse of(Long trackingNo, List<ImageResponse> images) {
        return new ParcelResponse(trackingNo, images);
    }
}
