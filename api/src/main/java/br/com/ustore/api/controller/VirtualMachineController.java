package br.com.ustore.api.controller;

import br.com.ustore.api.dto.MessageDTO;
import br.com.ustore.api.dto.VirtualMachineDTO;
import br.com.ustore.api.entity.VirtualMachineEntity;
import br.com.ustore.api.repository.VirtualMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/VM")
public class VirtualMachineController {

    @Autowired
    VirtualMachineRepository virtualMachineRepository;

    @PostMapping("/create")
    public MessageDTO createVirtualMachine(@RequestBody VirtualMachineDTO virtualMachineDTO){

        VirtualMachineEntity vmEntity = new VirtualMachineEntity(virtualMachineDTO.name()
                , virtualMachineDTO.lengthCPU()
                , virtualMachineDTO.memory()
                , virtualMachineDTO.status());

        VirtualMachineEntity vm = virtualMachineRepository.findByName(vmEntity.getName());
        if (vm!=null){
            virtualMachineRepository.save(vmEntity);
            return new MessageDTO("Máquina virtual cadastrado com sucesso!");
        }

        return new MessageDTO("Máquina virtual já cadastrado!");
    }
}
