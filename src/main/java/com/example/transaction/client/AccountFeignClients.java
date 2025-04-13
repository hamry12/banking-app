package com.example.transaction.client;

import com.example.transaction.dto.AccountResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("accounts")
public interface AccountFeignClients {

    @GetMapping("/api/requests/accounts/{id}")
    public ResponseEntity<AccountResponseDto> getAccountDetails(@PathVariable("id") Long accountId);
}
