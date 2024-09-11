package com.example.demo.service;

import com.example.demo.DTO.FavouriteDTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Favourite;
import com.example.demo.exception.ResourceAlreadyExistException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavouriteService implements IFavouriteService {
    private final AccountRepository accountRepository;

    @Override
    public FavouriteDTO createFavourite(String CurrentAccountNumber,FavouriteDTO favouriteDTO)throws ResourceNotFoundException , ResourceAlreadyExistException {
       Account account = this.accountRepository.findByAccountNumber(favouriteDTO.getRecipientName());
       if(account == null) {
           throw new ResourceNotFoundException("Favourite Account not found");
       }
        Account currentAccount = this.accountRepository.findByAccountNumber(CurrentAccountNumber);
       if(currentAccount == null) {
           throw new ResourceNotFoundException("Current Account not found");
       }
       List<FavouriteDTO>favourites=account.getFavourites().stream().map(Favourite::toDTO).toList();
        boolean favouriteExists = favourites.stream()
                .anyMatch(fav -> fav.getRecipientAccount().equals(favouriteDTO.getRecipientAccount()));
        if (favouriteExists) {
            throw new ResourceAlreadyExistException("Favourite already exists");
        }

        Favourite favourite=Favourite.builder().
                account(account).
                recipientAccount(favouriteDTO.getRecipientAccount()).
                recipientName(favouriteDTO.getRecipientName()).
                build();
        currentAccount.getFavourites().add(favourite);
        return favourite.toDTO();
    }
    @Override
    public List<FavouriteDTO> getAllFavourites(Long customerId)throws ResourceNotFoundException {
        Account account = this.accountRepository.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Account not found"));
        return account.getFavourites().stream().map(Favourite::toDTO).collect(Collectors.toList());
    }

    @Override
    public Boolean deleteFavourite(FavouriteDTO favouriteDTO) throws ResourceNotFoundException {
        Account account = this.accountRepository.findByAccountNumber(favouriteDTO.getRecipientAccount());
        if(account==null){
            throw new ResourceNotFoundException("Account not found");
    }
        Favourite favourite=Favourite.builder().
                account(account).
                recipientAccount(favouriteDTO.getRecipientAccount()).
                recipientName(favouriteDTO.getRecipientName()).
                build();
        account.getFavourites().remove(favourite);
        accountRepository.save(account);
        return true;
    }
}
