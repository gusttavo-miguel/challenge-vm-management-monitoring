package br.com.ustore.api.controller;

import br.com.ustore.api.dto.MessageDTO;
import br.com.ustore.api.dto.VirtualMachineDTO;
import br.com.ustore.api.entity.UserEntity;
import br.com.ustore.api.entity.VirtualMachineEntity;
import br.com.ustore.api.repository.VirtualMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/VirtualMachine")
public class VirtualMachineController {

    @Autowired
    VirtualMachineRepository virtualMachineRepository;

    @PostMapping("/create")
    public MessageDTO createVirtualMachine(@Validated @RequestBody VirtualMachineDTO virtualMachineDTO){

        VirtualMachineEntity vmEntity = new VirtualMachineEntity(virtualMachineDTO.displayName()
                ,virtualMachineDTO.cpu()
                ,virtualMachineDTO.memory()
                ,virtualMachineDTO.status());

        VirtualMachineEntity vm = virtualMachineRepository.findByDisplayName(vmEntity.getDisplayName());
        if (vm==null){
            virtualMachineRepository.save(vmEntity);
            return new MessageDTO("Máquina virtual cadastrado com sucesso!");
        }

        return new MessageDTO("Máquina virtual já cadastrado!");
    }

    @PutMapping("/update")
    public MessageDTO updateVirtualMachine(@RequestBody VirtualMachineDTO virtualMachineDTO) {
        Optional<VirtualMachineEntity> existingVM = virtualMachineRepository.findById(virtualMachineDTO.id());

        if (existingVM.isEmpty()) {
            return new MessageDTO("Máquina virtual ainda não cadastrada!");
        }

        // Verifica se existe outra VM com o mesmo displayName
        Optional<VirtualMachineEntity> vmWithSameName = Optional.ofNullable(virtualMachineRepository.findByDisplayName(virtualMachineDTO.displayName()));
        if (vmWithSameName.isPresent() && !vmWithSameName.get().getId().equals(virtualMachineDTO.id())) {
            return new MessageDTO("Já existe uma máquina virtual com esse nome!");
        }

        VirtualMachineEntity vmEntity = new VirtualMachineEntity(
                virtualMachineDTO.id(),
                virtualMachineDTO.displayName(),
                virtualMachineDTO.cpu(),
                virtualMachineDTO.memory(),
                virtualMachineDTO.status()
        );

        virtualMachineRepository.save(vmEntity);
        return new MessageDTO("Máquina virtual atualizada com sucesso!");
    }


    @GetMapping("/all")
    public List<VirtualMachineEntity> getAllUsers() {
        return virtualMachineRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public MessageDTO deleteVirtualMachine(@PathVariable Long id) {
        virtualMachineRepository.deleteById(id);
        return new MessageDTO("Máquina virtual excluída com sucesso!");
    }
}