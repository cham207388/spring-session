package com.abc.springsession.exception;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginError extends RuntimeException{
    private String message;
}