package kfriday.subject.daehyeon.parcel.exception;

import kfriday.subject.daehyeon.common.error.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum ParcelErrorCode implements ErrorCode {
    PARCEL_NOT_FOUND("1001", HttpStatus.NOT_FOUND, "물류가 존재하지 않습니다.");

    private String code;
    private HttpStatus statue;
    private String message;

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public HttpStatus getStatus() {
        return null;
    }
}
