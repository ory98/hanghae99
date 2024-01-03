package kr.hanghae.deploy.service.order;

import kr.hanghae.deploy.domain.order.OrderRepository;
import kr.hanghae.deploy.domain.orderproduct.OrderProductRepository;
import kr.hanghae.deploy.domain.point.PointRepository;
import kr.hanghae.deploy.domain.product.Product;
import kr.hanghae.deploy.domain.product.ProductRepository;
import kr.hanghae.deploy.domain.user.User;
import kr.hanghae.deploy.domain.user.UserRepository;
import kr.hanghae.deploy.dto.order.OrderProductInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderServiceConcurrencyTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;

    @AfterEach
    void tearDown() {
        orderProductRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
        pointRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("주문 생성 시 여러 주문이 들어왔을 경우, 재고가 없을 경우 주문이 취소된다.")
    void orderConcurrencyStockTest() throws InterruptedException {
        int latchCount = 100;
        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        ExecutorService executorService = Executors.newFixedThreadPool(10); // 스레드풀에 스래드 생성
        CountDownLatch countDownLatch = new CountDownLatch(latchCount);

        // 테스트 데이터 만들기
        userRepository.saveAndFlush(new User("user", 10000L));
        productRepository.saveAndFlush(new Product("1234", "짬뽕", 100L, 1L));

        IntStream.range(0, latchCount).forEach(action ->
                executorService.execute(() -> {
                    try {
                        orderService.saveOrder(
                                "user",
                                List.of(new OrderProductInfo("1234", 1L))
                        );
                        successCount.getAndIncrement();
                    } catch (RuntimeException e) {
                        assertThat(e.getMessage())
                                .isEqualTo("짬뽕 상품은 재고량 부족입니다. 현재 재고량 : 0개");
                        failCount.getAndIncrement();
                    } finally {
                        countDownLatch.countDown();
                    }
                })
        );

        countDownLatch.await();

        // 결과값 검증
        assertThat(successCount.get()).isEqualTo(1);
        assertThat(failCount.get()).isEqualTo(99);
        assertThat(orderRepository.findAll().size()).isEqualTo(1); // 주문이 한건 이루어져야함
        assertThat(productRepository.findByProductNumber("1234").getStock()).isEqualTo(0L); // 재고 검증
        assertThat(userRepository.findByUsername("user").get().getPoint()).isEqualTo(9900L); // 포인트 검증
    }

    @Test
    @DisplayName("주문 생성 시 여러 주문이 들어왔을 경우, 사용자의 포인트가 없을 경우 주문이 취소된다.")
    void orderConcurrencyPointTest() throws InterruptedException {
        int latchCount = 100;
        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        ExecutorService executorService = Executors.newFixedThreadPool(10); // 스레드풀에 스래드 생성
        CountDownLatch countDownLatch = new CountDownLatch(latchCount);

        // 테스트 데이터 만들기
        userRepository.saveAndFlush(new User("user", 100L));
        productRepository.saveAndFlush(new Product("1234", "짬뽕", 100L, 100L));

        IntStream.range(0, latchCount).forEach(action ->
                executorService.execute(() -> {
                    try {
                        orderService.saveOrder(
                                "user",
                                List.of(new OrderProductInfo("1234", 1L))
                        );
                        successCount.getAndIncrement();
                    } catch (RuntimeException e) {
                        assertThat(e.getMessage())
                                .isEqualTo("point가 부족합니다. 현재 point : 0 원 , 결제가격 : 100 원");
                        failCount.getAndIncrement();
                    } finally {
                        countDownLatch.countDown();
                    }
                })
        );

        countDownLatch.await();

        // 결과값 검증
        assertThat(successCount.get()).isEqualTo(1);
        assertThat(failCount.get()).isEqualTo(99);
        assertThat(orderRepository.findAll().size()).isEqualTo(1); // 주문이 한건 이루어져야함
        assertThat(productRepository.findByProductNumber("1234").getStock()).isEqualTo(99L); // 재고 검증
        assertThat(userRepository.findByUsername("user").get().getPoint()).isEqualTo(0L); // 포인트 검증
    }


    @Test
    @DisplayName("주문 생성 시 10개 주문이 들어왔을 경우, 주문이 10개 성공한다.")
    void orderConcurrencySuccessTest() throws InterruptedException {
        int latchCount = 10;
        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        ExecutorService executorService = Executors.newFixedThreadPool(10); // 스레드풀에 스래드 생성
        CountDownLatch countDownLatch = new CountDownLatch(latchCount);

        // 테스트 데이터 만들기
        userRepository.saveAndFlush(new User("user", 10000L));
        productRepository.saveAllAndFlush(List.of(
                new Product("0", "짬뽕1", 100L, 10L),
                new Product("1", "짬뽕2", 100L, 10L),
                new Product("2", "짬뽕3", 100L, 10L),
                new Product("3", "짬뽕4", 100L, 10L),
                new Product("4", "짬뽕5", 100L, 10L),
                new Product("5", "짬뽕6", 100L, 10L),
                new Product("6", "짬뽕7", 100L, 10L),
                new Product("7", "짬뽕8", 100L, 10L),
                new Product("8", "짬뽕9", 100L, 10L),
                new Product("9", "짬뽕10", 100L, 10L)
        ));

        productRepository.findAll().forEach((product)-> {
            executorService.execute(() -> {
                try {
                    orderService.saveOrder(
                            "user",
                            List.of(new OrderProductInfo(product.getProductNumber(), 1L))
                    );
                    successCount.getAndIncrement();
                } catch (RuntimeException e) {
                    System.out.println("fail:" + Thread.currentThread());
                    failCount.getAndIncrement();
                } finally {
                    countDownLatch.countDown();
                }
            });
        });

        countDownLatch.await();

        // 결과값 검증
        assertThat(successCount.get()).isEqualTo(10);
        assertThat(failCount.get()).isEqualTo(0);
//        assertThat(orderRepository.findAll().size()).isEqualTo(1); // 주문이 한건 이루어져야함
//        assertThat(productRepository.findByProductNumber("1234").getStock()).isEqualTo(99L); // 재고 검증
//        assertThat(userRepository.findByUsername("user").get().getPoint()).isEqualTo(0L); // 포인트 검증
    }
}
