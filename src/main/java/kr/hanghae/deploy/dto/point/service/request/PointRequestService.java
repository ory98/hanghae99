package kr.hanghae.deploy.dto.point.service.request;

import lombok.Getter;

@Getter
public class PointRequestService {

    private String username;

    private Long point;

    public PointRequestService(String username, Long point) {
        this.username = username;
        this.point = point;
    }

    public String getUsername() {
       return username;
    }

    public Long getPoint() {
        return point;
    }
}
