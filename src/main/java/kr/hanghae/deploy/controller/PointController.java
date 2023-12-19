package kr.hanghae.deploy.controller;

import kr.hanghae.deploy.dto.ApiResponse;
import kr.hanghae.deploy.dto.point.controller.request.PointRequest;
import kr.hanghae.deploy.dto.point.service.response.PointResponse;
import kr.hanghae.deploy.service.point.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    @PutMapping("/point")
    public ApiResponse<PointResponse> updatepoint(@RequestBody PointRequest request) {
        return ApiResponse.ok(pointService.updatePoint(request.toService()));
    }

//    @GetMapping("/point/info")
//    public ApiResponse<pointResponse> getpointInfo(@RequestBody )
}