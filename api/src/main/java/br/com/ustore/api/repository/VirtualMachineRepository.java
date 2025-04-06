package br.com.ustore.api.repository;

import br.com.ustore.api.entity.VirtualMachineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VirtualMachineRepository extends JpaRepository<VirtualMachineEntity, Long> {
    VirtualMachineEntity findByDisplayName(String displayName);
}
