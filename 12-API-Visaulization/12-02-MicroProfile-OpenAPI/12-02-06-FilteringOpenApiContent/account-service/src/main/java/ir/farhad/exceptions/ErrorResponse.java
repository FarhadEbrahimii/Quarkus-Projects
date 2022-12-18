package ir.farhad.exceptions;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class ErrorResponse {
    @Schema(required = true, example ="javax.ws.rs.WebApplicationException")
    private String exceptionType;
    @Schema(required = true, example = "400", type = SchemaType.INTEGER)
    private Integer code;
    private String error;

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
