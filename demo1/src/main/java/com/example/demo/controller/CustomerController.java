package com.example.demo.controller;
import com.example.demo.DTO.CustomerDTO;
import com.example.demo.DTO.UpdateCustomerDTO;
import com.example.demo.DTO.UpdatePasswordDTO;
import com.example.demo.exception.CustomerAlreadyExistException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.WrongPasswordException;
import com.example.demo.exception.response.ErrorDetails;
import com.example.demo.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
@Validated
@Tag(name = "Customer Controller", description = "Customer controller")
public class CustomerController {

    private final ICustomerService customerService;
    @Operation(summary = "Get customer by id")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = CustomerDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @GetMapping("/{customerId}")
    public CustomerDTO getCustomerbyId(@PathVariable Long customerId)throws ResourceNotFoundException{
        return this.customerService.getCustomerbyId(customerId);
    }
    @Operation(summary = "Update Customer Data")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = CustomerDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @PutMapping("/data/{customerId}")
    CustomerDTO updateCustomerData(@PathVariable Long customerId,@RequestBody UpdateCustomerDTO customerDTO)throws CustomerAlreadyExistException, ResourceNotFoundException{
        return this.customerService.updateCustomerData(customerId, customerDTO);
    }
    @Operation(summary = "Change Customer Password")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = CustomerDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @PutMapping("/password/{customerId1}")
    CustomerDTO updateCustomerPassword(@PathVariable Long customerId1,@RequestBody UpdatePasswordDTO customerDTO) throws ResourceNotFoundException, WrongPasswordException {
        return this.customerService.updateCustomerPassword(customerId1, customerDTO);
    }
}
