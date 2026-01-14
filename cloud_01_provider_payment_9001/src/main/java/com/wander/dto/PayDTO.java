package com.wander.dto;

import com.wander.entity.Pay;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class PayDTO extends Pay implements Serializable {
}
