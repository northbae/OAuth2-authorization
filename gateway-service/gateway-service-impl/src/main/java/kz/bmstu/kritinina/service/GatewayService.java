package kz.bmstu.kritinina.service;

import kz.bmstu.kritinina.config.CarPage;
import kz.bmstu.kritinina.dto.BookCarDto;
import kz.bmstu.kritinina.dto.CarDto;
import kz.bmstu.kritinina.dto.RentalCreationDto;
import kz.bmstu.kritinina.dto.RentalDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface GatewayService {
    CarPage<CarDto> getAllCars(Boolean showAll, Pageable pageable);
    List<RentalDto> getRental();
    RentalDto getRental(UUID rentalUid);
    RentalCreationDto bookCar(BookCarDto bookCarDto);
    void finishRental(UUID rentalUid);
    void cancelRental(UUID rentalUid);
}