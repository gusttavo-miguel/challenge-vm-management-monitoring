package br.com.ustore.api.dto;

public record VirtualMachineDTO(String displayName, long memory, long cpu, String status) {
}
