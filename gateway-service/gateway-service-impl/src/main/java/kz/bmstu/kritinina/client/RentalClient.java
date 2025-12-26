package kz.bmstu.kritinina.client;

import kz.bmstu.kritinina.config.FeignConfig;
import kz.bmstu.kritinina.dto.RentalRequest;
import kz.bmstu.kritinina.dto.RentalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "rental", url = "http://rental-service:8060/api/v1/rental", configuration = FeignConfig.class)
public interface RentalClient {

    @GetMapping(value = "/{rentalUid}")
    ResponseEntity<RentalResponse> getRentalById(@PathVariable("rentalUid") UUID rentalUid);

    @GetMapping
    ResponseEntity<List<RentalResponse>> getAllRentals();

    @PostMapping
    ResponseEntity<RentalResponse> createRental(@RequestBody RentalRequest rentalRequest);

    @PostMapping(value = "/{rentalUid}/finish")
    ResponseEntity<Void> finishRental(@PathVariable("rentalUid") UUID rentalUid);

    @DeleteMapping(value = "/{rentalUid}")
    ResponseEntity<Void> cancelRental(@PathVariable("rentalUid") UUID rentalUid);
}