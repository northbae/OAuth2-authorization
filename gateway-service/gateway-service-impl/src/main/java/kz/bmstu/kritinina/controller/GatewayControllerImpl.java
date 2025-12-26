package kz.bmstu.kritinina.controller;

import kz.bmstu.kritinina.config.CarPage;
import kz.bmstu.kritinina.dto.BookCarDto;
import kz.bmstu.kritinina.dto.CarDto;
import kz.bmstu.kritinina.dto.RentalCreationDto;
import kz.bmstu.kritinina.dto.RentalDto;
import kz.bmstu.kritinina.service.GatewayService;
import kz.bmstu.kritinina.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class GatewayControllerImpl implements GatewayController {

    private final GatewayService gatewayService;

    @Override
    public ResponseEntity<Void> getHealth() {
        return ResponseEntity.ok().build();
    }

    @Override
    public CarPage<CarDto> getAllCars(Boolean showAll, Pageable pageable) {
        return gatewayService.getAllCars(showAll, pageable);
    }

    @Override
    public ResponseEntity<List<RentalDto>> getRental() {
        return ResponseEntity.ok(gatewayService.getRental());
    }

    @Override
    public ResponseEntity<RentalDto> getRental(UUID rentalUid) {
        return ResponseEntity.ok(gatewayService.getRental(rentalUid));
    }

    @Override
    public ResponseEntity<RentalCreationDto> bookCar(BookCarDto bookCarDto) {
        return ResponseEntity.ok(gatewayService.bookCar(bookCarDto));
    }

    @Override
    public ResponseEntity<Void> finishRental(UUID rentalUid) {
        gatewayService.finishRental(rentalUid);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> cancelRental(UUID rentalUid) {
        gatewayService.cancelRental(rentalUid);
        return ResponseEntity.noContent().build();
    }
}