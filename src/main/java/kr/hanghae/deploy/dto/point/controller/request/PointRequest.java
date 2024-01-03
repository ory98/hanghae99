package kr.hanghae.deploy.dto.point.controller.request;

import kr.hanghae.deploy.dto.point.service.request.PointRequestService;
import lombok.Getter;

@Getter
public class PointRequest {

    private String username;
    private Long point;

    public PointRequestService toService() {
        return new PointRequestService( this.username, this.point);
    }
}
