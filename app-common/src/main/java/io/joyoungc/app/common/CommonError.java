package io.joyoungc.app.common;

import org.springframework.http.HttpStatus;

/***
 * CommonError
 *
 * Created by Aiden Jeong on 2020.07.15
 */
public enum CommonError implements ErrorCode {

    COMMON_INTERNAL_SERVER_ERROR("APP1N0001", HttpStatus.INTERNAL_SERVER_ERROR),
    COMMON_BAD_REQUEST("APP1N0002", HttpStatus.BAD_REQUEST),
    COMMON_UNAUTHORIZED("APP1N0003", HttpStatus.UNAUTHORIZED),
    COMMON_NOT_FOUND("APP1N0004", HttpStatus.NOT_FOUND),
    COMMON_DATA_NOT_FOUND("APP1N0005", HttpStatus.NOT_FOUND),
    COMMON_DUPLICATED_REQUEST("APP1N0006", HttpStatus.BAD_REQUEST),
    COMMON_FILE_PROCESS_FAILED("APP1N0007", HttpStatus.BAD_REQUEST),
    COMMON_UNKNOWN_ERROR("APP1N0010", HttpStatus.INTERNAL_SERVER_ERROR),
    COMMON_ETC("APP1N9999", HttpStatus.INTERNAL_SERVER_ERROR);

    private HttpStatus httpStatus;
    private String code;

    CommonError(String code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public int getStatus() {
        return this.httpStatus.value();
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

}
