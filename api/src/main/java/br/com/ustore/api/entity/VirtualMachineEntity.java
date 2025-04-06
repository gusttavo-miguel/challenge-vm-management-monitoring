package br.com.ustore.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "virtual_machine")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VirtualMachineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório!")
    @Length(min = 3)
    @Column(nullable = false, unique = true)
    private String displayName;

    @Column(nullable = false)
    private long memory;

    @Column(nullable = false)
    private long cpu;

    @Column(nullable = true)
    private String status;

    public VirtualMachineEntity(String displayName, long cpu, long memory, String status) {
        this.displayName = displayName;
        this.cpu = cpu;
        this.memory = memory;
        this.status = status;
    }
}