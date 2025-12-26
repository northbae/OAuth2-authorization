package kz.bmstu.kritinina.controller;

import kz.bmstu.kritinina.dto.RentalRequest;
import kz.bmstu.kritinina.dto.RentalResponse;
import kz.bmstu.kritinina.service.RentalService;
import kz.bmstu.kritinina.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RentalControllerImpl implements RentalController {

    private final RentalService rentalService;
    private final JwtUtils jwtUtils;

    @Override
    public ResponseEntity<Void> getHealth() {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<RentalResponse> getRentalById(UUID rentalUid) {
        String username = jwtUtils.getCurrentUsername();
        return ResponseEntity.ok(rentalService.getRentalById(rentalUid, username));
    }

    @Override
    public ResponseEntity<List<RentalResponse>> getAllRentals() {
        String username = jwtUtils.getCurrentUsername();
        return ResponseEntity.ok(rentalService.getAllRentals(username));
    }

    @Override
    public ResponseEntity<RentalResponse> createRental(RentalRequest rentalRequest) {
        String username = jwtUtils.getCurrentUsername();
        return ResponseEntity.ok(rentalService.createRental(username, rentalRequest));
    }

    @Override
    public ResponseEntity<Void> cancelRental(UUID rentalUid) {
        String username = jwtUtils.getCurrentUsername();
        rentalService.cancelRental(rentalUid, username);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> finishRental(UUID rentalUid) {
        String username = jwtUtils.getCurrentUsername();
        rentalService.finishRental(rentalUid, username);
        return ResponseEntity.ok().build();
    }
}