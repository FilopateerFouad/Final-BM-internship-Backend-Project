package com.example.demo.controller;
import com.example.demo.DTO.FavouriteDTO;
import com.example.demo.exception.ResourceAlreadyExistException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.response.ErrorDetails;
import com.example.demo.service.IFavouriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/favourites")
@RequiredArgsConstructor
@Validated
public class FavouriteController {
    private final IFavouriteService favouriteService;
    @Operation(summary = "create favourite customer by id")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation =FavouriteDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @PostMapping("/{accountNumber}")
    public FavouriteDTO createFavourite(@PathVariable String accountNumber, @RequestBody FavouriteDTO favouriteDTO)throws ResourceNotFoundException , ResourceAlreadyExistException {
        return this.favouriteService.createFavourite(accountNumber,favouriteDTO);
    }
    @Operation(summary = "Get ALl Favourite")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = FavouriteDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @GetMapping("/{customerId}")
    public List<FavouriteDTO> getAllFavourites(@PathVariable Long customerId)throws ResourceNotFoundException{
        return this.favouriteService.getAllFavourites(customerId);
    }
    @Operation(summary = "Delete ALl Favourite")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = FavouriteDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @DeleteMapping
    public Boolean deleteFavourite(@RequestBody FavouriteDTO favouriteDTO) throws ResourceNotFoundException{
        return this.favouriteService.deleteFavourite(favouriteDTO);
    }
}
