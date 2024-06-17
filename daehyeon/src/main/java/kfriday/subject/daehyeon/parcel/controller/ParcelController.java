package kfriday.subject.daehyeon.parcel.controller;

import kfriday.subject.daehyeon.parcel.dto.request.ParcelCreateRequest;
import kfriday.subject.daehyeon.parcel.dto.request.ParcelUpdateRequest;
import kfriday.subject.daehyeon.parcel.dto.response.ParcelResponse;
import kfriday.subject.daehyeon.parcel.service.ParcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/parcels")
public class ParcelController {
    private final ParcelService parcelService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ParcelCreateRequest parcelCreateRequest) {
        Long parcelId = parcelService.create(parcelCreateRequest);

        return ResponseEntity.created(URI.create("/api/v1/parcels/" + parcelId))
                .build();
    }

    @GetMapping
    public ResponseEntity<List<ParcelResponse>> getParcels() {
        return ResponseEntity.ok(parcelService.getParcels());
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @RequestBody ParcelUpdateRequest parcelUpdateRequest) {
        parcelService.update(id, parcelUpdateRequest);

        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        parcelService.delete(id);

        return ResponseEntity.ok()
                .build();
    }
}
