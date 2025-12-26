package kz.bmstu.kritinina.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kz.bmstu.kritinina.config.CarPage;
import kz.bmstu.kritinina.dto.BookCarDto;
import kz.bmstu.kritinina.dto.CarDto;
import kz.bmstu.kritinina.dto.RentalCreationDto;
import kz.bmstu.kritinina.dto.RentalDto;
import kz.bmstu.kritinina.dto.ServiceError;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("api/v1")
public interface GatewayController {

    @GetMapping("/manage/health")
    ResponseEntity<Void> getHealth();

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список доступных для бронирования автомобилей"),
            @ApiResponse(responseCode = "500", description = "Сервис недоступен",
                    content = { @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ServiceError.class)) })
    })
    @GetMapping("/cars")
    CarPage<CarDto> getAllCars(@RequestParam Boolean showAll,
                               @ParameterObject Pageable pageable);

    @ApiResponse(responseCode = "200", description = "Список аренд пользователя")
    @GetMapping("/rental")
    ResponseEntity<List<RentalDto>> getRental();

    @GetMapping("/rental/{rentalUid}")
    ResponseEntity<RentalDto> getRental(@PathVariable("rentalUid") UUID rentalUid);

    @PostMapping("/rental")
    ResponseEntity<RentalCreationDto> bookCar(@RequestBody BookCarDto bookCarDto);

    @PostMapping("/rental/{rentalUid}/finish")
    ResponseEntity<Void> finishRental(@PathVariable("rentalUid") UUID rentalUid);

    @DeleteMapping("/rental/{rentalUid}")
    @ApiResponse(responseCode = "204", description = "Аренда успешно отменена")
    ResponseEntity<Void> cancelRental(@PathVariable("rentalUid") UUID rentalUid);
}