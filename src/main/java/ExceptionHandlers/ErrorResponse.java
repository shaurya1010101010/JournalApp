package ExceptionHandlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    
    private Date timestamp = new Date();
    private int status;
    private String error;
    private String message;
    private String errorCode;

    public ErrorResponse(int value, String internalServerError, String message, String err500) {
    }
}
