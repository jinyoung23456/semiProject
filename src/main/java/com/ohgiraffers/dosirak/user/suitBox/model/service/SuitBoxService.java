package com.ohgiraffers.dosirak.user.suitBox.model.service;

import com.ohgiraffers.dosirak.admin.suitBox.model.dto.SuitBoxMenuDTO;
import com.ohgiraffers.dosirak.user.suitBox.model.dao.SuitBoxMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuitBoxService {
    private final SuitBoxMapper mapper;

    public SuitBoxService(SuitBoxMapper mapper) {
        this.mapper = mapper;
    }

    public List<SuitBoxMenuDTO> selectSaleMenu() {
        return mapper.selectSaleMenu();
    }
}
