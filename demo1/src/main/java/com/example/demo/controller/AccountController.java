package com.example.demo.controller;
import com.example.demo.DTO.AccountDTO;
import com.example.demo.DTO.CreateAccountDTO;
import com.example.demo.DTO.TransactionDTO;
import com.example.demo.DTO.TransferRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.response.ErrorDetails;
import com.example.demo.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
@Validated
@Tag(name = "Account Controller", description = "Account controller")
public class AccountController {
    private final IAccountService accountService;
    @PostMapping
    @Operation(summary = "Create new Account")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = AccountDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    public AccountDTO createAccount(@Valid @RequestBody CreateAccountDTO accountDTO) throws ResourceNotFoundException {
        return this.accountService.createAccount(accountDTO);
    }
    @Operation(summary = "Get Account by Id")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = AccountDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @GetMapping("/getaccount/{accountNumber}")
    public AccountDTO getAccount(@PathVariable String accountNumber) throws ResourceNotFoundException {
        return this.accountService.getAccountById(accountNumber);
    }
    @Operation(summary = "Get Transaction set for the account")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = TransactionDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @GetMapping("/transactions/{accountNumber}")
    public List<TransactionDTO> getAllTransactions(@PathVariable String accountNumber)throws ResourceNotFoundException{
        return this.accountService.getAllTransactions(accountNumber);
    }
    @Operation(summary = "Get Balance for the account")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = AccountDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @GetMapping("/balance/{accountNumber}")
    public BigDecimal getBalanaceById(@PathVariable String accountNumber) throws ResourceNotFoundException{
        return this.accountService.getBalanaceById(accountNumber);
    }
    @Operation(summary = "Transfer funds",
            description = "Transfers funds from one account to another",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Transfer successful"),
                    @ApiResponse(responseCode = "400", description = "Invalid transfer request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestBody TransferRequest transferRequestDTO) throws ResourceNotFoundException{
        accountService.transferMoney(transferRequestDTO.getFromAccount(), transferRequestDTO.getToAccount(), transferRequestDTO.getAmount());
        return ResponseEntity.ok("Transfer successful");
    }
}
