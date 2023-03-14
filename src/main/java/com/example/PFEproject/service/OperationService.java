package com.example.PFEproject.service;

import com.example.PFEproject.bean.Operation;
import com.example.PFEproject.repo.OperationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class OperationService {
    @Autowired
    OperationRepo operationRepo;

    public int deleteOperationById(Long id) {
        return operationRepo.deleteOperationById(id);
    }

    public List<Operation> findAll() {
        return operationRepo.findAll();
    }

    public List<Operation> findByCreateurOperationId(Long id) {
        return operationRepo.findByCreateurOperationId(id);
    }

    public  Operation save(Operation entity) throws Exception{
        if(entity == null){
            throw new Exception();
        }else {
            return operationRepo.save(entity);
        }
    }
}
