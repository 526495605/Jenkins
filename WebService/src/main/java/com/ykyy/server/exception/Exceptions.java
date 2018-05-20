package com.ykyy.server.exception;


import javax.ws.rs.core.Response;

/**
 * Created by my on 2015/3/25.
 */
public class Exceptions {

    public static class ApiException extends RuntimeException{

        private int statsCode;
        private String message;

        public ApiException(){
            super("服务器内部错误");
            this.statsCode = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        }

        public ApiException(int statusCode, String message) {
            super(message);
            this.statsCode = statusCode;
            this.message = message;
        }

        public ApiException(Response.Status status, String message) {
            super(message);
            this.statsCode = status.getStatusCode();
        }

        public int getStatsCode() {
            return statsCode;
        }

        @Override
        public String getMessage()
        {
            return message;
        }
    }


    /**
     * 400
     */
    public static class DataValidationFailedException extends ApiException {
        public DataValidationFailedException(String message) {
            super(400, message);
            //Logging.info(message);
        }
    }

    public static DataValidationFailedException get400Exception(String message){
        return new DataValidationFailedException(message);
    }

    /**
     * 401
     */
    public static class UnauthorizedException extends ApiException {
    	public UnauthorizedException(String message) {
    		super(401, message);
    	}
    }

    public static UnauthorizedException get401Exception(String message){
        return new UnauthorizedException(message);
    }


    /**
     * 403
     */
    public static class ForbiddenException extends ApiException {
    	public ForbiddenException(String message) {
    		super(403, message);
    	}
    }

    public static ForbiddenException get403Exception(String message){
        return new ForbiddenException(message);
    }


    /**
     * 404
     */
    public static class DataNotFoundException extends ApiException {
        public DataNotFoundException(String message) {
            super(404, message);
        }
    }

    public static DataNotFoundException get404Exception(String message){
        return new DataNotFoundException(message);
    }

    /**
     * 409
     */
    public  static class DataConflictedException extends ApiException {
        public DataConflictedException(String message) {
            super(409, message);
        }
    }

    public static DataConflictedException get409Exception(String message){
        return new DataConflictedException(message);
    }

    /**
     * 412 token失效
     **/
    public  static class TokenException extends ApiException {
        public TokenException(String message) {
            super(412, message);
        }
    }

    public static TokenException get412Exception(String message){
        return new TokenException(message);
    }

}
