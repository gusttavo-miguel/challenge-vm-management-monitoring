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
    @Length(min = 5)
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank(message = "A memória é obrigatória!")
    @Column(nullable = false)
    private long memory;

    @NotBlank(message = "O tamanho da CPU é obrigatório!")
    @Column(nullable = false)
    private long lengthCPU;

    @Column(nullable = false)
    private String status;
}