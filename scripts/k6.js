import http from "k6/http";
import { sleep } from "k6";

// docker-compose up -d influxdb grafana
// k6 run --out influxdb=http://127.0.0.1:8086 scripts/k6.js

// 동시에 한상품을 1000번 주문이 일어날 때
// - 재고 1000개 이상일 경우 순차적 처리
// - 재고 1000개 이하일 경우 반환 처리
export const options =  {
    // scenarios: {
    //     load_test: {
    //         executor: 'shared-iterations',
    //         vus: 500,
    //         maxDuration: "5m",
    //         exec: 'loadTest',
    //         iterations: 30000,
    //     },
    // },
    stages: [
        {duration: '5s', target: 100},
        {duration: '10s', target: 100},
        {duration: '5s', target: 150},
        {duration: '10s', target: 150},
        {duration: '5s', target: 200},
        {duration: '10s', target: 200},
    ],
    thresholds: {
        http_req_duration: ['p(95)<200'],
    },
};


// 한명의 유저가 1000번을 메크로 했을때, 결제를 순차적으로 처리
export default function() {

    const url = 'http://localhost:8080/order';
    const payload = JSON.stringify({
        username: 'A',
        orderProductInfos: [
            {
                "productNumber": "1",
                "stock": 1
            }
        ],
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    http.post(url, payload, params);
    sleep(1);
}
