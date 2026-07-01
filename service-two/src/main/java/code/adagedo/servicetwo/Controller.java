package code.adagedo.servicetwo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/api/v1/orders/1")
    public ResponseEntity<Orders> ordersResponseEntity() {
        Orders orders = new Orders("Pizza", "James", 9);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
}
