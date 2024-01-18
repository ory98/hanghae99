package kr.hanghae.deploy.service.order;

import kr.hanghae.deploy.domain.order.OrderedEvent;
import kr.hanghae.deploy.domain.orderproduct.OrderProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderedEventListener {
    @Async
    @EventListener
    public void sendDataOut(OrderedEvent event) throws RuntimeException {
        log.info(String.format("외부로 데이터 전송 성공 [주문번호 : %s]", event.getOrderId()));
    }
}
