package br.com.ustore.api.dto;

public record VirtualMachineDTO(long id, String displayName, long memory, long cpu, String status) {
}
