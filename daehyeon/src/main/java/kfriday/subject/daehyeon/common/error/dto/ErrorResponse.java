package kfriday.subject.daehyeon.common.error.dto;

import kfriday.subject.daehyeon.common.error.ErrorCode;

public record ErrorResponse(String code, String message) {

    public static ErrorResponse from(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
    }
}
