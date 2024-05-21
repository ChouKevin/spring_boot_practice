package com.example.demo.CRM.company.client.register;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterClientReq {
    
    @Data
    public static class ClientInfo {

        @NotBlank
        String name;
        
        String phone;
        @Email
        String email;
    }
    
    @NotNull
    Long companyId;

    @Valid
    List<ClientInfo> clientInfos;
}
