package com.example.demo.service;

import com.example.demo.DTO.FavouriteDTO;
import com.example.demo.entity.Favourite;
import com.example.demo.exception.ResourceAlreadyExistException;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.List;

public interface IFavouriteService {
    FavouriteDTO createFavourite(String CurrentAccountNumber,FavouriteDTO favouriteDTO)throws ResourceNotFoundException, ResourceAlreadyExistException;
    List<FavouriteDTO> getAllFavourites(Long customerId)throws ResourceNotFoundException;
    Boolean deleteFavourite(FavouriteDTO favouriteDTO)throws ResourceNotFoundException;
}
