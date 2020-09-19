package com.brillqr.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private Map<String,Object> data;
    private Map<String,Object> error;

    @JsonIgnore
    public Map<String,Object> getDataMap(){
        return this.data;
    }

    @JsonIgnore
    public Map<String,Object> getErrorMap(){
        return  this.error;
    }
}
