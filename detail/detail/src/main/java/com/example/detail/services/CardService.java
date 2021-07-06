package com.example.detail.services;

import com.example.detail.annotations.DataSourceSelect;
import com.example.detail.models.Card;
import com.example.detail.repositories.second.CardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @DataSourceSelect(DataSourceSelect.card)
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Card createCard(Card card) throws Exception {
        if (card.getBalance() == null) {
            throw new IllegalArgumentException("balance can not be null");
        } else if (card.getStudentId() == null) {
            throw new IllegalArgumentException("student id can not be null");
        }
        return cardRepository.save(card);
    }
}