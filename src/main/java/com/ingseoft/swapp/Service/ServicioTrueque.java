package com.ingseoft.swapp.Service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ingseoft.swapp.Model.Trueque;
import com.ingseoft.swapp.Repository.TruequeRepository;

@Component
public class ServicioTrueque {
    private TruequeRepository repository;

    public ServicioTrueque(TruequeRepository repository) {
        this.repository=repository;
    }

    public Iterable<Trueque> getAllTrueques(){
        return this.repository.findAll();
    }

    public Trueque addTrueque (Trueque newTrueque) throws Exception{
        List<Trueque> list1 = this.repository.findByIdTrueque(newTrueque.getId());
        boolean status1 = list1.isEmpty();
        if(status1 == false){
            throw new Exception("El id del trueque ya se encuentra registrado.");
        }else{
            return this.repository.save(newTrueque);
        }
    }
}
