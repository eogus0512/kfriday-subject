package kfriday.subject.daehyeon.parcel.service;

import kfriday.subject.daehyeon.common.error.exception.BusinessException;
import kfriday.subject.daehyeon.parcel.domain.Image;
import kfriday.subject.daehyeon.parcel.domain.ImageRepository;
import kfriday.subject.daehyeon.parcel.domain.Parcel;
import kfriday.subject.daehyeon.parcel.domain.ParcelRepository;
import kfriday.subject.daehyeon.parcel.dto.request.ParcelCreateRequest;
import kfriday.subject.daehyeon.parcel.dto.request.ParcelUpdateRequest;
import kfriday.subject.daehyeon.parcel.dto.response.ImageResponse;
import kfriday.subject.daehyeon.parcel.dto.response.ParcelResponse;
import kfriday.subject.daehyeon.parcel.exception.ParcelErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ParcelService {
    private final ImageRepository imageRepository;
    private final ParcelRepository parcelRepository;

    public Long create(ParcelCreateRequest parcelCreateRequest) {
        List<Image> images = parcelCreateRequest.images().stream().
                map(imageCreateRequest -> Image.builder()
                        .filename(imageCreateRequest.filename())
                        .type(imageCreateRequest.type())
                        .build())
                .toList();

        Parcel parcel = Parcel.builder()
                .trackingNo(parcelCreateRequest.trackingNo())
                .images(images)
                .build();

        parcelRepository.save(parcel);

        for (Image image : images) {
            image.setParcel(parcel);
            imageRepository.save(image);
        }

        return parcel.getId();
    }

    public List<ParcelResponse> getParcels() {
        List<Parcel> parcels = parcelRepository.findAll();

        return parcels.stream()
                .map(parcel -> {
                    List<ImageResponse> imageResponses = parcel.getImages().stream()
                            .map(ImageResponse::from)
                            .toList();
                    return ParcelResponse.of(parcel.getTrackingNo(), imageResponses);
                })
                .toList();
    }
    public void update(Long id, ParcelUpdateRequest parcelUpdateRequest) {
        Parcel parcel = findParcel(id);

        List<Image> newImages = parcelUpdateRequest.images().stream()
                .map(imageUpdateRequest -> Image.builder()
                        .filename(imageUpdateRequest.filename())
                        .type(imageUpdateRequest.type())
                        .build())
                .toList();

        parcel.update(parcelUpdateRequest.trackingNo(), newImages);
    }

    public void delete(Long id) {
        Parcel parcel = findParcel(id);

        imageRepository.deleteAllByParcel(parcel);
        parcelRepository.deleteById(id);
    }

    private Parcel findParcel(Long id) {
        return parcelRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ParcelErrorCode.PARCEL_NOT_FOUND));
    }
}
