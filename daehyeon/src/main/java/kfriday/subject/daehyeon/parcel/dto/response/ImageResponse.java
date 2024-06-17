package kfriday.subject.daehyeon.parcel.dto.response;

import kfriday.subject.daehyeon.parcel.domain.Image;

public record ImageResponse(
        String filename,
        String type
) {
    public static ImageResponse from(Image image) {
        return new ImageResponse(image.getFilename(), image.getType());
    }
}
