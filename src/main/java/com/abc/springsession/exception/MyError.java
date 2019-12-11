package com.abc.springsession.exception;

import lombok.*;

import static com.abc.springsession.utils.EmployeeUtil.getDateTime;


@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyError extends RuntimeException{
    private String message;
    private String timestamp = getDateTime();
}
