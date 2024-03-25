package eu.epitech.resources;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

class ExceptionMappers {
    @ServerExceptionMapper
    public RestResponse<String> mapException(NotFoundException x) {
        return RestResponse.status(Response.Status.NOT_FOUND, x.getMessage());
    }

    @ServerExceptionMapper
    public RestResponse<String> mapException(BadRequestException x) {
        return RestResponse.status(Response.Status.BAD_REQUEST, x.getMessage());
    }
}